package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.tree.AbstractInsnNode;


import jdk.graal.compiler.test.bytecodefuzz.Pair;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class InsertMethodCallMutation implements Mutation {

    private static record UnInlineCandidate(Type methodType, int iindex, AbstractInsnNode insn){};

    public final byte[] mutate(byte[] data, PseudoRandom prng) {
        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, ClassReader.EXPAND_FRAMES);

        MethodNode mn = MethodSelector.select(cn, prng);

        InstructionVisitor instructionVisitor = new InstructionVisitor(Opcodes.ASM9);
        AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, instructionVisitor);

        List<UnInlineCandidate> candidates = new ArrayList<>();

        for (AbstractInsnNode insn : mn.instructions) {
            int iindex = instructionVisitor.iindex();
            Type methodType = AsmTypeSupport.getInstructionMethodType(insn, analyzer.stack);
            if (methodType != null) {
                candidates.add(new UnInlineCandidate(methodType, iindex, insn));
            }
            insn.accept(analyzer);
        }

        if (candidates.isEmpty()) {
            throw new RuntimeException("No uninlineable instruction found!");
        }

        UnInlineCandidate target = prng.pickIn(candidates);

        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM9, writer) {

            Set<String> methodNames = new HashSet<>();


            private static record VisitMethodParams(int access, String name, String descriptor, String signature, String[] exceptions){};
            private VisitMethodParams fuzzedMethodParams = null;

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                methodNames.add(name);

                if (!name.equals(mn.name)) {
                    return this.cv.visitMethod(access, name, descriptor, signature, exceptions);
                }
                else {
                    // Visit this method at the end after we calculate the new name of the new method
                    fuzzedMethodParams = new VisitMethodParams(access, name, descriptor, signature, exceptions);
                    return null;
                }
            }

            private String generateUniqueTargetMethodName() {
                String initialName = "{uninlined}";

                if (!methodNames.contains(initialName)) return initialName;

                int version = 0;
                while(methodNames.contains(initialName + version)) {
                    version++;
                }
                return initialName + version;
            }

            @Override
            public void visitEnd() {

                String methodName = generateUniqueTargetMethodName();

                // Generate new method
                Method method = new Method(methodName, target.methodType.getDescriptor());
                GeneratorAdapter generator = new GeneratorAdapter(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, method, null, null, this);

                generator.loadArgs();
                target.insn.accept(generator);
                generator.returnValue();
                generator.endMethod();

                // Add the fuzzed method
                MethodVisitor mv = this.cv.visitMethod(fuzzedMethodParams.access, fuzzedMethodParams.name, fuzzedMethodParams.descriptor, fuzzedMethodParams.signature, fuzzedMethodParams.exceptions);
                ReplaceInsnWithCallMethodVisitor replaceMv = new ReplaceInsnWithCallMethodVisitor(Opcodes.ASM9, mv, methodName);
                mn.accept(replaceMv);

                this.cv.visitEnd();
            }

            private class ReplaceInsnWithCallMethodVisitor extends InstructionVisitor {

                private final String methodName;

                public ReplaceInsnWithCallMethodVisitor(int api, MethodVisitor mv, String methodName) {
                    super(api, mv);
                    this.methodName = methodName;
                }

                private boolean tryInsertCall() {
                    boolean shouldInsert = iindex() == target.iindex;
                    if (shouldInsert) {
                        this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, cn.name, methodName, target.methodType.getDescriptor(), false);
                        visitInstructionInternal();
                    }
                    return shouldInsert;
                }

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                    if (tryInsertCall()) return;
                    super.visitFieldInsn(opcode, owner, name, descriptor);
                }

                @Override
                public void visitInsn(int opcode) {
                    if (tryInsertCall()) return;
                    super.visitInsn(opcode);
                }

                @Override
                public void visitIntInsn(int opcode, int operand) {
                    if (tryInsertCall()) return;
                    super.visitIntInsn(opcode, operand);
                }

                @Override
                public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                    if (tryInsertCall()) return;
                    super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
                }

                @Override
                public void visitLdcInsn(Object value) {
                    if (tryInsertCall()) return;
                    super.visitLdcInsn(value);
                }

                @Override
                public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
                    if (tryInsertCall()) return;
                    super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
                }

                @Override
                public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
                    if (tryInsertCall()) return;
                    super.visitMultiANewArrayInsn(descriptor, numDimensions);
                }

                @Override
                public void visitTypeInsn(int opcode, String type) {
                    if (tryInsertCall()) return;
                    super.visitTypeInsn(opcode, type);
                }
            };
        };
        cn.accept(visitor);
        return writer.toByteArray();
    }
}
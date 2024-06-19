package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.LocalVariablesSorter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class InsertJumpMutation implements Mutation {

    private final int maxJumps;

    public InsertJumpMutation() {
        this.maxJumps = 10;
    }

    public InsertJumpMutation(int maxJumps) {
        this.maxJumps = maxJumps;
    }

    public void mutate(ClassReader reader, ClassWriter writer, FreeSpace freeSpace, PseudoRandom prng) {

        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, ClassReader.EXPAND_FRAMES);

        // TODO: better method selection
        // Select first non-ctor method for now
        MethodNode mn = null;
        for (var m: cn.methods) {
            if (!m.name.equals("<init>")) {
                mn = m;
                break;
            }
        }

        if (mn == null) {
            throw new RuntimeException("Mutated class has no method other than the constructor!");
        }

        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc);
        mn.accept(frameMapAnalyzer);

        Jump jump = selectJump(frameMapAnalyzer.getMap(), prng);

        System.out.println("Selected jump: " + jump.toString());

        InsertJumpClassVisitor jumpVisitor = new InsertJumpClassVisitor(Opcodes.ASM9, writer, jump, mn.name, this.maxJumps);
        cn.accept(jumpVisitor);

        freeSpace.add(reader.b.length - writer.toByteArray().length);
        if (freeSpace.amount() < 0) {
            System.err.println("Exceeded maximal Size!!!");
        }
    }

    private static record Jump(int source, int target) {}

    private static Function<List<Integer>, Integer> weightFunction = l -> l.size();

    private Jump selectJump(Map<String, List<Integer>> frameMap, PseudoRandom prng) {

        int weightSum = frameMap.values().stream()
            .map(weightFunction)
            .reduce(0, (a,x) -> a + x);

        int rand = prng.indexIn(weightSum);

        int i = 0;
        int cummulativeWeight = 0;

        for(Iterator<Integer> it = frameMap.values().stream().map(weightFunction).iterator(); it.hasNext(); i++) {
            cummulativeWeight += it.next();

            if (cummulativeWeight > rand)
                break;
        }

        String signature = frameMap.keySet().stream().skip(i).iterator().next();

        List<Integer> bucket = frameMap.get(signature);

        int source = prng.pickIn(bucket);
        int target = prng.pickIn(bucket);

        
        return new Jump(source, target);
    }

    private static class InsertJumpClassVisitor extends ClassVisitor {
    
        private final String methodName;
        private final Jump jump;
        private final int maxJumps;

        public InsertJumpClassVisitor(int api, ClassVisitor classVisitor, Jump jump, String methodName, int maxJumps) {
            super(api, classVisitor);

            this.methodName = methodName;
            this.jump = jump;
            this.maxJumps = maxJumps;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

            MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
            
            if (!name.equals(methodName)) {
                return mv;
            }

            System.out.println("Mutation method " + name);

            InsertJumpMethodVisitor methodVisitor = new InsertJumpMethodVisitor(api, mv);
            LocalVariablesSorter localSorter = new LocalVariablesSorter(access, descriptor, methodVisitor);
            methodVisitor.localSorter = localSorter;

            return localSorter;
        }

        private class InsertJumpMethodVisitor extends InstructionVisitor {
            
            public InsertJumpMethodVisitor(int api, MethodVisitor mv) {
                super(api, mv);
            }

            public LocalVariablesSorter localSorter;

            private int varNum = -1;
            private int iindex = 0;

            private final Label label = new Label();

            @Override
            public void visitCode() {

                // Alloc a new variable and initialize it with maxJumps
                varNum = localSorter.newLocal(Type.INT_TYPE);
                mv.visitLdcInsn(maxJumps);
                mv.visitVarInsn(Opcodes.ISTORE, varNum);
            }
            
            private void insertJumpTarget() {
                mv.visitLabel(label);
            }

            private void insertJumpSource() {
                mv.visitIincInsn(varNum, -1);
                mv.visitVarInsn(Opcodes.ILOAD, varNum);
                mv.visitJumpInsn(Opcodes.IFGE, label);
            }

            private void tryInsert() {
                if (iindex == jump.source) {
                    insertJumpSource();
                }
                if (iindex == jump.target) {
                    insertJumpTarget();
                }
                
                iindex++;
            }

            @Override
            public void visitInstruction() {
                tryInsert();
            }
        }
    }

}
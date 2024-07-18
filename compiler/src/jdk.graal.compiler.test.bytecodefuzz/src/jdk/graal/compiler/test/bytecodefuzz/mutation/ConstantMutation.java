package jdk.graal.compiler.test.bytecodefuzz.mutation;

import jdk.graal.compiler.test.bytecodefuzz.FreeSpace;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import java.util.List;
import java.util.ArrayList;

public class ConstantMutation implements NonGrowingMutation {

    private final boolean ctorOnly;

    public ConstantMutation(boolean ctorOnly) {
        this.ctorOnly = ctorOnly;
    }

    public ConstantMutation() {
        this(true);
    }

    private MethodNode selectMethod(ClassNode cn, PseudoRandom prng) {
        if (ctorOnly) {
            return cn.methods.stream()
                .filter(m -> m.name.equals("<init>") && m.desc.equals("()V"))
                .findAny()
                .get();
        }
        else {
            return MethodSelector.select(cn, prng, true);
        }
    }

    public byte[] mutate(byte[] data, PseudoRandom prng, FreeSpace freeSpace) {
        
        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, 0);
        
        MethodNode mn = selectMethod(cn, prng);

        // find all constant loading -> custom analyzer
        LoadConstantLocator locator = new LoadConstantLocator(Opcodes.ASM9);
        mn.accept(locator);

        List<Integer> locations = locator.getLocations(); 
        
        if (locations.isEmpty()) {
            System.err.println("Constant mutator selected a method without constants!");
            return null;
        }

        int location = prng.pickIn(locations);

        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    
                MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
    
                if (name.equals(mn.name)) {
                    MutateConstantsMethodVisitor mcmv = new MutateConstantsMethodVisitor(this.api, mv, prng, freeSpace);
                    return new MutateConstantDelegator(this.api, mv, mcmv, location);
                }
                else {
                    return mv;
                }
            }
        };
        
        cn.accept(cv);
        return writer.toByteArray();
    }    
    
    /**
     * Delegates between the given MethodVisitor mv (in our case the one from ClassWriter) and MutateConstantsMethodVisitor mcmv.
     * MutateConstantsMethodVisitor is written in a way that it mutates all constants and we delegate the dispatch of which constant to mutate to this class.
     * 
     * When we delegate the instruction to mcmv, we still have to visit the instruction in InstructionVisitor, hence we call the visitInstructionInternal here.
     */
    private static class MutateConstantDelegator extends InstructionVisitor {

        private final MutateConstantsMethodVisitor mcmv;
        private final int location;

        public MutateConstantDelegator(int api, MethodVisitor mv, MutateConstantsMethodVisitor mcmv, int location) {
            super(api, mv);
            this.mcmv = mcmv;
            this.location = location;
        }

        @Override
        public void visitLdcInsn(Object value){
            if (location == iindex()) {
                mcmv.visitLdcInsn(value);
                super.visitInstructionInternal();
            }
            else {
                super.visitLdcInsn(value);
            }
        }

        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (opcode == Opcodes.BIPUSH || opcode == Opcodes.SIPUSH) {
                if (location == iindex()) {
                    mcmv.visitIntInsn(opcode, operand);
                    super.visitInstructionInternal();
                    return;
                }
                
            }
            super.visitIntInsn(opcode, operand);
        }
       
        @Override
        public void visitInsn(int opcode) {
            if (LoadConstantLocator.isOpcodeXCONST(opcode)) {
                if (location == iindex()) {
                    mcmv.visitInsn(opcode);
                    super.visitInstructionInternal();
                    return;
                }
            }
            super.visitInsn(opcode);
        }
    }
}
package jdk.graal.compiler.test.bytecodefuzz;

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

    public void mutate(ClassReader reader, ClassWriter writer, FreeSpace freeSpace, PseudoRandom prng) {
        
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, 0);
        
        MethodNode mn = selectMethod(cn, prng);

        // find all constant loading -> custom analyzer
        LoadConstantLocator locator = new LoadConstantLocator(Opcodes.ASM9);
        mn.accept(locator);

        List<Integer> locations = locator.getLocations(); 
        
        if (locations.isEmpty()) {
            // TODO: solve in a better way!
            System.err.println("Constant mutator selected a method without constants!");
            return;
        }

        int location = prng.pickIn(locations);

        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    
                MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
    
                if (name.equals(mn.name)) {
                    MutateConstantsMethodVisitor mcmv = new MutateConstantsMethodVisitor(this.api, mv, prng, freeSpace);
                    MutateConstantDelegator delegator = new MutateConstantDelegator(this.api, mv, mcmv, location);
                    return delegator;
                }
                else {
                    return mv;
                }
            }
        };
        
        cn.accept(cv);
    }

    private static class LoadConstantLocator extends InstructionVisitor {

        public LoadConstantLocator(int api) {
            super(api);
        }

        List<Integer> loadConstantLocations; 

        public List<Integer> getLocations() {
            return loadConstantLocations;
        }

        @Override
        public void visitCode() {
            super.visitCode();
            loadConstantLocations = new ArrayList<>();
        }

        @Override
        public void visitLdcInsn(Object value){
           // Optimistically include all Ldc locations, even if it is possible that the value itself might not be mutatable
           loadConstantLocations.add(iindex());
           super.visitLdcInsn(value);
        }

        //? Should we keep the type or should we allow to make the constants bigger?
        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (opcode == Opcodes.BIPUSH || opcode == Opcodes.SIPUSH) {
                loadConstantLocations.add(iindex());
            }
            super.visitIntInsn(opcode, operand);
        }

        @Override
        public void visitInsn(int opcode) {
            if (MutateConstantsMethodVisitor.isOpcodeXCONST(opcode)) {
                loadConstantLocations.add(iindex());
            }
            super.visitInsn(opcode);
        }
    }
    
    /**
     * Delegates between the given MethodVisitor mv (in our case the one from ClassWriter) and MutateConstantsMethodVisitor mcmv.
     * MutateConstantsMethodVisitor is written in a way that it mutates all constants and we delegate the dispatch of which constant to mutate to this class.
     * 
     * When we delegate the instruction to mcmv, we still have to visit the instruction in InstructionVisitor, hence we call the visitInstructionInternal here.
     */

     // TODO: change back to private
    public static class MutateConstantDelegator extends InstructionVisitor {

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
            if (MutateConstantsMethodVisitor.isOpcodeXCONST(opcode)) {
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
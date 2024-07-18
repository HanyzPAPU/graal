package jdk.graal.compiler.test.bytecodefuzz;

import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Opcodes;

public class LoadConstantLocator extends InstructionVisitor {

    public static boolean isOpcodeICONST(int opcode) {
        return 
            opcode == Opcodes.ICONST_M1 ||
            opcode == Opcodes.ICONST_0 ||
            opcode == Opcodes.ICONST_1 ||
            opcode == Opcodes.ICONST_2 ||
            opcode == Opcodes.ICONST_3 ||
            opcode == Opcodes.ICONST_4 ||
            opcode == Opcodes.ICONST_5;
    }

    public static boolean isOpcodeXCONST(int opcode) {
        return 
            opcode == Opcodes.ICONST_M1 ||
            opcode == Opcodes.ICONST_0 ||
            opcode == Opcodes.ICONST_1 ||
            opcode == Opcodes.ICONST_2 ||
            opcode == Opcodes.ICONST_3 ||
            opcode == Opcodes.ICONST_4 ||
            opcode == Opcodes.ICONST_5 ||
            opcode == Opcodes.LCONST_0 ||
            opcode == Opcodes.LCONST_1 ||
            opcode == Opcodes.DCONST_0 ||
            opcode == Opcodes.DCONST_1 ||
            opcode == Opcodes.FCONST_0 ||
            opcode == Opcodes.FCONST_1 ||
            opcode == Opcodes.FCONST_2 ;
    }

    private final boolean intOnly;

    public LoadConstantLocator(int api) {
        this(api, false);
    }

    public LoadConstantLocator(int api, boolean intOnly) {
        super(api);
        this.intOnly = intOnly;
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
        if ((intOnly && value instanceof Integer) || (!intOnly)) {
            loadConstantLocations.add(iindex());
        }
        super.visitLdcInsn(value);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        if (opcode == Opcodes.BIPUSH || opcode == Opcodes.SIPUSH) {
            loadConstantLocations.add(iindex());
        }
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((intOnly && isOpcodeICONST(opcode)) || (!intOnly && isOpcodeXCONST(opcode))) {
            loadConstantLocations.add(iindex());
        }
        super.visitInsn(opcode);
    }
}
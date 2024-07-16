package jdk.graal.compiler.test.bytecodefuzz;

import org.objectweb.asm.Label;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

public class InstructionVisitor extends MethodVisitor {

    private int iindex;

    protected InstructionVisitor(int api) {
        super(api);
    }

    protected InstructionVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    protected int iindex() {
        return iindex;
    }   

    protected final void visitInstructionInternal() {
        visitInstruction();
        iindex++;
    }

    public void visitInstruction() { }

    @Override
    public void visitCode() {
        iindex = 0;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        visitInstructionInternal();
        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitIincInsn(int varIndex, int increment) {
        visitInstructionInternal();
        super.visitIincInsn(varIndex, increment);
    }

    @Override
    public void visitInsn(int opcode) {
        visitInstructionInternal();
        super.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        visitInstructionInternal();
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        visitInstructionInternal();
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        visitInstructionInternal();
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitLdcInsn(Object value) {
        visitInstructionInternal();
        super.visitLdcInsn(value);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        visitInstructionInternal();
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
        visitInstructionInternal();
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        visitInstructionInternal();
        super.visitMultiANewArrayInsn(descriptor, numDimensions);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        visitInstructionInternal();
        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        visitInstructionInternal();
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitVarInsn(int opcode, int varIndex) {
        visitInstructionInternal();
        super.visitVarInsn(opcode, varIndex);
    }
}
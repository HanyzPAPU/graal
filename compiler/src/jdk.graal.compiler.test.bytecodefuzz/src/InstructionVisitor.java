package jdk.graal.compiler.test.bytecodefuzz;

import org.objectweb.asm.Label;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

public class InstructionVisitor extends MethodVisitor {

    protected InstructionVisitor(int api) {
        super(api);
    }

    protected InstructionVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    public void visitInstruction() {}

    @Override
    public final void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        visitInstruction();
        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public final void visitIincInsn(int varIndex, int increment) {
        visitInstruction();
        super.visitIincInsn(varIndex, increment);
    }

    @Override
    public final void visitInsn(int opcode) {
        visitInstruction();
        super.visitInsn(opcode);
    }

    @Override
    public final void visitIntInsn(int opcode, int operand) {
        visitInstruction();
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public final void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        visitInstruction();
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public final void visitJumpInsn(int opcode, Label label) {
        visitInstruction();
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public final void visitLdcInsn(Object value) {
        visitInstruction();
        super.visitLdcInsn(value);
    }

    @Override
    public final void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        visitInstruction();
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public final void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
        visitInstruction();
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
    }

    @Override
    public final void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        visitInstruction();
        super.visitMultiANewArrayInsn(descriptor, numDimensions);
    }

    @Override
    public final void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        visitInstruction();
        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public final void visitTypeInsn(int opcode, String type) {
        visitInstruction();
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public final void visitVarInsn(int opcode, int varIndex) {
        visitInstruction();
        super.visitVarInsn(opcode, varIndex);
    }
}
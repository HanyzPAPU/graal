package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import org.objectweb.asm.Label;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;

public class FrameMapAnalyzer extends MethodVisitor {

    private FrameTracer frameTracer;

    public FrameMapAnalyzer(int api, String owner, int access, String name, String descriptor) {
        super(api);
        this.frameTracer = new FrameTracer(api);
        AnalyzerAdapter analyzer = new AnalyzerAdapter(owner, access, name, descriptor, this.frameTracer);
        this.mv = analyzer;
        this.frameTracer.analyzer = analyzer;
    }

    /**
     * Returns a Map from frameMap signature to a Set of bytecode indices of instructions before which the frameMap has the corresponding signature.
     * 
     * A signature consists of the types on the stack and in local variables
     */
    public Map<String, List<Integer>> getMap() {
        return this.frameTracer.getMap();
    }

    private class FrameTracer extends MethodVisitor {

        public AnalyzerAdapter analyzer = null;

        private Map<String, List<Integer>> map = null;
        private int iindex;

        public FrameTracer(int api) {
            super(api);
        }

        public Map<String, List<Integer>> getMap() {
            return this.map;
        }

        private String getCurrentSignature() {
            String stackSignature = analyzer.stack == null ? "NULL" : analyzer.stack.toString();
            String localsSignature = analyzer.locals == null ? "NULL" : analyzer.locals.toString();
            return stackSignature + "$" + localsSignature;
        }

        private void updateMap() {
            String signature = getCurrentSignature();
            map.computeIfAbsent(signature, key -> new ArrayList<>()).add(iindex);
            iindex++;
        }

        @Override
        public void visitCode() {
            // Reset variables
            iindex = 0;
            // Prefer creating a new Map instead of clearing in case anybody stored a reference
            map = new HashMap<>();
        }

        @Override
        public void visitEnd() {
            System.out.println(map);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
            updateMap();
        }

        @Override
        public void visitIincInsn(int varIndex, int increment) {
            updateMap();
        }

        @Override
        public void visitInsn(int opcode) {
            updateMap();
        }

        @Override
        public void visitIntInsn(int opcode, int operand) {
            updateMap();
        }

        @Override
        public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
            updateMap();
        }

        @Override
        public void visitJumpInsn(int opcode, Label label) {
            updateMap();
        }

        @Override
        public void visitLdcInsn(Object value) {
            updateMap();
        }

        @Override
        public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
            updateMap();
        }

        @Override
        public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
            updateMap();
        }

        @Override
        public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
            updateMap();
        }

        @Override
        public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
            updateMap();
        }

        @Override
        public void visitTypeInsn(int opcode, String type) {
            updateMap();
        }

        @Override
        public void visitVarInsn(int opcode, int varIndex) {
            updateMap();
        }
    }
}
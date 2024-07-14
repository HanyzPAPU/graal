package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;

public class FrameMapAnalyzer extends MethodVisitor  {

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

    private class FrameTracer extends InstructionVisitor {

        public AnalyzerAdapter analyzer = null;

        private Map<String, List<Integer>> map = null;

        public FrameTracer(int api) {
            super(api);
        }

        public Map<String, List<Integer>> getMap() {
            return this.map;
        }

        public int getInstructionCount() {
            return iindex();
        }

        private String getCurrentSignature() {
            String stackSignature = analyzer.stack == null ? "NULL" : analyzer.stack.toString();
            String localsSignature = analyzer.locals == null ? "NULL" : analyzer.locals.toString();
            return stackSignature + "<#$!>" + localsSignature;
        }

        private void updateMap() {
            String signature = getCurrentSignature();
            map.computeIfAbsent(signature, k -> new ArrayList<>()).add(iindex());
        }

        @Override
        public void visitCode() {
            super.visitCode();
            // Prefer creating a new Map instead of clearing in case anybody stored a reference
            map = new LinkedHashMap<>();
        }

        @Override
        public void visitInstruction() {
            updateMap();
        }
        
    }
}
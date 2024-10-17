package jdk.graal.compiler.test.bytecodefuzz;

import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.graal.compiler.java.BytecodeParser.BytecodeParserError;

import java.lang.reflect.Method;
import java.util.Arrays;

public class FuzzTarget extends GraalCompilerTest {

    private class TestExecution {
        String methodName = null;

        private ResolvedJavaMethod getMethod(Class<?> clazz) throws Exception {
            if (methodName != null) {
                return FuzzTarget.this.asResolvedJavaMethod(clazz.getMethod(methodName));
            }

            Method[] methods = clazz.getMethods();
            if (methods.length == 0) {
                methods = clazz.getDeclaredMethods();
                if (methods.length == 0) {
                    throw new RuntimeException("Class does not contain any methods!");
                }
            }
            
            // For now select the first method without formal parameters
            Method chosenMethod = Arrays.stream(methods)
                .filter(m -> m.getParameterCount() == 0)
                .findFirst()
                .get();

            methodName = chosenMethod.getName();
            return FuzzTarget.this.asResolvedJavaMethod(chosenMethod);
        }

        private Object getReceiver(ResolvedJavaMethod method, Class<?> clazz) throws Exception {
            return method.isStatic() ? null : clazz.getConstructor().newInstance();
        }

        private Result executeExpected(byte[] bytes) {
            try {
                ByteClassLoader loader = new ByteClassLoader();
                Class<?> clazz = loader.LoadFromBytes(null, bytes);
                //System.err.print(clazz.getName());
                ResolvedJavaMethod method = getMethod(clazz);
                Object receiver = getReceiver(method, clazz);
                return FuzzTarget.this.executeExpected(method, receiver);
            }
            catch (Throwable e) {
                // If class loading or reference interpreter fail, do not continue
                System.err.println("[ERROR] " + e);
                return null;
            }
        }

        private void testAgainstExpected(byte[] bytes, Result expect) throws Exception {
            ByteClassLoader loader = new ByteClassLoader();
            Class<?> clazz = loader.LoadFromBytes(null, bytes);
            loader.ResolveClass(clazz);
            ResolvedJavaMethod method = getMethod(clazz);
            Object receiver = getReceiver(method, clazz);
            FuzzTarget.this.testAgainstExpected(getInitialOptions(), method, expect, receiver);
            //System.err.println("[DONE]");
        }

        public void testCompile(byte[] bytes) {
            ResolvedJavaMethod method;
            try {
                ByteClassLoader loader = new ByteClassLoader();
                Class<?> clazz = loader.LoadFromBytes(null, bytes);
                method = getMethod(clazz);
            }
            catch (Throwable e) {
                // If class loading fails, do not continue
                System.err.println("[ERROR] " + e);
                return;
            }

            try {
                getCode(method, getInitialOptions());
            }
            catch (BytecodeParserError e) {
                // Ignore parser errors
            }
        }

        Result test(byte[] bytes) throws Exception {
            Result expect = executeExpected(bytes);
            if (expect != null && getCodeCache() != null) {
                testAgainstExpected(bytes, expect);
            }
            return expect;
        }
    }

    private final boolean compileOnly;

    private FuzzTarget(boolean compileOnly) {
        this.compileOnly = compileOnly;
    }

    void testBytecode(byte[] bytes) throws Exception{
        var testExecution = new TestExecution();
        if (compileOnly) {
            testExecution.testCompile(bytes);
        }
        else {
            testExecution.test(bytes);
        }
    }

    static FuzzTarget instance;

    static boolean getCompileOnlyProperty() {
        String property = System.getProperty("fuzzCompileOnly");
        if (property == null) {
            return true;
        }
        if (property.equals("true")) {
            return true;
        }
        if (property.equals("false")) {
            return false;
        }
        System.err.println("Wrong format for property fuzzCompileOnly: " + property);
        return true;
    }

    public static void fuzzerInitialize(){
        // TODO: don't init in reproducer
        MutatorHarness.InitMutator();
        instance = new FuzzTarget(getCompileOnlyProperty());
    }

    public static void fuzzerTestOneInput(byte[] input) throws Exception {
        instance.testBytecode(input);
    }
}

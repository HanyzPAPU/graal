package jdk.graal.compiler.test.bytecodefuzz;

import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.meta.ResolvedJavaMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class FuzzTarget extends GraalCompilerTest {

    void testBytecode(byte[] bytes) throws Exception {

        // For now, method name is fixed, but it could be easily extracted by reflection
        
        ResolvedJavaMethod method;
        Object reciever;
        ByteClassLoader loader = new ByteClassLoader();

        try {
            Class<?> clazz = loader.LoadFromBytes(null, bytes);

            System.out.print(clazz.getName());

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

            method = asResolvedJavaMethod(chosenMethod);
            reciever = method.isStatic() ? null : clazz.getConstructor().newInstance();
        }
        // Don't crash on reflection/loading errors
        catch (Throwable ignored) {
            System.out.println(" [Error] " + ignored);
            return;
        }

        try {
            test(method, reciever);
        }
        // Don't crash on errors in <clinit>
        catch(java.lang.ExceptionInInitializerError ignored) {
            System.out.println(" [Error in initializer!]");
            return;
        }
        
        System.out.println(" [Done]");
    }

    static FuzzTarget instance;

    public static void fuzzerInitialize(){
        // TODO: don't init in reproducer
        MutatorHarness.InitMutator();
        instance = new FuzzTarget();
    }

    public static void fuzzerTestOneInput(byte[] input) throws Exception {
        instance.testBytecode(input);
    }
}

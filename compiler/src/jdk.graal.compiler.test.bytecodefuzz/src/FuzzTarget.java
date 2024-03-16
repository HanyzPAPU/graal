package jdk.graal.compiler.test.bytecodefuzz;

import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.meta.ResolvedJavaMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FuzzTarget extends GraalCompilerTest {

    void testBytecode(byte[] bytes) throws Exception {

        // For now, method name is fixed, but it could be easily extracted by reflection
        
        ResolvedJavaMethod method;
        Object reciever;
        ByteClassLoader loader = new ByteClassLoader();

        try {
            Class<?> hello = loader.LoadFromBytes(null, bytes);

            var methods = hello.getMethods();
            if (methods.length == 0) {
                methods = hello.getDeclaredMethods();
                if (methods.length == 0) {
                    throw new RuntimeException("Class does not contain any methods!");
                }
            }
            
            // For now select the first method
            method = asResolvedJavaMethod(methods[0]);
            
            reciever = method.isStatic() ? null : hello.getConstructor().newInstance();
        }
        // Don't crash on reflection/loading errors
        catch (Exception ingored) {
            return;
        }
        catch (java.lang.ClassFormatError ignored){
            return;
        }
        catch (java.lang.NoClassDefFoundError ignored){
            return;
        }

        test(method, reciever);
    }

    static FuzzTarget instance;

    public static void fuzzerInitialize(){
        MutatorHarness.InitMutator();
        instance = new FuzzTarget();
    }

    public static void fuzzerTestOneInput(byte[] input) throws Exception {
        instance.testBytecode(input);
    }
}

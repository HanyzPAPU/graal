package jdk.graal.compiler.core.test.bytecodefuzz;

import jdk.graal.compiler.core.test.GraalCompilerTest;
import jdk.vm.ci.meta.ResolvedJavaMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.junit.Test;

public class FuzzSandbox extends GraalCompilerTest {

    void testBytecode(byte[] bytes) throws Exception {

        // For now, method name is fixed, but it could be easily extracted by reflection
        
        ResolvedJavaMethod method;
        Object reciever;
        ByteClassLoader loader = new ByteClassLoader();

        try {
            Class<?> hello = loader.LoadFromBytes(null, bytes);
            method = getResolvedJavaMethod(hello, "hello");
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

    static FuzzSandbox instance;

    public static void fuzzerInitialize(){
        MutatorHarness.initMutator();
        instance = new FuzzSandbox();
    }

    public static void fuzzerTestOneInput(byte[] input) throws Exception {
        instance.testBytecode(input);
    }

    @Test
    public void entry() throws Exception {

        Path path = Paths.get("/home/honza/graal/compiler/src/jdk.graal.compiler.test/src/jdk/graal/compiler/core/test/bytecodefuzz/Hello.class");
        byte[] data = Files.readAllBytes(path);
 
        testBytecode(data);
    }
}

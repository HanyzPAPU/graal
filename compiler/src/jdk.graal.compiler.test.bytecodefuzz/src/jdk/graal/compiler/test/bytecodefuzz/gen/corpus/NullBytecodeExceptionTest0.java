package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NullBytecodeExceptionTest0 {
    public static Object obj = (Object) null;

    public static void nullSnippet()  {
        obj.toString();
    }
}
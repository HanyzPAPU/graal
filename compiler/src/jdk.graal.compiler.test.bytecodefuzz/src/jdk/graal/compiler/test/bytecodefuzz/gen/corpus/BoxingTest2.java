package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class BoxingTest2 {
    public static int arg = -5;

    public static Object boxSnippet()  {
        return arg;
    }
}
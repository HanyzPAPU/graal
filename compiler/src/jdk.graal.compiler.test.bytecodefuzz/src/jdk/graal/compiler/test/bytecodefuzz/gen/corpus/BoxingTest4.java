package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class BoxingTest4 {
    public static int arg = -300;

    public static Object boxSnippet()  {
        return arg;
    }
}
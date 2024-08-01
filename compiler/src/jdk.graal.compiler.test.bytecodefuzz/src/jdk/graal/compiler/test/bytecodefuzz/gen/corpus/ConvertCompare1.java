package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare1 {
    public static int x = 42;

    public static boolean testChar42()  {
        return ((char) x) == 42;
    }
}
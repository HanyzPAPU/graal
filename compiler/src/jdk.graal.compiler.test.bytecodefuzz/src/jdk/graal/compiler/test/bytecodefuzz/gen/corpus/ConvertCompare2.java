package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare2 {
    public static int x = (int) Character.MAX_VALUE;

    public static boolean testChar42()  {
        return ((char) x) == 42;
    }
}
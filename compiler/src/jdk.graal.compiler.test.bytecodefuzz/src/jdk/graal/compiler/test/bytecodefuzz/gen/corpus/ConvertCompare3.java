package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare3 {
    public static int x = 42;

    public static boolean testCharMax()  {
        return ((char) x) == Character.MAX_VALUE;
    }
}
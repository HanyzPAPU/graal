package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare4 {
    public static int x = (int) Character.MAX_VALUE;

    public static boolean testCharMax()  {
        return ((char) x) == Character.MAX_VALUE;
    }
}
package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_intern032 {
    private static int i = 2;

    public static boolean test()  {
        return ("id" + i).intern().equals("id" + i);
    }
}
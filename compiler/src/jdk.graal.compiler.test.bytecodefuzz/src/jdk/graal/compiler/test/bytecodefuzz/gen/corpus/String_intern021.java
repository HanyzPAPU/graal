package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_intern021 {
    private static int i = 1;

    public static boolean test()  {
        return ("id" + i).intern() == ("id" + i).intern();
    }
}
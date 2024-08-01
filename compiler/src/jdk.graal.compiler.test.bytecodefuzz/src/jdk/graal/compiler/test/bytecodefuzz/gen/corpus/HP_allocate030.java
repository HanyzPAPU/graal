package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_allocate030 {
    public static int count = 100;

    public static int test()  {
        @SuppressWarnings("unused")
        final int sum = 0;
        String text = "";
        for (int i = 0; i < count; i++) {
            text += '.';
        }
        return text.length();
    }
}
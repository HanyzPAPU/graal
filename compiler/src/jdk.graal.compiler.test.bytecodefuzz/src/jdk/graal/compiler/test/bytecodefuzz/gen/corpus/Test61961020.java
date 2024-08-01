package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Test61961020 {
    

    public static String test()  {
        int i1 = 0;
        int i2 = Integer.MAX_VALUE;

        while (i1 >= 0) {
            i1++;
            if (i1 > i2) {
                return "E R R O R: " + i1;
            }
        }
        return "ok";
    }
}
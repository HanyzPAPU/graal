package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class DeadCode015 {
    public static int a = 6;

    public static int test()  {
        int arg = a;
        int p = arg;
        if (p > 2) {
            p += 1;
            arg += 10;
        } else {
            p += 2;
            arg += 20;
            if (p > 3) {
                p += 1;
                arg += 10;
                if (p > 4) {
                    p += 1;
                    arg += 10;
                } else {
                    p += 2;
                    arg += 20;
                }
            } else {
                p += 2;
                arg += 20;
            }
        }
        return p;
    }
}
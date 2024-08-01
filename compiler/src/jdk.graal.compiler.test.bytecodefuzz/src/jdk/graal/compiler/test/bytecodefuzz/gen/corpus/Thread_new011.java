package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Thread_new011 {
    private static int i = 1;

    public static boolean test()  {
        if (i == 0) {
            return new Thread() != null;
        }
        if (i == 1) {
            return new Thread("Thread_new01") != null;
        }
        if (i == 2) {
            return new Thread(new Thread()) != null;
        }
        if (i == 3) {
            return new Thread(new Thread(), "Thread_new01") != null;
        }
        return false;
    }
}
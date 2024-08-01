package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_forName012 {
    public static int i = 2;

    public static String test()  throws ClassNotFoundException {
        if (i == 0) {
            return Class.forName("java.lang.Object").toString();
        }
        if (i == 1) {
            return Class.forName("java.lang.String").toString();
        }
        if (i == 2) {
            return Class.forName("jdk.graal.compiler.jtt.lang.Class_forName01").toString();
        }
        if (i == 3) {
            return Class.forName("xyxzz.xyzyzyz.XXYYY").toString();
        }
        return null;
    }
}
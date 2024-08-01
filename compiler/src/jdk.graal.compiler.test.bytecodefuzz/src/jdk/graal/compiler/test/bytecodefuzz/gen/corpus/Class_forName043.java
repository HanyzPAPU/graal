package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_forName043 {
    public static int i = 3;

    public static String test()  throws ClassNotFoundException {
        String clname = null;
        if (i == 0) {
            clname = "java.lang.Object[]";
        } else if (i == 1) {
            clname = "[Ljava.lang.String;";
        } else if (i == 2) {
            clname = "[Ljava/lang/String;";
        } else if (i == 3) {
            clname = "[I";
        } else if (i == 4) {
            clname = "[java.lang.Object;";
        }
        if (clname != null) {
            return Class.forName(clname).toString();
        }
        return null;
    }
}
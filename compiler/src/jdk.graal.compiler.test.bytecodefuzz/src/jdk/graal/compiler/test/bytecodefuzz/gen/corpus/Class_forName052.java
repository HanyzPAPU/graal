package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_forName052 {
    public static int i = 5;

    public static String test()  throws ClassNotFoundException {
        final URLClassLoader classLoader = new URLClassLoader(new URL[0], String.class.getClassLoader());
        if (i == 0) {
            return Class.forName("java.lang.String", false, classLoader).toString();
        } else if (i == 1) {
            return Class.forName("[Ljava.lang.String;", false, classLoader).toString();
        }
        return null;
    }
}
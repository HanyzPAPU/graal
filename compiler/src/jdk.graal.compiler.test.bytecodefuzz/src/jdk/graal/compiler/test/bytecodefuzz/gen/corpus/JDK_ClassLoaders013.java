package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class JDK_ClassLoaders013 {
    public static int i = 3;

    public static boolean test()  {
        if (i == 0) {
            return Object.class.getClassLoader() == null;
        }
        if (i == 1) {
            return Class.class.getClassLoader() == null;
        }
        if (i == 2) {
            return String.class.getClassLoader() == null;
        }
        if (i == 3) {
            return Thread.class.getClassLoader() == null;
        }
        if (i == 4) {
            return System.class.getClassLoader() == null;
        }
        if (i == 5) {
            return ClassLoader.class.getClassLoader() == null;
        }
        return false;
    }
}
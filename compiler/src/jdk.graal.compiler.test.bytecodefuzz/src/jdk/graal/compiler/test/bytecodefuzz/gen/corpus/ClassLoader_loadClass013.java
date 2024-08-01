package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class ClassLoader_loadClass013 {
    public static int i = 5;

    public static String test()  throws ClassNotFoundException {
        final URLClassLoader classLoader = new URLClassLoader(new URL[0], String.class.getClassLoader());
        if (i == 0) {
            return classLoader.loadClass("java.lang.String").toString();
        } else if (i == 1) {
            return classLoader.loadClass("[Ljava.lang.String;").toString();
        } else if (i == 2) {
            return classLoader.loadClass("java.lang.String[]").toString();
        }
        return null;
    }
}
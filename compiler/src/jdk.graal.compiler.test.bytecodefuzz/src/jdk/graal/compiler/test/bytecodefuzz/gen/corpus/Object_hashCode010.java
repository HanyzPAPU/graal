package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Object_hashCode010 {
    

    public static boolean test()  {
        final Object o1 = new Object();
        final Object o2 = new Object();
        return o1.hashCode() != 0 || o2.hashCode() != 0;
    }
}
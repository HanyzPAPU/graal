package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Thread_setName0 {
    public static String name = "abc";

    public static String test()  {
        String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(name);
        String name2 = Thread.currentThread().getName();
        Thread.currentThread().setName(oldName);
        return name2;
    }
}
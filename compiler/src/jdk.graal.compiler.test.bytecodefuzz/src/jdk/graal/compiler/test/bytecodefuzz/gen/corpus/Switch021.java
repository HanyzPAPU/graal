package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Switch021 {
    public static int arg = 1;

    public static int test()  {
        switch (arg) {
            case 1:
                return 2;
            default:
                return 1;
        }
    }
}
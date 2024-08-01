package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_intern010 {
    

    public static boolean test()  {
        // Checkstyle: stop
        return "id".intern() == "id";
        // Checkstyle: resume
    }
}
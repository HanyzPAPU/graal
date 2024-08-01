package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class CharacterBits3 {
    private static char o = (char) 1;

    public static char test()  {
        return Character.reverseBytes(o);
    }
}
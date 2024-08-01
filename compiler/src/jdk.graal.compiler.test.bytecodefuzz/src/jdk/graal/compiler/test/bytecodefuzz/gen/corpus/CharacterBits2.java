package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class CharacterBits2 {
    private static char o = (char) 0;

    public static char test()  {
        return Character.reverseBytes(o);
    }
}
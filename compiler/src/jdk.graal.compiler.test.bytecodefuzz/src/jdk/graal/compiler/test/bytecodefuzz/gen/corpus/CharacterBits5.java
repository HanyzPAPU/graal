package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class CharacterBits5 {
    private static char o = (char) 0x00ff;

    public static char test()  {
        return Character.reverseBytes(o);
    }
}
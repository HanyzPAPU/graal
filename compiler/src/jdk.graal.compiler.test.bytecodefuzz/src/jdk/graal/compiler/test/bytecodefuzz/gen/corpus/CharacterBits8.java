package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class CharacterBits8 {
    public static char o = (char) 0x3fff;

    public static char test()  {
        return Character.reverseBytes(o);
    }
}
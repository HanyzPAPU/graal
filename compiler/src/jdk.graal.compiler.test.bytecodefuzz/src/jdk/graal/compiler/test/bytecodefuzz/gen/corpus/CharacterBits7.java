package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class CharacterBits7 {
    public static char o = (char) 0xffff;

    public static char test()  {
        return Character.reverseBytes(o);
    }
}
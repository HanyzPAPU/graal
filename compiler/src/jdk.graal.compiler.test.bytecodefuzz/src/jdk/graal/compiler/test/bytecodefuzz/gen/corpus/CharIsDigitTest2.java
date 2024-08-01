package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class CharIsDigitTest2 {
    public static char ch = '\ubeef';

    public boolean isDigit()  {
        return Character.isDigit(ch);
    }
}
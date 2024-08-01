package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class CharIsDigitTest1 {
    private static char ch = 'a';

    public boolean isDigit()  {
        return Character.isDigit(ch);
    }
}
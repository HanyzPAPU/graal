package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NarrowingReadTest2 {
    private static Long l = Long.valueOf(Integer.MAX_VALUE);

    public int testNarrowReadSnippetInt()  {
        return (int) l.longValue();
    }
}
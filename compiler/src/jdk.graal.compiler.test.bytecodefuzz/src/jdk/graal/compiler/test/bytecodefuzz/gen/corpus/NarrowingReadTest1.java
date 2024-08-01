package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NarrowingReadTest1 {
    private static Long l = Long.valueOf(Short.MAX_VALUE);

    public short testNarrowReadSnippetShort()  {
        return (short) l.longValue();
    }
}
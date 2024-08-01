package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NarrowingReadTest3 {
    private static Long l = Long.valueOf(Integer.MAX_VALUE);

    public long testNarrowReadSnippetIntToLong()  {
        return (int) l.longValue();
    }
}
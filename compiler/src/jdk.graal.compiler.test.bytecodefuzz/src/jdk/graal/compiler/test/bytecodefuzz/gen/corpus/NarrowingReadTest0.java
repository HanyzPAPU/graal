package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class NarrowingReadTest0 {
    public static Long l = Long.valueOf(Byte.MAX_VALUE);

    public byte testNarrowReadSnippetByte()  {
        return (byte) l.longValue();
    }
}
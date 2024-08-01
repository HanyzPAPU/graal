package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class Adler32SubstitutionsTest1 {
    public static byte[] input = "some string".getBytes();
    public static int offset = 0;
    public static int length = 12;

    public static long updateBytes()  {
        Adler32 adler = new Adler32();
        adler.update(input, offset, length);
        return adler.getValue();
    }
}
package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class CRC32SubstitutionsTest0 {
    private static byte[] input = "some string".getBytes();

    public static long update()  {
        CRC32 crc = new CRC32();
        for (byte b : input) {
            crc.update(b);
        }
        return crc.getValue();
    }
}
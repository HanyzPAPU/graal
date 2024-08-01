package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class Adler32SubstitutionsTest0 {
    private static byte[] input = "some string".getBytes();

    public static long update()  {
        Adler32 adler = new Adler32();
        for (byte b : input) {
            adler.update(b);
        }
        return adler.getValue();
    }
}
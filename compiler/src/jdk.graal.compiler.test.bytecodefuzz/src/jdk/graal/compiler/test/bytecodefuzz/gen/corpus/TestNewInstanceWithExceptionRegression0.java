package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import jdk.graal.compiler.core.test.SubprocessTest;
import jdk.graal.compiler.debug.GraalError;
import jdk.graal.compiler.debug.TTY;
import jdk.graal.compiler.test.SubprocessUtil;
public class TestNewInstanceWithExceptionRegression0 {
    

    public void snippet01()  {
        try {
            byte[] a = new byte[123];
            byte[] b = new byte[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
        }
    }
}
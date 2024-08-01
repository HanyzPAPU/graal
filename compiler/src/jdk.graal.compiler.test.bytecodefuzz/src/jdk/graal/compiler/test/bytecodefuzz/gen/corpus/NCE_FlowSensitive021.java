package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class NCE_FlowSensitive021 {
    public static String arg = "x";

    public static String test()  {
        if (arg != null) {
            return arg.toString();
        }
        return arg.toString();
    }
}
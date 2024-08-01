package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class NCE_FlowSensitive012 {
    private static String arg = "yay";

    public static String test()  {
        if (arg != null) {
            return arg.toString();
        }
        return null;
    }
}
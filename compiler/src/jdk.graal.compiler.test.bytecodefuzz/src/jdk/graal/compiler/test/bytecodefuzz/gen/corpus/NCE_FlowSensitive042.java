package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class NCE_FlowSensitive042 {
    public static String arg2 = "yay";

    public static String test()  {
        String arg = arg2;
        if (arg == null) {
            arg = "null";
        }
        // arg cannot be null here
        return arg.toString();
    }
}
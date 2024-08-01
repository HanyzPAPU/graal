package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class NCE_FlowSensitive032 {
    public static String arg = "yay";

    public static String test()  {
        if ("x".equals(arg)) {
            if (arg == null) {
                return "null";
            }
        } else {
            if (arg == null) {
                return "null";
            }
        }
        // arg cannot be null here
        return arg.toString();
    }
}
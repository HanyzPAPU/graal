package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import static jdk.graal.compiler.api.directives.GraalDirectives.injectBranchProbability;
import org.junit.Test;
public class IfCanonicalizerSwapTest0 {
    private static long value = -1L;

    public static String testSnippet1()  {
        if (injectBranchProbability(0.50, value >= 0L) && injectBranchProbability(0.00, value <= 35L)) {
            return "JustRight";
        } else {
            if (injectBranchProbability(0.50, value > 35L)) {
                return "TooHot";
            } else {
                return "TooCold";
            }
        }
    }
}
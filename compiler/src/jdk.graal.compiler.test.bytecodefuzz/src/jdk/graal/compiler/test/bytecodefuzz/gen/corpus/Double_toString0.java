package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Double_toString0 {
    

    public static String test()  {
        double z1 = 0.4363485526704198;
        double z2 = -0.43536514763046896;
        double z3 = z1 + z2;
        return Double.toString(z3);
    }
}
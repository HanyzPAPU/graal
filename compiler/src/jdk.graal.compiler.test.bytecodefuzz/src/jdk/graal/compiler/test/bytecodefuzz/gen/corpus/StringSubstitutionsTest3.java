package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.Invoke;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.replacements.nodes.ArrayEqualsNode;
import org.junit.Test;
import jdk.vm.ci.code.InstalledCode;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class StringSubstitutionsTest3 {
    

    public int indexOfConstantUTF16case3()  {
        int index = ("grga " + ((char) 0x100) + "varak").indexOf(((char) 0x10D) + "varak");
        return index;
    }
}
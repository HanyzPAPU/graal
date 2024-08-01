package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.api.replacements.Snippet;
import jdk.graal.compiler.debug.DebugContext;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.StructuredGraph.Builder;
import jdk.graal.compiler.phases.PhaseSuite;
import jdk.graal.compiler.phases.tiers.HighTierContext;
import jdk.graal.compiler.word.Word;
import org.graalvm.word.Pointer;
import org.graalvm.word.UnsignedWord;
import org.graalvm.word.WordBase;
import org.graalvm.word.WordFactory;
import org.junit.Test;
public class WordTest30 {
    private static long input = 1234L;

    public static long cast()  {
        WordBase base = WordFactory.signed(input);
        UnsignedWord unsigned = (UnsignedWord) base;
        Pointer pointer = (Pointer) unsigned;
        Word word = (Word) pointer;
        return word.rawValue();
    }
}
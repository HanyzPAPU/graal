package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
public class CheckedListTest1 {
    

    public static void testCopyOf()  {
        Object[] mixed = new Object[]{"a", "b", 18};
        try {
            Arrays.copyOf(mixed, 4, String[].class);
        } catch (ArrayStoreException e) {
            return;
        }
        throw new RuntimeException("should not reach here");
    }
}
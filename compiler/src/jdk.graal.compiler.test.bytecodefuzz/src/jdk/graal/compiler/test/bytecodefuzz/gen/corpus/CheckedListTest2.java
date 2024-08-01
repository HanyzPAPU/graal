package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
public class CheckedListTest2 {
    

    public static void testArraycopy()  {
        Object[] mixed = new Object[]{"a", "b", 18};
        try {
            String[] strings = new String[4];
            System.arraycopy(mixed, 0, strings, 0, 3);
        } catch (ArrayStoreException e) {
            return;
        }
        throw new RuntimeException("should not reach here");
    }
}
package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import java.lang.reflect.Field;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class InsertArithmeticMutation extends AbstractMutation {


    // Should this be an ancestor of insert neutral op? or a sibling?
    // aren't there like operations that do not have a neutral element?

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        // select from iindeces with primitive type on TOS (String?)
        // binop / unop -> opt push, insert op

        return null;
    }
}
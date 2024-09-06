package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.test.bytecodefuzz.Pair;

public class InsertSwapMutation extends AbstractMutation {

    private static final Pattern topTwoSimpleTypesOnTosPattern = Pattern.compile("\\[(.*, )*(?<simple1>[^,]*), (?<simple2>[^,]*)\\]");
    private static final Pattern topTwoWideTypesOnTosPattern = Pattern.compile("\\[(.*, )*(?<wide1>[^,]*), "+Opcodes.TOP+", (?<wide2>[^,]*), "+Opcodes.TOP+"\\]");

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        
        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc, false);
        mn.accept(frameMapAnalyzer);

        List<Pair<Integer, Boolean>> validProgramPoints = frameMapAnalyzer.getMap().entrySet().stream()
            .flatMap(e -> {
                Matcher wideMatcher = topTwoWideTypesOnTosPattern.matcher(e.getKey());

                if (wideMatcher.matches()) {
                    String wide1 = wideMatcher.group("wide1");
                    String wide2 = wideMatcher.group("wide2");
                    
                    if (wide1.equals(wide2)) {
                        return e.getValue().stream().map(i -> new Pair<>(i, false));
                    }
                }

                Matcher simpleMatcher = topTwoSimpleTypesOnTosPattern.matcher(e.getKey());
                if (simpleMatcher.matches()) {
                    String simple1 = simpleMatcher.group("simple1");
                    String simple2 = simpleMatcher.group("simple2");
                    
                    if (simple1.equals(simple2)) {
                        return e.getValue().stream().map(i -> new Pair<>(i, true));
                    }
                    
                }
                return Stream.empty();
            })
            .collect(Collectors.toList());

        if (validProgramPoints.isEmpty()) {
            throw new MutationFailedException("Insert swap mutation called on a method without two same types on TOS");
        }
        
        Pair<Integer, Boolean> target = prng.pickIn(validProgramPoints);
        int targetIindex = target.first;
        boolean isSimple = target.second;

        return mv -> new InstructionVisitor(Opcodes.ASM9, mv) {
            @Override
            public void visitInstruction() {
                if (iindex() == targetIindex) {
                    if (isSimple) {
                        mv.visitInsn(Opcodes.SWAP);
                    }
                    else {
                        // long2, long1 -> long1, long2, long1
                        mv.visitInsn(Opcodes.DUP2_X2);
                        // long1, long2, long1 -> long1, long2
                        mv.visitInsn(Opcodes.POP2);
                    }
                }
            }
        };
    }
}
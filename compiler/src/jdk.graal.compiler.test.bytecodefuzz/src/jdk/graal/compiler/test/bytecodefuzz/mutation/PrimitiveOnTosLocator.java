package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import jdk.graal.compiler.test.bytecodefuzz.Pair;

public class PrimitiveOnTosLocator {

    private static final Pattern primitiveOnTosPattern = Pattern.compile("\\[(.*, )*((?<type1>" + Opcodes.INTEGER + "|" + Opcodes.FLOAT + ")|((?<type2>"+ Opcodes.LONG + "|" + Opcodes.DOUBLE + "), " + Opcodes.TOP +")|(?<string>"+ AsmTypeSupport.stringType.getInternalName() +"))\\]");

    public static List<Pair<Integer, Type>> getLocations(ClassNode cn, MethodNode mn) {
        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc, false);
        mn.accept(frameMapAnalyzer);

        return frameMapAnalyzer.getMap().entrySet().stream()
            .flatMap(e -> {
                Matcher matcher = primitiveOnTosPattern.matcher(e.getKey());
                if (matcher.matches()) {
                    String type1 = matcher.group("type1");
                    String type2 = matcher.group("type2");
                    String stringType = matcher.group("string");

                    if (stringType == null) {
                        Type type = AsmTypeSupport.getType(Integer.parseInt(type1 != null ? type1 : type2));
                        return e.getValue().stream().map(iindex -> new Pair<Integer, Type>(iindex, type));
                    }
                    else {
                        return e.getValue().stream().map(iindex -> new Pair<Integer, Type>(iindex, AsmTypeSupport.stringType));
                    }
                }
                else {
                    return Stream.empty();
                }
            })
            .collect(Collectors.toList());
    }
}
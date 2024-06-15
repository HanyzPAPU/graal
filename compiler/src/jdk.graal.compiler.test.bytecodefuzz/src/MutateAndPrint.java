package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Exception;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.StringWriter;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

public class MutateAndPrint {


    public static void main(String[] args) throws Exception {

        int maxSize = 500;
        int seed = 42;
        String filename = args[1] + ".class";

        byte[] data = Files.readAllBytes(Path.of(".", "precorpus", filename));

        dumpBytecode(data);

        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(0);
        FreeSpace freeSpace = new FreeSpace(maxSize - data.length);
        MutateConstantClassVisitor mutateConstants = new MutateConstantClassVisitor(Opcodes.ASM9, writer, seed, true, freeSpace);

        reader.accept(mutateConstants, 0);
        byte[] result = writer.toByteArray();
        
        assert(freeSpace.Amount() == maxSize - result.length);

        dumpBytecode(result);
    }

    private static void dumpBytecode(byte[] bytecode) {
        ClassReader reader = new ClassReader(bytecode);
        StringWriter sw = new StringWriter();
        TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(sw));
        reader.accept(visitor, 0);
        System.err.println(sw.toString());
    }
}
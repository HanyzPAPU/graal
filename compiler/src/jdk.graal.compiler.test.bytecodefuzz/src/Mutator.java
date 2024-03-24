package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Exception;
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


public final class Mutator {

    public byte[] Mutate(byte[] data, int maxSize, int seed) throws Exception {

        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(0);
        MutateConstantClassVisitor mutateConstants = new MutateConstantClassVisitor(Opcodes.ASM9, writer, seed);

        reader.accept(mutateConstants, 0);
        byte[] result = writer.toByteArray();

        if (result.length > maxSize) {
            dumpBytecode(result);            
            // TODO: change into more appropriate exception type
            throw new Exception(String.format("Generated Classfile that is too long! Length %d > Max %d", result.length, maxSize));
        }

        return result;
    }

    public void Init(int seed){
        return;
    }

    private void dumpBytecode(byte[] bytecode) {
        ClassReader reader = new ClassReader(bytecode);
        StringWriter sw = new StringWriter();
        TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(sw));
        reader.accept(visitor, 0);
        System.err.println(sw.toString());
    }
}

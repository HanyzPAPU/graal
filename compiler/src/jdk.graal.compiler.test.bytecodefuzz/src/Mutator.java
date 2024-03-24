package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Exception;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

public final class Mutator {

    public byte[] Mutate(byte[] data, int maxSize, int seed) throws Exception {

        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(0);
        MutateConstantClassVisitor mutateConstants = new MutateConstantClassVisitor(Opcodes.ASM9, writer, seed);

        byte[] result;
        int maxIters = 10;
        int iter = 0;

        do {
            reader.accept(mutateConstants, ClassReader.EXPAND_FRAMES);
            result = writer.toByteArray();
            iter++;
            if (iter > maxIters) {
                // TODO: think of what to do!
                break;
            }
        } while (result.length > maxSize);
        

        if (result.length > maxSize) {
            // TODO: change into more appropriate exception type
            throw new Exception(String.format("Generated Classfile that is too long! Length %d > Max %d", result.length, maxSize));
        }

        return result;
    }

    public void Init(int seed){
        return;
    }
}

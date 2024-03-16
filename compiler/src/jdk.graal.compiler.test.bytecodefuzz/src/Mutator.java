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
        ChangeStringClassVisitor mutateString = new ChangeStringClassVisitor(Opcodes.ASM9, writer, seed);

        reader.accept(mutateString, ClassReader.EXPAND_FRAMES);

        byte[] result = writer.toByteArray();

        if (result.length > maxSize) {
            // TODO: change into more appropriate exception type
            throw new Exception("Generated Classfile that is too long!");
        }

        return result;
    }

    public void Init(int seed){
        return;
    }
}

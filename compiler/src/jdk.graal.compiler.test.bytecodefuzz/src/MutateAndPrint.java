package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Exception;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.LocalVariablesSorter;


public class MutateAndPrint {


    public static void main(String[] args) throws Exception {

        int maxSize = 500;
        int seed = 42;
        String filename = args[1] + ".class";

        byte[] data = Files.readAllBytes(Path.of(".", "precorpus", filename));

        dumpBytecode(data);
        run(data);

        // ClassReader reader = new ClassReader(data);
        // ClassWriter writer = new ClassWriter(0);
        // FreeSpace freeSpace = new FreeSpace(maxSize - data.length);
        // MutateConstantClassVisitor mutateConstants = new MutateConstantClassVisitor(Opcodes.ASM9, writer, seed, true, freeSpace);

        // reader.accept(mutateConstants, 0);
        // byte[] result = writer.toByteArray();
        
        // assert(freeSpace.Amount() == maxSize - result.length);

        // dumpBytecode(result);
        // run(result);
    }

    private static void dumpBytecode(byte[] bytecode) {
        ClassReader reader = new ClassReader(bytecode);
        StringWriter sw = new StringWriter();

        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(sw));

        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9, traceClassVisitor) {

            String name;

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                this.name = name;
                this.cv.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                return new FrameMapAnalyzer(this.api, this.name, access, name, descriptor);
            }
        };

        
        reader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        System.err.println(sw.toString());
    }

    private static void run(byte[] bytecode) throws Exception {
        
        Object reciever;
        ByteClassLoader loader = new ByteClassLoader();
        Method method;

        try {
            Class<?> clazz = loader.LoadFromBytes(null, bytecode);

            var methods = clazz.getMethods();
            if (methods.length == 0) {
                methods = clazz.getDeclaredMethods();
                if (methods.length == 0) {
                    throw new RuntimeException("Class does not contain any methods!");
                }
            }
            
            // For now select the first method
            method = methods[0];
            
            reciever = (method.getModifiers() & Modifier.STATIC) != 0 ? null : clazz.getConstructor().newInstance();
        }
        // Don't crash on reflection/loading errors
        catch (Exception ingored) {
            return;
        }
        catch (java.lang.ClassFormatError ignored){
            return;
        }
        catch (java.lang.NoClassDefFoundError ignored){
            return;
        }

        Object res = method.invoke(reciever);

        System.out.println(res);
    }
}
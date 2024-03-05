package jdk.graal.compiler.core.test.bytecodefuzz;

import java.util.Random;
import java.lang.StringBuilder;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ChangeStringClassVisitor extends ClassVisitor {

    int seed;
    int api;

    public ChangeStringClassVisitor(int api, ClassVisitor classVisitor, int seed) {
        super(api, classVisitor);
        this.seed = seed;
        this.api = api;
        this.cv = classVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new ChangeStringMethodVisitor(api, this.cv.visitMethod(access, name, descriptor, signature, exceptions), seed);
    }
    
    class ChangeStringMethodVisitor extends MethodVisitor {

        Random rnd;
        static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#$%^&*()-_=+{}[]\\|;:/?.>,<";

        public ChangeStringMethodVisitor(int api, MethodVisitor methodVisitor, int seed) {
            super(api, methodVisitor);
            this.mv = methodVisitor;
            rnd = new Random(seed);
        }

        @Override
        public void visitLdcInsn(Object value){
            if (value instanceof String) {
                var builder = new StringBuilder((String) value);
                int index = rnd.nextInt(builder.length());
                char val = alphabet.charAt(rnd.nextInt(alphabet.length()));
                builder.setCharAt(index, val);
                String result = builder.toString();

                this.mv.visitLdcInsn(result);
            }
            else {
                this.mv.visitLdcInsn(value);
            }
        }

    }
}

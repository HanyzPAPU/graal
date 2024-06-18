package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Optional;
import java.lang.reflect.AnnotatedType;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.code_intelligence.jazzer.mutation.api.MutatorFactory;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import com.code_intelligence.jazzer.mutation.api.SerializingMutator;
import com.code_intelligence.jazzer.mutation.mutator.lang.LangMutators;
import com.code_intelligence.jazzer.mutation.support.TypeSupport;

import com.code_intelligence.jazzer.mutation.annotation.WithUtf8Length;
import com.code_intelligence.jazzer.mutation.support.TypeHolder;

public class MutateConstantClassVisitor extends ClassVisitor {

    PseudoRandom prng;
    int api;

    boolean ctorOnly;
    private static final String ctorName = "<init>";

    FreeSpace freeSpace;

    public MutateConstantClassVisitor(int api, ClassVisitor classVisitor, PseudoRandom prng, boolean ctorOnly, FreeSpace freeSpace) {
        super(api, classVisitor);
        this.prng = prng;
        this.api = api;
        this.cv = classVisitor;
        this.ctorOnly = ctorOnly;
        this.freeSpace = freeSpace;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (ctorOnly && !name.equals(ctorName)) {
            // Do not mutate non ctor methods when ctorOnly is true
            return this.cv.visitMethod(access, name, descriptor, signature, exceptions);
        }
        else {
            return new MutateConstantMethodVisitor(api, this.cv.visitMethod(access, name, descriptor, signature, exceptions));    
        }
    }
    
    class MutateConstantMethodVisitor extends MethodVisitor {

        MutatorFactory mutatorFactory = LangMutators.newFactory();

        public MutateConstantMethodVisitor(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
            this.mv = methodVisitor;
        }

        <T> T mutateOrThrow(Object value, Class<T> clazz) throws Exception {
            AnnotatedType annotatedType = TypeSupport.asAnnotatedType(clazz);

            @SuppressWarnings("unchecked") // This method will throw before the cast would be unsuccesfull
            SerializingMutator<T> mutator = (SerializingMutator<T>)mutatorFactory.createOrThrow(annotatedType);

            return mutator.mutate(clazz.cast(value), prng);    
        }

        // This function calculates the number of bytes needed to store the given string in a classfile
        int modifiedUTF8Len(String s) {

            // Classfiles use a modified UTF-8 encoding for some reason
            // which stores the data in a different way than `s.getBytes(StandardCharsets.UTF_8)`

            int res = 0;
            int i = 0;

            while (i < s.length()) {
                int codePoint = s.codePointAt(i);

                if (codePoint >= 1 && codePoint <= 0x7F) {
                    res += 1;
                    i += 1;
                }
                else if (codePoint == 0 || (codePoint >= 0x80 && codePoint <= 0x7FF)) {
                    res += 2;
                    i += 1;
                }
                else if (codePoint >= 0x800 && codePoint <= 0xFFFF) {
                    res += 3;
                    i += 1;
                }
                else {
                    res += 6;
                    i += 2;
                }
            }
            return res;
        }

        @Override
        public void visitLdcInsn(Object value){
            try {
                Object mutant;
                if (value instanceof String s) {

                    

                    int originalUTF8Length = s.getBytes(StandardCharsets.UTF_8).length;
                    int maxUTF8Length = originalUTF8Length + freeSpace.amount();
                    int originalLength = modifiedUTF8Len(s);
                    int maxLength = originalLength + freeSpace.amount();
                    
                    AnnotatedType annotatedType = TypeSupport.asAnnotatedType(String.class);
                    annotatedType = JazzerTypeSupport.WithUtf8Length(annotatedType, 0, maxUTF8Length);

                    @SuppressWarnings("unchecked") // This method will throw before the cast would be unsuccesfull
                    SerializingMutator<String> mutator = (SerializingMutator<String>)mutatorFactory.createOrThrow(annotatedType);

                    String mutated = mutator.mutate(s, prng);
                    int mutatedLength;

                    while ((mutatedLength = modifiedUTF8Len(mutated)) > maxLength) {
                        // Remove first character
                        mutated = mutated.substring(1);
                    }

                    mutant = mutated;
                    freeSpace.add(originalLength - mutatedLength);
                }
                else {
                    mutant = mutateOrThrow(value, value.getClass());
                }

                if (mutant == null) {
                    this.mv.visitInsn(Opcodes.ACONST_NULL);
                }
                else {
                    this.mv.visitLdcInsn(mutant);
                }
                
            }
            catch (Exception e) {
                this.mv.visitLdcInsn(value);
            }
        }

        //? Should we keep the type or should we allow to make the constants bigger?
        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (opcode == Opcodes.BIPUSH) {

                AnnotatedType annotatedType = TypeSupport.asAnnotatedType(Byte.class);
                annotatedType = TypeSupport.notNull(annotatedType);
                @SuppressWarnings("unchecked") // This method will throw before the cast would be unsuccesfull
                SerializingMutator<Byte> mutator = (SerializingMutator<Byte>)mutatorFactory.createOrThrow(annotatedType);

                byte mutant = mutator.mutate((byte)operand, prng);
                this.mv.visitIntInsn(opcode, mutant);
            }
            else if (opcode == Opcodes.SIPUSH) {
                AnnotatedType annotatedType = TypeSupport.asAnnotatedType(Short.class);
                annotatedType = TypeSupport.notNull(annotatedType);
                @SuppressWarnings("unchecked") // This method will throw before the cast would be unsuccesfull
                SerializingMutator<Short> mutator = (SerializingMutator<Short>)mutatorFactory.createOrThrow(annotatedType);

                short mutant = mutator.mutate((short)operand, prng);
                this.mv.visitIntInsn(opcode, mutant);
            }
            else {
                this.mv.visitIntInsn(opcode, operand);
            }
        }
        
        private boolean isICONST(int opcode) {
            return 
                opcode == Opcodes.ICONST_M1 ||
                opcode == Opcodes.ICONST_0 ||
                opcode == Opcodes.ICONST_1 ||
                opcode == Opcodes.ICONST_2 ||
                opcode == Opcodes.ICONST_3 ||
                opcode == Opcodes.ICONST_4 ||
                opcode == Opcodes.ICONST_5;
        }

        private int[] positiveICONSTopcodes = new int[] {
            Opcodes.ICONST_0,
            Opcodes.ICONST_1,
            Opcodes.ICONST_2,
            Opcodes.ICONST_3,
            Opcodes.ICONST_4,
            Opcodes.ICONST_5
        };

        private int getICONSTopcode(int val) {
            if (val == -1) {
                return Opcodes.ICONST_M1;
            }
            else if (val <= 5) {
                return positiveICONSTopcodes[val];
            }
            throw new Error("Illegal ICONST value!");
        }


        //? Should we keep the predefined values or should we allow to make the constants bigger?
        //? Making them bigger would make the classfile larger, so we have to tread carefully

        @Override
        public void visitInsn(int opcode) {

            // We don't use Jazzer mutators here, as they do not bring much benefit for such small intervals

            if (isICONST(opcode)) {
                int value = prng.closedRange(-1, 5);
                this.mv.visitInsn(getICONSTopcode(value));
            }

            // Just switch 1/0 for Longs and Doubles
            else if (opcode == Opcodes.LCONST_0) {
                this.mv.visitInsn(Opcodes.LCONST_1);
            }

            else if (opcode == Opcodes.LCONST_1) {
                this.mv.visitInsn(Opcodes.LCONST_0);
            }

            else if (opcode == Opcodes.DCONST_0) {
                this.mv.visitInsn(Opcodes.DCONST_1);
            }

            else if (opcode == Opcodes.DCONST_1) {
                this.mv.visitInsn(Opcodes.DCONST_0);
            }

            // Pick from 0..2 for floats
            else if (opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_2) {
                
                int value = prng.closedRange(0, 2);

                if (value == 0) {
                    this.mv.visitInsn(Opcodes.FCONST_0);
                }
                else if (value == 1){
                    this.mv.visitInsn(Opcodes.FCONST_1);
                }
                else {
                    this.mv.visitInsn(Opcodes.FCONST_2);
                }
            }

            else {
                this.mv.visitInsn(opcode);
            }
        }

    }
}

package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Optional;
import java.lang.reflect.AnnotatedType;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.code_intelligence.jazzer.mutation.api.MutatorFactory;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import com.code_intelligence.jazzer.mutation.api.SerializingMutator;
import com.code_intelligence.jazzer.mutation.mutator.lang.LangMutators;
import com.code_intelligence.jazzer.mutation.engine.SeededPseudoRandom;
import com.code_intelligence.jazzer.mutation.support.TypeSupport;

import com.code_intelligence.jazzer.mutation.annotation.WithUtf8Length;
import com.code_intelligence.jazzer.mutation.support.TypeHolder;

public class MutateConstantClassVisitor extends ClassVisitor {

    PseudoRandom prng;
    int api;

    public MutateConstantClassVisitor(int api, ClassVisitor classVisitor, int seed) {
        super(api, classVisitor);
        this.prng = new SeededPseudoRandom(seed);
        this.api = api;
        this.cv = classVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new MutateConstantMethodVisitor(api, this.cv.visitMethod(access, name, descriptor, signature, exceptions), prng);
    }
    
    class MutateConstantMethodVisitor extends MethodVisitor {

        PseudoRandom prng;
        MutatorFactory mutatorFactory = LangMutators.newFactory();

        public MutateConstantMethodVisitor(int api, MethodVisitor methodVisitor, PseudoRandom prng) {
            super(api, methodVisitor);
            this.mv = methodVisitor;
            this.prng = prng;
        }


        <T> T mutateOrThrow(Object value, Class<T> clazz) throws Exception {

            AnnotatedType annotatedType = TypeSupport.asAnnotatedType(clazz);

            if (value instanceof String s) {
                // Divided by 2 because utf16 representation of utf8 can be up to 2 times larger, and we need to keep the size of the classfile small
                // TODO: perhaps allow the string to grow a little?
                annotatedType = JazzerTypeSupport.WithUtf8Length(annotatedType, 0, s.length() / 2);
            }

            @SuppressWarnings("unchecked") // This method will throw before the cast would be unsuccesfull
            SerializingMutator<T> mutator = (SerializingMutator<T>)mutatorFactory.createOrThrow(annotatedType);

            return mutator.mutate(clazz.cast(value), prng);
        }

        // TODO: special load constant instructions for small ints/floats/...

        @Override
        public void visitLdcInsn(Object value){
            try {
                Object mutant = mutateOrThrow(value, value.getClass());
                this.mv.visitLdcInsn(mutant);
            }
            catch (Exception e) {
                this.mv.visitLdcInsn(value);
            }
        }

    }
}

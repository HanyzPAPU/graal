package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import java.lang.reflect.Field;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public class InsertWriteMutation extends AbstractMutation {
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        
        InstructionVisitor iv = new InstructionVisitor(Opcodes.ASM9);
        mn.accept(iv);
        int iindex = prng.indexIn(iv.iindex());

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            return new InsertValuePushMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng, cn, mn) {
                
                boolean isStatic = (mn.access & Opcodes.ACC_STATIC) != 0;

                Type selectedType;
                
                @Override
                protected void afterPush(Type type) {
                    // Sanity check
                    assert(AsmTypeSupport.canBeAssignedTo(type, selectedType));
                }

                private void insertArrayStore(Type arrayType) {
                    // precondition: array ref on TOS
                    
                    // TODO support multiDimentionalArrays

                    assert(arrayType.getSort() == Type.ARRAY);
                    assert(arrayType.getDimensions() == 1);

                    int index = prng.indexIn(MIN_ARRAY_SIZE);
                    mv.visitIntInsn(Opcodes.BIPUSH, index);

                    selectedType = arrayType.getElementType();
                    pushValue(selectedType);
                    mv.visitInsn(selectedType.getOpcode(Opcodes.IASTORE));
                }

                private void insertStore(int locVar) {
                    assert(selectedType != null);
                    pushValue(selectedType);
                    mv.visitIntInsn(selectedType.getOpcode(Opcodes.ISTORE), locVar);
                }

                private void insertPutField(String owner, String fieldName, boolean staticField) {
                    // precondition: object ref on TOS if not static

                    if (!staticField) {
                        this.mv.visitVarInsn(Opcodes.ALOAD, 0); // this
                    }

                    assert(selectedType != null);
                    pushValue(selectedType);
                    
                    this.mv.visitFieldInsn(
                        staticField ? Opcodes.PUTSTATIC : Opcodes.PUTFIELD,
                        owner,
                        fieldName,
                        selectedType.getDescriptor()
                    );
                }

                private boolean canInsertToLocal() {
                    return analyzer.locals.stream()
                        .map(AsmTypeSupport::getType)
                        .anyMatch(x -> x != null);
                }

                private void deref(Type type) {
                    // precondition type of TOS == type
                    
                }

                private boolean canDeref(Type type) {
                    // TODO
                    return false;
                }

                private void insertToLocal() {
                    List<Integer> possibleLocals = IntStream.range(0, analyzer.locals.size())
                        .filter(i -> AsmTypeSupport.getType(analyzer.locals.get(i)) != null)
                        .boxed()
                        .collect(Collectors.toList());

                    int locVar = prng.pickIn(possibleLocals);
                    selectedType = AsmTypeSupport.getType(analyzer.locals.get(locVar));
                    if (canDeref(selectedType)) {
                        // TODO: different prob?
                        if (prng.choice()) {
                            // TODO: Xload
                            deref(selectedType);
                        }
                    }
                    insertStore(locVar);
                }

                private boolean canInsertToField() {
                    return cn.fields.stream()
                        .filter(f -> {
                            if (isStatic) {
                                return (f.access & Opcodes.ACC_STATIC) != 0;
                            }
                            return true;
                        })
                        .map(f -> Type.getType(f.desc))
                        .anyMatch(x -> x != null);
                }

                private void insertToField() {

                    List<FieldNode> possibleFields = cn.fields.stream()
                        .filter(f -> {
                            if (isStatic) {
                                return (f.access & Opcodes.ACC_STATIC) != 0;
                            }
                            return true;
                        })
                        .filter(f -> Type.getType(f.desc) != null)
                        .collect(Collectors.toList());

                    FieldNode fn = prng.pickIn(possibleFields);
                    
                    selectedType = Type.getType(fn.desc);

                    if (canDeref(selectedType)) {
                        // TODO: different prob
                        if (prng.choice()) {
                            // TODO: get{static|field}
                            deref(selectedType);
                        }
                    }
                    
                    insertPutField(cn.name, fn.name, (fn.access & Opcodes.ACC_STATIC) != 0);
                }

                private void insertWrite() {

                    boolean localPossible = canInsertToLocal();
                    boolean fieldPossible = canInsertToField();

                    if (localPossible && fieldPossible) {
                        if (prng.choice()) {
                            insertToLocal();
                        }
                        else {
                            insertToField();
                        }
                        return;
                    }

                    if (localPossible) {
                        insertToLocal();
                    }
                    else if (fieldPossible) {
                        insertToField();
                    }
                    else {
                        throw new RuntimeException("No existing locals or fields to write to!");
                    }
                }

                @Override
                public void visitInstruction() {
                    if (iindex() == targetIindex) {
                        insertWrite();
                    }
                }
            };
        };
    }
}
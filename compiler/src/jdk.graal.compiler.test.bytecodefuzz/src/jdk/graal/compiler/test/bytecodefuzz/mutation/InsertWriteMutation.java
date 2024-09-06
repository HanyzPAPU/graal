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

        // Don't select before first instruction in a class without any fields, as there will be no place to save to
        int iindex = cn.fields.isEmpty() ? prng.otherIndexIn(iv.iindex(), 0) : prng.indexIn(iv.iindex());

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            return new InsertValuePushMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng, cn, mn) {
                
                boolean isStatic = AsmTypeSupport.isStatic(mn.access);

                Type selectedType;
                
                @Override
                protected void afterPush(Type type) {
                    // Sanity check
                    assert(AsmTypeSupport.canBeAssignedTo(type, selectedType));
                }

                private void insertArrayStore(Type arrayType) {
                    // precondition: array ref on TOS
                    
                    assert(arrayType.getSort() == Type.ARRAY);

                    int index = prng.indexIn(MIN_ARRAY_SIZE);
                    mv.visitIntInsn(Opcodes.BIPUSH, index);

                    selectedType = AsmTypeSupport.getDirectSubarrayType(arrayType);
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
                    assert(selectedType != null);
                    pushValue(selectedType);
                    this.mv.visitFieldInsn(
                        staticField ? Opcodes.PUTSTATIC : Opcodes.PUTFIELD,
                        owner,
                        fieldName,
                        selectedType.getDescriptor()
                    );
                }

                private void insertWriteToTosVal() {
                    // FieldHolder or array on TOS without derefs

                    Type tosType = getStackTosType();
                    assert(tosType != null);
                    
                    if (tosType.getSort() == Type.ARRAY) {
                        insertArrayStore(tosType);
                    }
                    else if (tosType.equals(AsmTypeSupport.fieldHolderType)) {
                        Type fieldType = prng.pickIn(AsmTypeSupport.fieldHolderFieldTypes);
                        String fieldName = AsmTypeSupport.fieldHolderFieldNameByType.get(fieldType);
                        selectedType = fieldType;
                        insertPutField(AsmTypeSupport.fieldHolderType.getInternalName(), fieldName, false);
                    }
                    else {
                        throw new RuntimeException("Unsupported type on TOS! " + tosType);
                    }
                }

                private boolean canInsertToLocal() {
                    return analyzer.locals.stream()
                        .map(AsmTypeSupport::getType)
                        .anyMatch(x -> x != null);
                }

                // Recursively dereferences the value on TOS but leaves a value that can be written to on TOS
                private void deref(Type type) {
                    // precondition type of TOS == type

                    if (type.getSort() == Type.ARRAY) {
                        assert(type.getDimensions() > 1);

                        int index = prng.indexIn(MIN_ARRAY_SIZE);
                        mv.visitIntInsn(Opcodes.BIPUSH, index);
                        mv.visitInsn(Opcodes.AALOAD);
                    }
                    else if (type.equals(AsmTypeSupport.fieldHolderType)) {
                        List<Type> viableFields = AsmTypeSupport.fieldHolderFieldTypes.stream()
                            .filter(this::canWriteTo)
                            .collect(Collectors.toList());
                        Type fieldType = prng.pickIn(viableFields);
                        this.mv.visitFieldInsn(
                            Opcodes.GETFIELD,
                            AsmTypeSupport.fieldHolderType.getInternalName(),
                            AsmTypeSupport.fieldHolderFieldNameByType.get(fieldType),
                            fieldType.getDescriptor()
                        );
                    }

                    Type tosType = getStackTosType();
                    assert(tosType != null);

                    if(canDeref(tosType)) {
                        if (prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                            deref(tosType);
                        }
                    }
                }

                private boolean canWriteTo(Type type) {
                    return type.getSort() == Type.ARRAY || type.equals(AsmTypeSupport.fieldHolderType);
                }

                private boolean canDeref(Type type) {
                    if (type.getSort() == Type.ARRAY) {
                        return type.getDimensions() > 1 || type.getElementType().equals(AsmTypeSupport.fieldHolderType);
                    }
                    return type.equals(AsmTypeSupport.fieldHolderType);
                }

                private void insertToLocal() {
                    List<Integer> possibleLocals = IntStream.range(0, analyzer.locals.size())
                        .filter(i -> AsmTypeSupport.getType(analyzer.locals.get(i)) != null)
                        .boxed()
                        .collect(Collectors.toList());

                    int locVar = prng.pickIn(possibleLocals);
                    selectedType = AsmTypeSupport.getType(analyzer.locals.get(locVar));
                    if (canDeref(selectedType)) {
                        if (prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                            this.mv.visitVarInsn(selectedType.getOpcode(Opcodes.ILOAD), locVar);
                            deref(selectedType);
                            insertWriteToTosVal();   
                            return;
                        }
                    }
                    insertStore(locVar);
                }

                private boolean canInsertToField() {
                    return cn.fields.stream()
                        .filter(f -> {
                            if (isStatic) {
                                return AsmTypeSupport.isStatic(f.access);
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
                                return AsmTypeSupport.isStatic(f.access);
                            }
                            return true;
                        })
                        .filter(f -> Type.getType(f.desc) != null)
                        .collect(Collectors.toList());

                    FieldNode fn = prng.pickIn(possibleFields);
                    boolean staticField = AsmTypeSupport.isStatic(fn.access);
                    
                    selectedType = Type.getType(fn.desc);

                    if (canDeref(selectedType)) {
                        if (prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                            if (!staticField) {
                                this.mv.visitVarInsn(Opcodes.ALOAD, 0);        
                            }
                            this.mv.visitFieldInsn(
                                staticField ? Opcodes.GETSTATIC : Opcodes.GETFIELD,
                                cn.name,
                                fn.name,
                                fn.desc
                            );
                            deref(selectedType);
                            insertWriteToTosVal();
                            return;
                        }
                    }

                    if (!staticField) {
                        this.mv.visitVarInsn(Opcodes.ALOAD, 0);
                    }
                    insertPutField(cn.name, fn.name, staticField);
                }

                private boolean canWriteToTos() {
                    Type tosType = getStackTosType();
                    if (tosType == null) return false;
                    // Since tos write can generate a POP, we can lose the only value of the TOS type
                    // by popping it, so check first if we can find it at any other place.
                    // TODO: Technically, if the 2nd stack value would be of the same type, this would be fine
                    return  canGenerateFresh(tosType) || canLoad(tosType) || canGetField(tosType);
                }

                private void writeToTos() {
                    Type tosType = getStackTosType();
                    assert(tosType != null);

                    if (canWriteTo(tosType)) {
                        if (prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {

                            // We will use up the value at TOS by the inserted write, so DUP it
                            mv.visitInsn(Opcodes.DUP);

                            if (canDeref(tosType)) {
                                if (prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                                    deref(tosType);
                                }
                            }
                            insertWriteToTosVal();
                            return;
                        }
                    }

                    // POP
                    if (tosType.equals(Type.LONG_TYPE) || tosType.equals(Type.DOUBLE_TYPE)) {
                        this.mv.visitInsn(Opcodes.POP2);
                    }
                    else {
                        this.mv.visitInsn(Opcodes.POP);
                    }
                    selectedType = tosType;
                    pushValue(tosType);
                }

                private List<Runnable> gatherVariants() {
                    List<Runnable> res = new ArrayList<>();
                    if (canWriteToTos()) {
                        res.add(this::writeToTos);
                    }
                    if (canInsertToLocal()) {
                        res.add(this::insertToLocal);
                    }
                    if (canInsertToField()) {
                        res.add(this::insertToField);
                    }
                    if (res.isEmpty()){
                        throw new MutationFailedException("Insert write selected an instruction without a place to insert to");
                    }
                    return res;
                }

                private void insertWrite() {
                    prng.pickIn(gatherVariants()).run();                    
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
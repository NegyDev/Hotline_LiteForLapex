package Negy.Transformers;

import org.objectweb.asm.*;

import javax.swing.*;

public class PlayerControllerMpTransformer {
    public static byte[] TransformClient(byte[] originalBytes) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("N") && descriptor.equals("()F")) {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitLdcInsn(Object o) {
                            mv.visitLdcInsn(4.0F);
                        }
                    };
                }else if (name.equals("l") && descriptor.equals("()Z")){
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode() {
                            mv.visitLdcInsn("reach");
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Module/ModuleManager", "getModule", "(Ljava/lang/String;)LNegy/Module/Module;", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 3);
                            mv.visitVarInsn(Opcodes.ALOAD, 3);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Negy/Module/Module", "isEnabled", "()Z", false);
                            Label l1 = new Label();
                            mv.visitJumpInsn(Opcodes.IFNE, l1);
                            mv.visitInsn(Opcodes.ICONST_0);
                            mv.visitInsn(Opcodes.IRETURN);
                            mv.visitLabel(l1);
                            mv.visitInsn(Opcodes.ICONST_1);
                            mv.visitInsn(Opcodes.IRETURN);
                            mv.visitMaxs(3, 3);
                            mv.visitEnd();
                        }
                    };
                }
                return original_mv;
            }
        };

        ClassReader classReader = new ClassReader(originalBytes);
        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }
}

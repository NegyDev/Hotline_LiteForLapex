package Negy.Transformers;

import org.objectweb.asm.*;

public class StartTransformer {
    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("u") && descriptor.equals("(Lnet/minecraft/client/Ym;)V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitCode();
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"Negy/Client","Start","()V",false);
                            mv.visitVarInsn(Opcodes.ALOAD,1);
                            mv.visitTypeInsn(Opcodes.INSTANCEOF, "sonoyuncu/W");
                            Label label = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, label);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(label);
                            mv.visitVarInsn(Opcodes.ALOAD, 1);
                            mv.visitTypeInsn(Opcodes.INSTANCEOF, "aC");
                            Label label2 = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, label2);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(label2);
                            mv.visitVarInsn(Opcodes.ALOAD, 1);
                            mv.visitTypeInsn(Opcodes.INSTANCEOF, "ac");
                            Label label3 = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, label3);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(label3);
                        }
                    };
                }else if (name.equals("t") && descriptor.equals("()V")) {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitCode();
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"Negy/Client","Start","()V",false);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC,"Negy/Module/Module","KeyCheckEvent","()V",false);

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

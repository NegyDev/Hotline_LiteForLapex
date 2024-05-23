package Negy.Transformers;

import org.objectweb.asm.*;

public class GuiIngameTransformer {

    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("p") && descriptor.equals("(FLnet/minecraft/client/Yy;)V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitTypeInsn(Opcodes.NEW, "Negy/Event/impl/Render2DEvent");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "Negy/Event/impl/Render2DEvent", "<init>", "()V", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 25);
                            mv.visitVarInsn(Opcodes.ALOAD, 25);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Module/Module", "onEventAdd", "(LNegy/Event/Event;)V", false);
                            mv.visitCode();

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

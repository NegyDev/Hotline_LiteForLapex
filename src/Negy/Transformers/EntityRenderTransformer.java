package Negy.Transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
public class EntityRenderTransformer {

    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("a") && descriptor.equals("(Lnet/minecraft/BM;DDDFF)V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitTypeInsn(Opcodes.NEW, "Negy/Event/impl/EntityRenderEvent");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitVarInsn(Opcodes.ALOAD, 0);
                            mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/d");
                            mv.visitVarInsn(Opcodes.ALOAD, 1);
                            mv.visitVarInsn(Opcodes.DLOAD, 2);
                            mv.visitVarInsn(Opcodes.DLOAD, 4);
                            mv.visitVarInsn(Opcodes.DLOAD, 6);
                            mv.visitVarInsn(Opcodes.FLOAD, 9);
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "Negy/Event/impl/EntityRenderEvent", "<init>", "(Lnet/minecraft/client/d;Lnet/minecraft/BM;DDDF)V", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 55);
                            mv.visitVarInsn(Opcodes.ALOAD, 55);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Event/EventListener", "ListenEvent", "(LNegy/Event/Event;)V", false);
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

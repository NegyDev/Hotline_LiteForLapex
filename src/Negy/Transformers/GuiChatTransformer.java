package Negy.Transformers;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
public class GuiChatTransformer {
    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("D") && descriptor.equals("(Ljava/lang/String;)V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitTypeInsn(Opcodes.NEW, "Negy/Event/impl/TextEvent");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitVarInsn(Opcodes.ALOAD, 1);
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "Negy/Event/impl/TextEvent", "<init>", "(Ljava/lang/String;)V", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 3);
                            mv.visitVarInsn(Opcodes.ALOAD, 3);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Command/Command", "ChatReceiveEventCall", "(LNegy/Event/impl/TextEvent;)V", false);
                            mv.visitVarInsn(Opcodes.ALOAD, 3);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Negy/Event/impl/TextEvent", "getText", "()Ljava/lang/String;", false);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Command/Command", "stringCheckEvent", "(Ljava/lang/String;)Z", false);
                            Label label = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, label);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(label);
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

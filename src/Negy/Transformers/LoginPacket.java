package Negy.Transformers;

import org.objectweb.asm.*;

public class LoginPacket {
    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("run") && descriptor.equals("()V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitVarInsn(Opcodes.ALOAD, 4);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
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

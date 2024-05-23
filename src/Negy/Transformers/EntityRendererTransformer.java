package Negy.Transformers;

import org.objectweb.asm.*;

public class EntityRendererTransformer {
    public static byte[] TransformClient(byte[] originalBytes) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("L") && descriptor.equals("()F")) {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode() {
                            mv.visitVarInsn(Opcodes.DLOAD, 6);
                            mv.visitLdcInsn(6.0);
                            mv.visitVarInsn(Opcodes.DSTORE, 6);
                            super.visitCode();
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

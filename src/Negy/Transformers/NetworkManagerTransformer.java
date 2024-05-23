package Negy.Transformers;

import org.objectweb.asm.*;

public class NetworkManagerTransformer {
    public static byte[] TransformClient(byte[] originalBytes)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM6, classWriter)
        {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor original_mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("B") && descriptor.equals("(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/wj;)V"))
                {
                    return new MethodVisitor(Opcodes.ASM6, original_mv) {
                        @Override
                        public void visitCode()
                        {
                            mv.visitTypeInsn(Opcodes.NEW, "Negy/Event/impl/PacketReceiveEvent");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitVarInsn(Opcodes.ALOAD, 2);
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "Negy/Event/impl/PacketReceiveEvent", "<init>", "(Lnet/minecraft/wj;)V", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 5);
                            mv.visitVarInsn(Opcodes.ALOAD, 5);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Event/EventListener", "ListenEvent", "(LNegy/Event/Event;)V", false);
                            mv.visitVarInsn(Opcodes.ALOAD, 5);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Negy/Event/impl/PacketReceiveEvent", "isCancelled", "()Z", false);
                            Label notCancelled = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, notCancelled);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(notCancelled);
                            mv.visitCode();
                        }
                    };
                }else if (name.equals("f")&&descriptor.equals("(Lnet/minecraft/wj;)V")){
                    return new MethodVisitor(Opcodes.ASM6,original_mv){
                        @Override
                        public void visitCode() {
                            mv.visitTypeInsn(Opcodes.NEW, "Negy/Event/impl/PacketSendEvent");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitVarInsn(Opcodes.ALOAD,1);
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "Negy/Event/impl/PacketSendEvent", "<init>", "(Lnet/minecraft/wj;)V", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 7);
                            mv.visitVarInsn(Opcodes.ALOAD, 7);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Negy/Event/EventListener", "ListenEvent", "(LNegy/Event/Event;)V", false);
                            mv.visitVarInsn(Opcodes.ALOAD, 7);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Negy/Event/impl/PacketSendEvent", "isCancelled", "()Z", false);
                            Label notCancelled = new Label();
                            mv.visitJumpInsn(Opcodes.IFEQ, notCancelled);
                            mv.visitInsn(Opcodes.RETURN);
                            mv.visitLabel(notCancelled);
                            mv.visitVarInsn(Opcodes.ALOAD, 7);
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Negy/Event/impl/PacketSendEvent", "getPacket", "()Lnet/minecraft/wj;", false);
                            mv.visitVarInsn(Opcodes.ASTORE, 1);
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

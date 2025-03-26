package com.siprikorea.agent.properties;

import org.objectweb.asm.*;

public class MethodAdapter extends MethodVisitor {
    public MethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.ARETURN) {
            mv.visitVarInsn(Opcodes.ASTORE, 1);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/siprikorea/agent/properties/EnvVariableReplacer", "replaceEnvVars", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
        }
        super.visitInsn(opcode);
    }
}
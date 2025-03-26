package com.siprikorea.agent.properties;

import java.lang.instrument.Instrumentation;
import java.io.File;
import java.util.jar.JarFile;

public class EnvVariableAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Environment Variable Agent Loaded!");

        try {
            // ASM 라이브러리를 부트스트랩 클래스로 등록
            File agentJar = new File(EnvVariableAgent.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            inst.appendToBootstrapClassLoaderSearch(new JarFile(agentJar));
            System.out.println("Agent JAR added to bootstrap class loader search");
        } catch (Exception ignored) {
        }

        // Transformer 등록 (부트스트랩 클래스 로더도 포함)
        inst.addTransformer(new PropertiesTransformer(), true);

        try {
            // `Properties` 클래스 재변환 시도 (JVM에 따라 실패할 수 있음)
            Class<?> propertiesClass = Class.forName("java.util.Properties");
            if (inst.isRetransformClassesSupported()) {
                inst.retransformClasses(propertiesClass);
            }
        } catch (Exception ignored) {
        }
    }
}
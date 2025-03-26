package com.siprikorea.agent.properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvVariableReplacer {
    private static final Pattern ENV_PATTERN = Pattern.compile("\\$\\{([A-Za-z0-9_.]+)}");

    public static Object replaceEnvVars(Object value) {
        if (value instanceof String strValue) {
            Matcher matcher = ENV_PATTERN.matcher(strValue);
            StringBuilder result = new StringBuilder();

            while (matcher.find()) {
                String envVar = matcher.group(1);
                String envValue = System.getProperty(envVar);
                matcher.appendReplacement(result, envValue != null ? envValue : "");
            }
            matcher.appendTail(result);
            return result.toString();
        }
        return value; // 비 문자열 값은 변환하지 않음
    }
}
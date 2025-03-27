# Properties Environment Variable Agent

## Overview

`Properties Environment Variable Agent` is a Java Agent that replaces placeholders (e.g. ${ENV_VAR_NAME}) in the values of java.util.Properties with the corresponding values provided via Java system properties (e.g., -DENV_VAR_NAME=...).

This agent is designed to support **legacy systems** where `Properties` files are used, enabling the use of environment variables for configuration such as file paths and other settings.

---

## Features

- Loads as a Java Agent during JVM startup
- Intercepts `java.util.Properties` class usage
- Replaces value placeholders like ${...} with the corresponding Java system property values

Example:

```properties
log.path=${HOME}/logs/app.log
```

If the Java system property is set with -DHOME=/home/user, the above property is interpreted as:
```
log.path=/home/user/logs/app.log
```

---

## Build

This project uses the `shadowJar` plugin to create a fat JAR that includes all necessary dependencies.

Use Gradle to build the project:

```bash
./gradlew shadowJar
```

---

## Usage

1. Build the project to generate the .jar file.
2. Launch your Java application with the agent using the `-javaagent` option.
3. Specify system property values for placeholders using `-D` options.

For example:

```bash
java -javaagent:/path/to/properties-env-agent-all.jar \
     -DHOME=/home/user \
     -jar your-app.jar
```

---

## License

MIT License

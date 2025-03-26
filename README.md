# Properties Environment Variable Agent

## Overview

`Properties Environment Variable Agent` is a Java Agent that replaces environment variable placeholders (e.g. `${ENV_VAR_NAME}`) in the values of `java.util.Properties` with their corresponding values from the system environment.

This agent is designed to support **legacy systems** where `Properties` files are used, enabling the use of environment variables for configuration such as file paths and other settings.

---

## Features

- Loads as a Java Agent during JVM startup
- Intercepts `java.util.Properties` class usage
- Replaces value placeholders like `${...}` with actual environment variable values

Example:

```properties
log.path=${HOME}/logs/app.log
```

If the environment variable HOME=/home/user, the above property is interpreted as:
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
- Build the project to generate the .jar file.
- Launch your Java application with the agent using the -javaagent option:

```bash
java -javaagent:/path/to/properties-env-agent-all.jar -jar your-app.jar
```

---

## License

MIT License

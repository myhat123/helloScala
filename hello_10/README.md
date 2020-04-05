build.gradle
============

```groovy
plugins {
    .....

    id "com.github.johnrengelman.shadow" version "5.2.0"
}

jar {
    manifest {
        attributes 'Main-Class': 'hello_10.Hello'
    }
}
```

> gradle build

> gradle shadowJar

生成hello_10-all.jar

java -jar build/libs/hello_10-all.jar
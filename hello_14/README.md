参考资料

https://guides.gradle.org/creating-multi-project-builds/

多项目构建
========

gradle init --type scala-library

去除src目录，自建 sparkpi, fintest 子目录，把src目录结构拷贝到子目录中
settings.gradle 增加子项目名称

gradle :projects

官网文档
=======
https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block

参考这个示例，处理shadowJar插件

Applying plugins to subprojects

plugins {
    id 'org.gradle.sample.hello' version '1.0.0' apply false
    id 'org.gradle.sample.goodbye' version '1.0.0' apply false
}

subprojects {
    if (name.startsWith('hello')) {
        apply plugin: 'org.gradle.sample.hello'
    }
}

子项目构建
========
gradle :sparkpi:shadowJar

spark-submit --master spark://localhost:7077 --class sparkpi.SparkPi ./sparkpi/build/libs/sparkpi-1.0-all.jar

gradle :fintest:build

spark-submit --master spark://localhost:7077 --class fintest.FinTest ./fintest/build/libs/fintest-1.0-all.jar
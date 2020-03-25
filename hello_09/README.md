参考资料

https://guides.gradle.org/building-scala-libraries/

gradle init --type scala-library

build.gradle 文件

plugins {
    // Apply the scala plugin to add support for Scala
    id 'scala'
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Use Scala 2.12 in our library project
    implementation 'org.scala-lang:scala-library:2.12.8'

    // Use Scalatest for testing our library
    testImplementation 'junit:junit:4.12'
    testImplementation 'org.scalatest:scalatest_2.12:3.0.8'

    // Need scala-xml at test runtime
    testRuntimeOnly 'org.scala-lang.modules:scala-xml_2.12:1.2.0'
}

gradle build

scala -cp build/libs/hello_09.jar hello_09.Hello
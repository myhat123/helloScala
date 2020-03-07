编译后执行
========

参考资料：https://benmosheron.gitlab.io/blog/2019/04/16/scala-for-java.html

HelloWorld.scala 文件名 与 object Hello 伴生对象名称 可以不一致

第一种方式
scalac HelloWorld.scala
scala Hello

第二种方式
java -cp .:/home/hzg/scala-2.12.10/lib/scala-compiler.jar:/home/hzg/scala-2.12.10/lib/scala-library.jar Hello
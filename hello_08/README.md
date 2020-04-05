自执行jar包
==========

scalac -d ./classes src/com/mine/hello/Utils.scala

scalac -d ./classes -cp ./classes src/HelloWorld.scala

manifest.txt
============
```
Main-Class: Hello
Class-Path: scala-library.jar scala-compiler.jar
```

通过MANIFEST.MF中的Class-Path 来指定运行时需要用到的其他jar，其他jar可以是当前路径，也可以是
当前路径下的子目录。但不能包含在当前jar里面。Class-Path的冒号后面必须要空一个空格，否则会出错
文件的最后一行必须是一个回车换行符，否则也会出错

cp /home/hzg/scala-2.12.10/lib/scala-compiler.jar libs/

cp /home/hzg/scala-2.12.10/lib/scala-library.jar libs/

jar cmvf manifest.txt libs/hello.jar -C classes .

java -jar libs/hello.jar

后续
====

参考 kotlin include-runtime

可以把 kotlin 相应的 class 文件放入 jar 包中
这样可以省略 Class-Path处理，从而把scala-compiler, scala-library的class放入 jar 包中

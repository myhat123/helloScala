包机制之一
=========
scala 的包，可以不对应目录结构

Utils.scala 增加
package com.mine.hello

scalac Utils.scala

HelloWorld.scala 增加
import com.mine.hello._

scalac -cp . HelloWorld.scala
scala -cp . Hello

java -cp .:/home/hzg/scala-2.12.10/lib/scala-compiler.jar:/home/hzg/scala-2.12.10/lib/scala-library.jar Hello
参考资料:
http://www.scalatest.org/quick_start

> scalac Utils.scala

> scalac -cp .:./lib/scalatest_2.12-3.1.1.jar:./lib/scalactic_2.12-3.1.1.jar TestUtils.scala

> scala -cp .:./lib/scalatest_2.12-3.1.1.jar:./lib/scalactic_2.12-3.1.1.jar org.scalatest.run TestUtils

测试结果
=======

```
Run starting. Expected test count is: 1
TestUtils:
- should 两数之和
Run completed in 222 milliseconds.
Total number of tests run: 1
Suites: completed 1, aborted 0
Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
All tests passed.
```

> scalac -cp . HelloWorld.scala

> scala -cp . Hello

> java -cp .:/home/hzg/scala-2.12.10/lib/scala-compiler.jar:/home/hzg/scala-2.12.10/lib/scala-library.jar Hello
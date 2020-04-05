15位身份证补全为18位身份证算法

https://www.cnblogs.com/10158wsj/p/7050736.html

参照java代码，转成scala代码

这个代码作为spark自定义函数使用

参考资料：需翻墙
https://medium.com/@mrpowers/the-different-type-of-spark-functions-custom-transformations-column-functions-udfs-bf556c9d0ce7
 
 ```scala
def toLowerFun(str: String): Option[String] = {
  val s = Option(str).getOrElse(return None)
  Some(s.toLowerCase())
}

val toLower = udf[Option[String], String](toLowerFun)
```
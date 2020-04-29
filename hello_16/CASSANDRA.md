cassandra
=========

参考资料

https://thelastpickle.com/blog/2016/07/27/about-deletes-and-tombstones.html

cassandra 3.11.4  
运行 cassandra -f

data-files目录下有schema.cql中，创建 keyspace, table

运行cqlsh，操作时长设置 --request-timeout

> cqlsh --request-timeout=120 localhost

> cqlsh -u xxx -p xxxx localhost

设置gc_grace_seconds，默认是10天，调整这个参数为0，让后台立即清理数据

```sql
alter table finance.brch_qry_dtl with gc_grace_seconds=0;
```

```sql
use finance;
truncate brch_qry_dtl;
drop table brch_qry_dtl;
drop keyspace finance;
```

写入之后，并没有立刻在磁盘上 data/finance/brch_qyr_dtl*/ 目录下是空的

> nodetool flush 

运行之后，才有一大堆.db文件

```
hzg@gofast:~/cassandra-3.11.4/data/data/finance$ du -a -h
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Summary.db
36K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Index.db
140K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Data.db
12K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Statistics.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-TOC.txt
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-CompressionInfo.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Digest.crc32
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/backups
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Filter.db
216K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8
220K	.
```

> sstabledump ./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Data.db

```json
[
  {
    "partition" : {
      "key" : [ "6042****9393" ],
      "position" : 0
    },
    "rows" : [
      {
        "type" : "row",
        "position" : 74,
        "clustering" : [ "20191127200455", 2 ],
        "liveness_info" : { "tstamp" : "2020-04-29T10:23:18.734807Z" },
        "cells" : [
          { "name" : "amt", "value" : 30.0 },
          { "name" : "rpt_sum", "value" : "短扣" },
          { "name" : "tran_date", "value" : "2019-11-27" }
        ]
      }
    ]
  },
```

cell 删除
partition 删除
range 删除
row 删除

> spark-submit --master spark://localhost:7077 --class cleanup.CleanUp ./cleanup/build/libs/cleanup-1.0-all.jar brch_qry_dtl 2019-11-27 2019-11-27

nodetool flush

增加了 2 系列文件

```
hzg@gofast:~/cassandra-3.11.4/data/data/finance$ du -a -h
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Summary.db
24K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Index.db
36K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Index.db
140K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Data.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Digest.crc32
44K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Data.db
12K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Statistics.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-TOC.txt
12K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Statistics.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Summary.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-CompressionInfo.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Filter.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Digest.crc32
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/backups
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-1-big-Filter.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-CompressionInfo.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-TOC.txt
316K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8
320K	.
```

> sstabledump ./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-2-big-Data.db

```json
[
  {
    "partition" : {
      "key" : [ "6042****9393" ],
      "position" : 0
    },
    "rows" : [
      {
        "type" : "row",
        "position" : 54,
        "clustering" : [ "20191127200455", 2 ],
        "deletion_info" : { "marked_deleted" : "2020-04-29T10:51:21.312987Z", "local_delete_time" : "2020-04-29T10:51:21Z" },
        "cells" : [ ]
      }
    ]
  },
```

> nodetool compact

运行之后，1,2 -> 3
```
hzg@gofast:~/cassandra-3.11.4/data/data/finance$ du -a -h
24K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Index.db
12K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Statistics.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Digest.crc32
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-TOC.txt
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Filter.db
76K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Data.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-CompressionInfo.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/backups
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Summary.db
140K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8
144K	.
```

> sstabledump ./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Data.db

compact之后，数据留下了2019-11-28的

```json
[
  {
    "partition" : {
      "key" : [ "6042****4554" ],
      "position" : 0
    },
    "rows" : [
      {
        "type" : "row",
        "position" : 80,
        "clustering" : [ "20191128004505", 2 ],
        "liveness_info" : { "tstamp" : "2020-04-29T10:23:17.539214Z" },
        "cells" : [
          { "name" : "amt", "value" : 1000.0 },
          { "name" : "rpt_sum", "value" : "微信转账" },
          { "name" : "tran_date", "value" : "2019-11-28" }
        ]
      },
      {
        "type" : "row",
        "position" : 80,
        "clustering" : [ "20191128004649", 1 ],
        "liveness_info" : { "tstamp" : "2020-04-29T10:23:17.539214Z" },
        "cells" : [
          { "name" : "amt", "value" : 1000.0 },
          { "name" : "rpt_sum", "value" : "退货" },
          { "name" : "tran_date", "value" : "2019-11-28" }
        ]
      }
    ]
  },
```

> sstablemetadata ./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/md-3-big-Data.db

```sql
truncate brch_qry_dtl;
```

文件整个移至snapshots目录中

```
hzg@gofast:~/cassandra-3.11.4/data/data/finance$ du -a -h
24K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Index.db
12K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Statistics.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Digest.crc32
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-TOC.txt
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/schema.cql
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Filter.db
76K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Data.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-CompressionInfo.db
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/manifest.json
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl/md-3-big-Summary.db
144K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots/truncated-1588159100197-brch_qry_dtl
148K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/snapshots
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/backups
156K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8
160K	.
```

> nodetool clearsnapshot finance

执行之后

```
hzg@gofast:~/cassandra-3.11.4/data/data/finance$ du -a -h
4.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8/backups
8.0K	./brch_qry_dtl-ea50e8408a0211eab9b63d004c7712a8
12K	.
```
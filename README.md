# backups

1117
---
#CqlExample
Streaming Cql UDF.
1.PhoneFilter
2.StringFilter
3.IpFilter
#CqlDriver
Driver CQL file in storm and monitor the task.
#ACAL
AC algorithm
#Python
File python contains the code from s2 to s11 of the book "Python Crash Course".
---
1119
---
1.Add streaming CQL lib
2.Update CqlDriver and CqlExample
3.Add s15 of the book "Python Crash Course"
#CqlDriver
1.删除ConsumerThread类
2.监控类StormList增加功能--启动子线程。子线程通过Consumer监控输入输出量
#CqlExample
1.自定义算子初始化时读取的规则文件地址修改为"/home/stream-cql-bianry-storm-2.0/bin/rule"


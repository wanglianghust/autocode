1.在 tsb_codegen\properties\generator.properties 
里面对dbUrl，jdbcDriver，和userName pass, schema 作修改,因为你连接的是不同的数据库

dbUserid=tsb
dbPasswd=tsb
dbSchema=tsb
jdbcDriver=com.mysql.jdbc.Driver
dbUrl=jdbc:mysql://10.0.10.21:3306/tsb_ischool_qingguo


2.修改需要导出成实体的表名和对应的实体名字，用空格隔开
修改 tsb_codegen\ssbTables文件 里面的表的名字和表对应的java实体对象名字，例如。
t_u_basic User
t_u_basic User2


3.修改包前缀因为你生成的代码需要你对应的项目路径，因此需要做如下修改
在 tsb_codegen\properties\generator.properties中有两行

#创建的包路径
packageLocation=com/test
#java包前缀
packagePrefix=com.test


4.执行
执行 com.codegenerator.runner.AppFuseGenerator 类

5.执行后，代码在tsb_codegen\build 下面

6.代码结构
	src\dao
	src\model
	src\service
	src\webservice












2007-1-31 
解决模型层映射错误问题
解决自动生成ssb-dao.xml，ssb-ds.xml
自动配置session-factory和引用的hbm.xml


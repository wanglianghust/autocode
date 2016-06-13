# autocode
java代码生成器

#使用说明

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

创建的包路径  
packageLocation=com/test  
java包前缀  
packagePrefix=com.test  

4.执行  
执行 com.codegenerator.runner.AppFuseGenerator 类  

5.执行后，代码在tsb_codegen\build 下面  

6.代码结构  
    src\dao  
    src\model  
    src\service  
    src\webservice  


#二次开发
```
autocode/
├── properties/tsb/
│   ├── CommonTemplates.properties   对应模板路径
│   ├── FileLocations.properties     模板对应文件所在目录
│   ├── FileNames.properties         模板对应文件的格式
│   └── generator.properties         初始化配置
├── templates/tsb/common/
    ├── Model.java.vm                模板
    └── Mxml.xml.vm                  。。。

```



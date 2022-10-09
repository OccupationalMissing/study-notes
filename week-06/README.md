## 网络编程实践题
simple-cat 项目为自定义Web容器，simple-cat-test 为测试项目。
### simple-cat-test 使用 simple-cat 步骤
1. 添加 pom 依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.wang.simplecat</groupId>
        <artifactId>simple-cat</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

2. 自定义servlet  
测试项目中共定义三个 servlet : MyServlet 、MyServlet2 、IndexServlet

3. 启动服务 ： MyApplication.java

4. 测试结果 
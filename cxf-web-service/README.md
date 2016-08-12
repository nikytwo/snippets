# 车辆违章 web service

## 依赖

* jdk 1.6
* maven 3.1.0+
* 其他见[pom.xml](./pom.xml)文件

## 编译打包

运行命令

```shell
// 忽略测试
mvn package -Dmaven.test.skip=true
```

## 部署

~~**注意**: 需将 JVM 相关编码设置为`UTF-8`（-Dfile.encoding=UTF8）~~

### 方式1

将上面命令生成的 ./target/*.war 文件发布的相应的应用服务器即可。

### 方式2

将上面命令生成的 ./target/*.zip 文件解压后运行里面的`startup.bat`即可。

以此方式运行时，
默认主机名为： localhost，可通过`-h`参数修改(`localhost`时只能本地访问)；
默认端口号为： 8080，可通过`-p`参数修改；
默认上下文路径为: /TrafficViolate，可通过`-c`参数修改；

以默认参数启动后，web 服务地址为： http://localhost:8080/TrafficViolate?wsdl

## 配置

* [api-server.properties](./src/main/resources/api-server.properties)： 设置供应商服务的相关参数
* [log4j.properties](./src/main/resources/log4j.properties): 设置日志

## 使用

根据上面部署的服务地址（形如：`http://localhost:8080/TrafficViolate?wsdl`）生成 web service 客户端相关类。

调用示例如下：

```csharp
// 实例化 web service 客户端对象
TrafficViolateClient client = new TrafficViolateClient();
string result = client.Pecc("粤AS8T03", "2", "074686", "D15140");

Assert.AreEqual("{\"message\":\"查询违章成功\",\"result\":0,\"data\":[],\"rowCount\":0}", result);
```



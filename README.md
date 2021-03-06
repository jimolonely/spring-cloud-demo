# spring-cloud demo

make by jimo


## eureka-server
服务注册和发现

## router
路由+过滤+负载均衡

Zuul自带负载均衡

## app1
模拟消费者：
1. 访问app2，验证负载均衡
2. 访问app2，验证断路器

## app2
模拟服务生产者，更换端口启动多个应用

作为config-client，测试配置的动态更新

## config-server
配置中心服务，管理所有服务的配置

本demo采用本地git仓库的形式。

### 中心化配置
手动刷新每个应用：[https://spring.io/guides/gs/centralized-configuration/](https://spring.io/guides/gs/centralized-configuration/)

使用spring actuator刷新：
```shell 
$ curl localhost:8083/actuator/refresh -d {} -H "Content-Type: application/json"
```
### 批量更新
[https://www.cnblogs.com/wslook/p/9994915.html](https://www.cnblogs.com/wslook/p/9994915.html)
使用bus批量更新配置，采用默认的RabbitMQ，使用docker pull一个：
```shell 
docker run -d --hostname my-rabbit --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
[https://my.oschina.net/u/2518341/blog/2989307](https://my.oschina.net/u/2518341/blog/2989307)

刷新接口：只需要刷新config server就行,注意是post请求
```shell 
$ curl localhost:8888/actuator/bus-refresh -d {} -H "Content-Type: application/json"
```


## gateway
[https://spring.io/guides/gs/gateway/](https://spring.io/guides/gs/gateway/)

`/get`访问：
```json 
http://localhost:8887/get
{
  "args": {}, 
  "headers": {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8", 
    "Accept-Encoding": "gzip, deflate, br", 
    "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8", 
    "Connection": "close", 
    "Cookie": "_ga=GA1.1.134524732.1532668650", 
    "Dnt": "1", 
    "Forwarded": "proto=http;host=\"localhost:8887\";for=\"0:0:0:0:0:0:0:1%0:51270\"", 
    "Hello": "jimo", 
    "Host": "httpbin.org", 
    "Upgrade-Insecure-Requests": "1", 
    "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36", 
    "X-Forwarded-Host": "localhost:8887"
  }, 
  "origin": "0:0:0:0:0:0:0:1%0, 124.xxx.xx.xxx", 
  "url": "http://localhost:8887/get"
}
```

超时访问：
```shell
 curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:8887/delay/3
HTTP/1.1 504 Gateway Timeout
Content-Type: application/json;charset=UTF-8
Content-Length: 158

{"timestamp":"2018-12-19T06:59:50.728+0000","path":"/delay/3","status":504,"error":"Gateway Timeout","message":"Response took longer than configured timeout"}j
```
设置断路器后：
```shell 
 curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:8887/delay/3
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 8

fallback
```

## 分布式跟踪
基于spring cloud sleuth，需要有一个zipkin服务器在运行，推荐用docker运行。

```shell 
docker run -d -p 9411:9411 openzipkin/zipkin
```

参考文章：[https://my.oschina.net/orrin/blog/2906795](https://my.oschina.net/orrin/blog/2906795)

本demo中，app1，app2，router添加了跟踪依赖。



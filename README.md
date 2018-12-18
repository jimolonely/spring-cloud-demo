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





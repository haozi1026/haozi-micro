# 模块说明

haozi-account :账号管理

haozi-auth:认证授权

haozi-common:公共

haozi-gw:网关

haozi-register:注册中心

haozi-support:支撑

​	account-manage:管理登录后的账号信息

​	global-expection-handler:全局异常处理

​	mysql-support: mysql+mybatis 等支持

​	open-fegin-support:fegin支持

​	security-support:权限校验

# 功能支持

## mysql-support

除了集成Mybatis-plus外，提供对雪花算法的支持，在实体类上要生成雪花id的字段上加@SnowId即可

```java
@SnowId
private Long resourcesId;
```








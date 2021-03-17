## 实现功能
1、共享登录用户信息

对所有引入了common组件的服务，在接收到任何请求后都会首先从header中获取登录用户的信息，
并将用户信息放到ThreadLocal中，以确保在任何地方都能获取到当前用户信息
    
    注意：导入common组件的服务的启动类需要加@CompentScan注解，否则不会生效
2、集成feign服务间调用功能

在需要调用其他服务的服务启动类上添加@EnableFeignClients注解即可使用，common组件完成了请求携带用户信息
和请求异常处理，请求出现异常回抛出InternalException异常

3、全局异常处理和数据统一返回

编码实现过程中出现的义务异常一律使用BusinessException，所有接口返回数据一律使用Result类，对于业务中出错
需要返回前端错误信息的，可以在各自模块继承ResultCode类，统一管理业务异常

4、分布式的ID生成器
使用ID生成器时只需要自动注入IdWorker，调用nextId()方法即可生成18位的id，前提是务必要在配置文件中正确配置
spring.worker.datacenterId和spring.worker.machineId参数，否则无法使用，datacenterId代表机房ID，5bit
表示，最大支持32个机房，machineId是机器ID，也是5bit表示，最大支持同一机房部署32台机器。
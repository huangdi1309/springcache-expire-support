# springcache
springcache是spring框架自带的一套缓存框架，简化了使用redis作为缓存的使用方式。

# springcache-expire-support
因为springcache不支持设置单个key的过期时间， 此工程支持按key设置过期时间

# 版本要求
spring-boot 2.0以上，项目使用的版本是2.6.4  
spring-data-redis：2.6.2 （注意1.x.x不一定适用）

# 演示效果图
设置了key的过期时间  
![image](https://github.com/huangdi1309/springcache-expire-support/assets/27601181/806ed26c-b210-4771-a12d-8c1ef21154c8)  

以下是没有设置key过期时间  
![image](https://github.com/huangdi1309/springcache-expire-support/assets/27601181/3ac3aad9-0c60-4db3-b43b-cedb925bdb0e)




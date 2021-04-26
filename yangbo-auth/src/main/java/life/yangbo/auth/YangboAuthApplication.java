package life.yangbo.auth;

import life.yangbo.common.annotation.EnableFebsAuthExceptionHandler;
import life.yangbo.common.annotation.EnableFebsLettuceRedis;
import life.yangbo.common.annotation.EnableFebsServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableFebsLettuceRedis 开启连接redis的工具
 * @MapperScan("life.yangbo.auth.mapper") 将路径下的接口注入到容器中
 * @EnableFebsServerProtect 校验是否携带Gateway颁布的token从而实现内部服务保护
 * @EnableFebsAuthExceptionHandler 使用common包下的自定义配置处理类处理资源401、403异常（令牌不正确返回401和用户无权限返回403）
 */
@EnableFebsLettuceRedis
@MapperScan("life.yangbo.auth.mapper")
@EnableFebsServerProtect
@EnableFebsAuthExceptionHandler
@EnableDiscoveryClient
@SpringBootApplication
public class YangboAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboAuthApplication.class, args);
    }

}

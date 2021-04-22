package life.yangbo.server.test;

import life.yangbo.common.annotation.EnableFebsAuthExceptionHandler;
import life.yangbo.common.annotation.EnableFebsOauth2FeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @EnableFebsOauth2FeignClient 解决feign header头部丢失问题
 * @EnableFeignClients 开启feign调用
 * @EnableDiscoveryClient 开启服务注册与发现
 * @EnableGlobalMethodSecurity(prePostEnabled = true) 表示开启Spring Cloud Security权限注解
 * @EnableFebsAuthExceptionHandler 开启资源异常处理（401、403）配置，需要结合FebsServerTestResourceServerConfigure配置后才能生效
 */
@EnableFebsOauth2FeignClient
@EnableFeignClients
@EnableFebsAuthExceptionHandler
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class YangboServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboServerTestApplication.class, args);
    }

}

package life.yangbo.server.system;

import life.yangbo.common.annotation.EnableFebsAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @EnableFebsAuthExceptionHandler 开启资源异常处理（401、403）配置，需要结合FebsServerTestResourceServerConfigure配置后才能生效
 * @EnableGlobalMethodSecurity true表示开启Spring Cloud Security权限注解
 */
@EnableFebsAuthExceptionHandler
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@SpringBootApplication
public class YangboServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboServerSystemApplication.class, args);
    }

}

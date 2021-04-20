package life.yangbo.server.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @EnableDiscoveryClient 开启服务注册与发现
 * @EnableGlobalMethodSecurity(prePostEnabled = true) 表示开启Spring Cloud Security权限注解
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class YangboServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboServerTestApplication.class, args);
    }

}

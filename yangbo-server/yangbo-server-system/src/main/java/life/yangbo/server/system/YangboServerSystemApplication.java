package life.yangbo.server.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @EnableGlobalMethodSecurity true表示开启Spring Cloud Security权限注解
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@SpringBootApplication
public class YangboServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboServerSystemApplication.class, args);
    }

}

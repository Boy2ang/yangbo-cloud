package life.yangbo.auth;

import life.yangbo.common.annotation.EnableFebsAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableFebsAuthExceptionHandler 使用common包下的自定义配置处理类处理资源401、403异常（令牌不正确返回401和用户无权限返回403）
 */
@EnableFebsAuthExceptionHandler
@EnableDiscoveryClient
@SpringBootApplication
public class YangboAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboAuthApplication.class, args);
    }

}

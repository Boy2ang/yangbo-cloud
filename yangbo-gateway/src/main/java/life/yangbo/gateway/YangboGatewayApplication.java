package life.yangbo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @EnableZuulProxy 开启网关功能
 * @EnableDiscoveryClient 开启服务注册与发现
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class YangboGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboGatewayApplication.class, args);
    }

}

package life.yangbo.server.system;

import life.yangbo.common.annotation.EnableFebsAuthExceptionHandler;
import life.yangbo.common.annotation.EnableFebsOauth2FeignClient;
import life.yangbo.common.annotation.EnableFebsServerProtect;
import life.yangbo.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableTransactionManagement 让事务注解生效
 * @MapperScan 扫描mapper包下的接口，并注入到容器
 * @FebsCloudApplication 替代@EnableFebsServerProtect；@EnableFebsOauth2FeignClient；@EnableFebsAuthExceptionHandler三个注解
 * @EnableFebsServerProtect 校验是否携带Gateway颁布的token从而实现内部服务保护
 * @EnableFebsOauth2FeignClient 解决feign调用丢失header问题
 * @EnableFebsAuthExceptionHandler 开启资源异常处理（401、403）配置，需要结合FebsServerTestResourceServerConfigure配置后才能生效
 * @EnableGlobalMethodSecurity true表示开启Spring Cloud Security权限注解
 */
@EnableTransactionManagement
@MapperScan("life.yangbo.server.system.mapper")
@FebsCloudApplication
// @EnableFebsServerProtect
// @EnableFebsOauth2FeignClient
// @EnableFebsAuthExceptionHandler
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@SpringBootApplication
public class YangboServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboServerSystemApplication.class, args);
    }

}

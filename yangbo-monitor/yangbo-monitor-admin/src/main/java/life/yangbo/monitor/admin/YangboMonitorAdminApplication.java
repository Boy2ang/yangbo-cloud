package life.yangbo.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @EnableAdminServer 开启Spring Boot Admin服务端功能
 */
@EnableAdminServer
@SpringBootApplication
public class YangboMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboMonitorAdminApplication.class, args);
    }

}

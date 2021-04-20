package life.yangbo.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author yangbo
 */
@EnableEurekaServer
@SpringBootApplication
public class YangboRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboRegisterApplication.class, args);
    }

}

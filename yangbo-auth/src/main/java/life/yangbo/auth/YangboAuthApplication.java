package life.yangbo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class YangboAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangboAuthApplication.class, args);
    }

}

package life.yangbo.server.test.controller;

import life.yangbo.server.test.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class TestController {

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1() {
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2() {
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        log.info("i am coming...");
        return principal;
    }

    @GetMapping("over-time")
    public String testovertime() {
        try {
            // 模拟耗时10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return "测试超时";
    }

    /**
     * 远程调用server-system
     */
    @Autowired
    private IHelloService helloService;

    @GetMapping("hello")
    public String hello(String name) {
        log.info("Feign调用febs-server-system的/hello服务");
        return this.helloService.hello(name);
    }

    @GetMapping("good")
    public String good() {
        log.info("test的goot方法被调用啦");
        return "success";
    }
}

package life.yangbo.server.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class TestController {

    @GetMapping("info")
    public String test() {
        return "febs-server-system";
    }

    /**
     * 获取当前用户信息
     * @param principal
     * @return
     */
    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        log.info("system服务 获取当前用户信息");
        return principal;
    }

    /**
     * 供feign远程调用
     * @param name
     * @return
     */
    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用");
        //log.warn("/hello服务被调用");
        //log.error("/hello服务被调用");
        return "hello" + name;
    }
}

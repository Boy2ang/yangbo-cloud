package life.yangbo.common.configure;


import life.yangbo.common.interceptor.FebsServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 */
public class FebsServerProtectConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        return new FebsServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(febsServerProtectInterceptor());
    }
}

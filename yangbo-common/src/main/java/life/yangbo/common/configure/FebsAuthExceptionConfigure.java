package life.yangbo.common.configure;

import life.yangbo.common.handler.FebsAccessDeniedHandler;
import life.yangbo.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class FebsAuthExceptionConfigure {

    /**
     * @ConditionalOnMissingBean 当IOC容器中没有指定名称或类型的Bean的时候，就注册它
     * 这样做的好处在于，子系统可以自定义自个儿的资源服务器异常处理器，覆盖我们在febs-common通用模块里定义的。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler() {
        return new FebsAccessDeniedHandler();
    }

    /**
     * @ConditionalOnMissingBean 当IOC容器中没有指定名称或类型的Bean的时候，就注册它
     * 这样做的好处在于，子系统可以自定义自个儿的资源服务器异常处理器，覆盖我们在febs-common通用模块里定义的。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public FebsAuthExceptionEntryPoint authenticationEntryPoint() {
        return new FebsAuthExceptionEntryPoint();
    }
}

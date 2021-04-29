package life.yangbo.server.test.configure;

import life.yangbo.common.handler.FebsAccessDeniedHandler;
import life.yangbo.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class FebsServerTestResourceServerConfigure extends ResourceServerConfigurerAdapter {

    /**
     * 处理资源异常403 用户无权限
     */
    @Autowired
    private FebsAccessDeniedHandler accessDeniedHandler;
    /**
     * 处理资源异常 401 令牌不正确返回
     */
    @Autowired
    private FebsAuthExceptionEntryPoint exceptionEntryPoint;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                // 放行监控接口
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**").authenticated();
    }

    /**
     * 重写注入两个异常处理
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}

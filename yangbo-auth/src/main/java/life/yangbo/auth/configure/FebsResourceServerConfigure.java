package life.yangbo.auth.configure;

import life.yangbo.auth.properties.FebsAuthProperties;
import life.yangbo.common.handler.FebsAccessDeniedHandler;
import life.yangbo.common.handler.FebsAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * ResourceServerConfigurerAdapter表示资源服务器配置类
 * 用于处理非/oauth/开头的请求，其主要用于资源的保护，客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源。
 */
@Configuration
@EnableResourceServer
public class FebsResourceServerConfigure extends ResourceServerConfigurerAdapter {

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

    /**
     * 配置文件
     */
    @Autowired
    private FebsAuthProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated()
                .and().httpBasic();
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
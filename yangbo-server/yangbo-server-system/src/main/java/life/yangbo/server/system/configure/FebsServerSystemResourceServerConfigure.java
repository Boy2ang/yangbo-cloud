package life.yangbo.server.system.configure;

import life.yangbo.common.handler.FebsAccessDeniedHandler;
import life.yangbo.common.handler.FebsAuthExceptionEntryPoint;
import life.yangbo.server.system.properties.FebsServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置类
 */
@Configuration
@EnableResourceServer
public class FebsServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

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
     * swagger免认证的配置
     */
    @Autowired
    private FebsServerSystemProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 免认证的路径
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                // 放行路径
                .antMatchers(anonUrls).permitAll()
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

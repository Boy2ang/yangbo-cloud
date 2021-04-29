package life.yangbo.auth.configure;

import life.yangbo.auth.filter.ValidateCodeFilter;
import life.yangbo.auth.service.FebsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，刷新令牌的请求地址都是以/oauth/开头的，
 * 也就是说FebsSecurityConfigure用于处理和令牌相关的请求；
 * order表示优先级，数字越小越优先执行，手动写个2的目的是让FebsSecurityConfigure比FebsResourceServerConfigure优先执行
 * 实现的效果：以/oauth/开头的请求由FebsSecurityConfigure过滤器链处理，剩下的其他请求由FebsResourceServerConfigure过滤器链处理。
 */
@Order(2)
@EnableWebSecurity
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private FebsUserDetailService userDetailService;

    /**
     * 密码加密，从common公共包中注入
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 检验验证码的过滤器
     */
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // UsernamePasswordAuthenticationFilter过滤器执行前先执行验证码校验
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // FebsSecurityConfigure安全配置类只对/oauth/开头的请求有效。
                .requestMatchers().antMatchers("/oauth/**")
                .and()
                // 访问
                .authorizeRequests().antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
package life.yangbo.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Auth相关配置类
 * @PropertySource 指定读取的配置文件路径
 * @ConfigurationProperties 指定了要读取的属性的统一前缀名称
 * @SpringBootConfiguration 实质上为@Component的派生注解，用于将FebsAuthProperties纳入到IOC容器中。
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:febs-auth.properties"})
@ConfigurationProperties(prefix = "febs.auth")
public class FebsAuthProperties {
    /**
     * 因为一个认证服务器可以根据多种Client来发放对应的令牌,所以使用数组形式
     */
    private FebsClientsProperties[] clients = {};
    /**
     * 访问令牌有效时间
     */
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    /**
     * 刷新令牌有效时间
     */
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    /**
     * 免认证路径
     */
    private String anonUrl;

    //验证码配置类
    private FebsValidateCodeProperties code = new FebsValidateCodeProperties();
}

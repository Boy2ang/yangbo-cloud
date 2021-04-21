package life.yangbo.auth.properties;

import lombok.Data;

/**
 * Client配置类
 */
@Data
public class FebsClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}

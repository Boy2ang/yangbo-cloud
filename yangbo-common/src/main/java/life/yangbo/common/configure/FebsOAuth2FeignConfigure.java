package life.yangbo.common.configure;

import feign.RequestInterceptor;
import life.yangbo.common.entity.FebsConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

public class FebsOAuth2FeignConfigure {

    /**
     * feign调用的拦截器，用来解决feign调用丢失header的问题
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {

            // 添加Zuul Token
            String zuulToken = new String(Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(FebsConstant.ZUUL_TOKEN_HEADER, zuulToken);


            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}

package life.yangbo.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域处理
 */
@Configuration
public class FebsGateWayCorsConfigure {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许cookie跨域
        corsConfiguration.setAllowCredentials(true);
        // 表示请求头允许携带任何内容
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 表示允许任何来源
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 表示允许任何http方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}

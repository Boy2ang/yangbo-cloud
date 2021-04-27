package life.yangbo.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import life.yangbo.server.system.properties.FebsServerSystemProperties;
import life.yangbo.server.system.properties.FebsSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @EnableSwagger2 开启swagger功能
 */
@Configuration
@EnableSwagger2
public class FebsWebConfigure {

    @Autowired
    private FebsServerSystemProperties properties;

    /**
     * mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /**
     * swagger配置
     * @return
     */
    @Bean
    public Docket swaggerApi() {
        FebsSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 路径下的所有Controller都添加进去
                .apis(RequestHandlerSelectors.basePackage("life.yangbo.server.system.controller"))
                // Controller里的所有方法都纳入
                .paths(PathSelectors.any())
                .build()
                // 用于定义一些API页面信息，比如作者名称，邮箱，网站链接，开源协议等等。
                .apiInfo(apiInfo(swagger));
    }

    /**
     * swagger定义一些API页面信息，比如作者名称，邮箱，网站链接，开源协议等等。
     * @return
     */
    private ApiInfo apiInfo(FebsSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }
}

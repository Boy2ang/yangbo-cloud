package life.yangbo.common.selector;

import life.yangbo.common.configure.FebsAuthExceptionConfigure;
import life.yangbo.common.configure.FebsOAuth2FeignConfigure;
import life.yangbo.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 将多个配置同时导入，实现功能整合（启动类上面配置注解太多，不美观）
 */
public class FebsCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                // 认证类型异常翻译
                FebsAuthExceptionConfigure.class.getName(),
                // 开启带令牌的Feign请求，避免内部出现401
                FebsOAuth2FeignConfigure.class.getName(),
                // 避免客户端绕过网关直接请求内部微服务
                FebsServerProtectConfigure.class.getName()
        };
    }
}

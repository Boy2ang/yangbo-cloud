package life.yangbo.common.annotation;

import life.yangbo.common.configure.FebsAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Import(FebsAuthExceptionConfigure.class) 将FebsAuthExceptionConfigure配置类引入了进来。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsAuthExceptionConfigure.class)
public @interface EnableFebsAuthExceptionHandler {

}

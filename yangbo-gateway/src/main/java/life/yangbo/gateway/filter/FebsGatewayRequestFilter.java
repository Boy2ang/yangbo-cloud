package life.yangbo.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import life.yangbo.common.entity.FebsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义过滤器，实现在网关转发之前，请求头部加入网关信息（其他服务通过拦截器校验头部，只有接收到有网关的头部信息才能响应从而达到微服务防护）
 * 即其他服务只能通过网关调用
 */
@Slf4j
@Component
public class FebsGatewayRequestFilter extends ZuulFilter {

    /**
     * 对应Zuul生命周期的四个阶段：pre、post、route和error，我们要在请求转发出去前添加请求头，所以这里指定为pre；
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的优先级，数字越小，优先级越高。PreDecorationFilter过滤器的优先级为5，所以我们可以指定为6让我们的过滤器优先级比它低；
     * @return
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 方法返回boolean类型，true时表示是否执行该过滤器的run方法，false则表示不执行；
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 添加头信息
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 打印日志
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        // byte[] token = Base64Utils.encode(("yangbo:zuul:123456").getBytes());
        byte[] token = Base64Utils.encode((FebsConstant.ZUUL_TOKEN_VALUE).getBytes());
        // 添加头信息
        ctx.addZuulRequestHeader(FebsConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}

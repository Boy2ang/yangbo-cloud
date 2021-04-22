package life.yangbo.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import life.yangbo.common.entity.FebsConstant;
import life.yangbo.common.entity.FebsResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 只有含有网关颁发的头部信息才能放行请求，即实现了外部不能通过ip直接访问
 */
public class FebsServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(FebsConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            // 返回403
            FebsResponse febsResponse = new FebsResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(febsResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}

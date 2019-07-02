package com.wangzhou.gateway.filiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/22
 * Time:19:21
 **/
@Component
public class TokenFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //越小的优先级越高
        return PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext  requestContext=RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest=requestContext.getRequest();
        //从url参数里获取，也可以从cookie，header里获取
       String token=httpServletRequest.getParameter("token");
       if(StringUtils.isEmpty(token)){
           //表示不通过
           requestContext.setSendZuulResponse(false);
           //设置状态码
           requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
           System.out.println("token"+token);
       }
        return HttpStatus.SC_UNAUTHORIZED;
    }
}

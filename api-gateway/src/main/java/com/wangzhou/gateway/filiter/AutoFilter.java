//package com.wangzhou.gateway.filiter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.wangzhou.gateway.constant.CookieConstant;
//import com.wangzhou.gateway.util.CookieUtil;
//import org.apache.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
///**
// * 权限拦截（区分买家和卖家）
// * Created by IDEA
// * author:wangzhou
// * Data:2018/10/22
// * Time:19:21
// **/
//@Component
//public class AutoFilter extends ZuulFilter {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        //越小的优先级越高
//        return PRE_DECORATION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest httpServletRequest = requestContext.getRequest();
//        /**
//         * /order/create   只能买家访问（cookie里有openid）
//         * /order/finish   只能卖家访问（cookie里）
//         * /product/list    都能访问
//         */
//        if ("/order/order/create".equals(httpServletRequest.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(httpServletRequest, "openid");
//            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
//                //返回没有1
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//
//        if ("/order/order/finish".equals(httpServletRequest.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(httpServletRequest, "token");
//            if (cookie == null||StringUtils.isEmpty(cookie.getValue())
//                    ||StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(CookieConstant.TOKEN,cookie.getValue())))){
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//
//        return null;
//    }
//}

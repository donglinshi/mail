package com.cskaoyan.mail.filter;

import com.cskaoyan.mail.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 史栋林
 * @date 2020/8/10 18:35
 */
@WebFilter("/api/mall/*")
public class MallFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");

        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        if (!request.getMethod().equalsIgnoreCase("OPTIONS")){
            if (auth(request)){
                //需要验证权限
                String username = (String) request.getSession().getAttribute("username");

                if (username == null){
                    //没有登录需要拦截
                    response.getWriter().println(new Gson().toJson(Result.error("当前接口需要登录才能访问！")));
                    return;
                }
            }
        }

        chain.doFilter(req, resp);
    }

    private boolean auth(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if ("/api/mall/user/login".equalsIgnoreCase(requestURI) || "/api/mall/user/logout".equalsIgnoreCase(requestURI) ||
        "/api/mall/index/getType".equalsIgnoreCase(requestURI) || "/api/mall/goods/getGoodsByTypr".equalsIgnoreCase(requestURI) ||
        "/api/mall/goods/searchGoods".equalsIgnoreCase(requestURI) || "/api/mall/goods/getGoodsMsg".equalsIgnoreCase(requestURI) ||
        "/api/mall/goods/getGoodsComment".equalsIgnoreCase(requestURI)){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

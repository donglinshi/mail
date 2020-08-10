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
 * @date 2020/8/5 18:34
 */
@WebFilter("/api/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        if (!request.getMethod().equalsIgnoreCase("OPTIONS")){
            //不对options请求做出拦截,因为不携带cookies
            if (auth(request)){
                //需要验证权限
                String username = (String) request.getSession().getAttribute("username");
                if (username == null){
                    //没有登录,需要拦截
                    response.getWriter().println(new Gson().toJson(Result.error("访问当前接口需要登录！")));
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    private boolean auth(HttpServletRequest request) {
        if ("/api/admin/admin/login".equalsIgnoreCase(request.getRequestURI())
                || "/api/admin/admin/logoutAdmin".equalsIgnoreCase(request.getRequestURI())){
            //不需要检测是否拦截
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

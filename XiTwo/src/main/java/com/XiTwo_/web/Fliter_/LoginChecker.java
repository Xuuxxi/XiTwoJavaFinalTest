package com.XiTwo_.web.Fliter_;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginChecker implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpSession session = req.getSession();
        String uri = req.getRequestURI();

        if(uri.contains("/LoginServlet") || uri.contains("/RegisterServlet") || uri.contains("Register")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            Object user = session.getAttribute("user");
            if(user != null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                req.setAttribute("Login_error","未登录！");
                req.getRequestDispatcher("/Login").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

package com.controller;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import javax.annotation.processing.Filer;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "Auth", urlPatterns = {"*.xhtml"})
public class Auth implements Filter{
    public Auth(){
    }
    @Override
    public void init(FilterConfig fileterConfig) throws ServletException {
        
    }
    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        try{
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            
            String reqURI = reqt.getRequestURI();
            if(reqURI.indexOf("/index.xhtml")>=0 || (ses !=null && ses.getAttribute("correo")!=null) || reqURI.indexOf("/public/")>=0 || reqURI.contains("javac.faces.resources")){
                chain.doFilter(request, response);
            }else{
                resp.sendRedirect(reqt.getContextPath()+"/faces/index.xhtml");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void destroy(){
    }
}

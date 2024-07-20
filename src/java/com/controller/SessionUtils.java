package com.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.*;

public class SessionUtils {
    public static HttpSession getSession(){
        return(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    public static String getCorreo(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("correo").toString();
    }
    public static String getId_usuario(){
        HttpSession session = getSession();
        if(session!=null){
            return (String) session.getAttribute("id_usuario");
        }else{
            return null;
        }
    }
}

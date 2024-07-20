
package com.controller;

import com.datos.query;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.annotation.ManageBean;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.context.RequestContext;
import javax.servlet.http.*;

@ManagedBean (name="login")
@SessionScoped
public class loginController implements Serializable{
    private String correo;
    private String contra;
    private final query consulta= new query();
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    public String controlLogin(){
        if(consulta.controlLogin(correo, contra)){
            HttpSession session= SessionUtils.getSession();
            session.getAttribute("correo",correo);
            return "/Vistas/cursos";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(correo,new FacesMessages(FacesMessage.SEVERITY_ERROR,"Error","Correo o contraseña incorrecta"));
        return "";
    }
     
}

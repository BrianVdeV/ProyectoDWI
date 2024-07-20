package com.datos;

import com.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
        
public class query {
    EntityManagerFactory emf;
    EntityManager em;

    public query() {
        emf= Persistence.createEntityManagerFactory("ProyectoDWIPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    public boolean controlLogin(String correo, String contra){
        try{
            Usuario usuario=em.createNamedQuery("Usuario.control",Usuario.class).setParameter("correo", correo).setParameter("contra", contra).getSingleResult();
            if(usuario!=null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }finally{
            emf.close();
        }
    }
}

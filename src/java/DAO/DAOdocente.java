package DAO;

import Persistencia.*;
import Modelo.*;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOdocente {
    
    private Conexion conexion = new Conexion();
    private DTOdocente est = new DTOdocente();
 
    public DAOdocente() {
    }

    public ArrayList<DTOdocente> ListaDocentes() {
        ArrayList<DTOdocente> Lista = new ArrayList();
        String consulta = "select * from docente";
        try {
            Statement st = conexion.getCon().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                DTOdocente est = new DTOdocente();
                est.setId_docente(rs.getInt(1));
                est.setApellido(rs.getString(2));
                est.setNombre(rs.getString(3));
                est.setDni(rs.getInt(4));
                est.setSexo(rs.getString(5));
                est.setFecha(rs.getString(6));
                Lista.add(est);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al recuperar docentes..." + ex);
        }
        return Lista;
    }//fin de listadocente

     public boolean agregarDocente() {
        String sql = "insert into docente (apellido,nombre,dni,sexo,fecha) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, est.getApellido());
            ps.setString(2, est.getNombre());
            ps.setInt(3, est.getDni());
            ps.setString(4, est.getSexo());
            ps.setString(5, est.getFecha());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede agregar.." + ex);
        }
        return false;
    }//fin agregar  
     
    public boolean actualizarDocente() {
        String sql = "update docente set apellido=?, nombre=?,dni=?,sexo=?,fecha=? where id_docente=?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, est.getApellido());
            ps.setString(2, est.getNombre());
            ps.setInt(3, est.getDni());
            ps.setString(4, est.getSexo());
            ps.setString(5, est.getFecha());
            ps.setInt(6, est.getId_docente());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede actualizar.." + ex);
        }
        return false;
    }//fin actualizar

    public void editarDocente (DTOdocente docente) {
        est = docente;
    }//fin editar

    public boolean eliminarDocente(String id) {
        String sql = "delete from docente where id_docente=?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se pude eliminar");
        }
        return false;
    }//fin eliminar
    //getter y setter

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public DTOdocente getEst() {
        return est;
    }

    public void setEst(DTOdocente est) {
        this.est = est;
    }
    
}  


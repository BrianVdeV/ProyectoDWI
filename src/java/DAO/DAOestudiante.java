package DAO;

import Persistencia.*;
import Modelo.*;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOestudiante{

    private Conexion conexion = new Conexion();
    private DTOestudiante est = new DTOestudiante();

    public DAOestudiante() {
    }

    public ArrayList<DTOestudiante> ListaEstudiantes() {
        ArrayList<DTOestudiante> Lista = new ArrayList();
        String consulta = "select * from estudiante";
        try {
            Statement st = conexion.getCon().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                DTOestudiante est = new DTOestudiante();
                est.setId_estudiante(rs.getInt(1));
                est.setApellido(rs.getString(2));
                est.setNombre(rs.getString(3));
                est.setDni(rs.getInt(4));
                est.setSexo(rs.getString(5));
                est.setFecha(rs.getString(6));
                est.setResponsable(rs.getString(7));
                Lista.add(est);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al recuperar estudiantes..." + ex);
        }
        return Lista;
    }//fin de listaestudiante

    public boolean agregarEstudiante() {
        String sql = "insert into estudiante(apellido,nombre,dni,sexo,fecha,responsable) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, est.getApellido());
            ps.setString(2, est.getNombre());
            ps.setInt(3, est.getDni());
            ps.setString(4, est.getSexo());
            ps.setString(5, est.getFecha());
            ps.setString(6, est.getResponsable());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede agregar.." + ex);
        }
        return false;
    }//fin agregar   

    public boolean actualizarEstudiante() {
        String sql = "update estudiante set apellido=?, nombre=?,dni=?,sexo=?,fecha=?,responsable=? where id_estudiante=?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, est.getApellido());
            ps.setString(2, est.getNombre());
            ps.setInt(3, est.getDni());
            ps.setString(4, est.getSexo());
            ps.setString(5, est.getFecha());
            ps.setString(6, est.getResponsable());
            ps.setInt(7, est.getId_estudiante());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede actualizar.." + ex);
        }
        return false;
    }//fin actualizar

    public void editarEstudiante(DTOestudiante estudiante) {
        est = estudiante;
    }//fin editar

    public boolean eliminarEstudiante(String id) {
        String sql = "delete from estudiante where id_estudiante=?";
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

    public DTOestudiante getCar() {
        return est;
    }

    public void setCar(DTOestudiante est) {
        this.est = est;
    }
}//fin de la clase

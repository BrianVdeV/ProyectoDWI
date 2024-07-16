package DAO;

import Persistencia.*;
import Modelo.*;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOcurso{

    private Conexion conexion = new Conexion();
    private DTOcurso cur = new DTOcurso();

    public DAOcurso() {
    }

    public ArrayList<DTOcurso> ListaCursos() {
        ArrayList<DTOcurso> Lista = new ArrayList();
        String consulta = "select * from curso";
        try {
            Statement st = conexion.getCon().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                DTOcurso cur = new DTOcurso();
                cur.setId_curso(rs.getInt(1));
                cur.setNombre(rs.getString(2));
                cur.setAnio(rs.getInt(3));
                cur.setHoras(rs.getInt(4));
                Lista.add(cur);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al recuperar cursos..." + ex);
        }
        return Lista;
    }//fin de listacurso

    public boolean agregarCurso() {
        String sql = "insert into curso(nombre,anio,horas) values(?,?,?)";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, cur.getNombre());
            ps.setInt(2, cur.getAnio());
            ps.setInt(3, cur.getHoras());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede agregar.." + ex);
        }
        return false;
    }//fin agregar   

    public boolean actualizarCurso() {
        String sql = "update curso set nombre=?,anio=?,horas=? where id_curso=?";
        try {
            PreparedStatement ps = conexion.getCon().prepareStatement(sql);
            ps.setString(1, cur.getNombre());
            ps.setInt(2, cur.getAnio());
            ps.setInt(3, cur.getHoras());
            ps.setInt(4, cur.getId_curso());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR no se puede actualizar.." + ex);
        }
        return false;
    }//fin actualizar

    public void editarCurso(DTOcurso curso) {
        cur = curso;
    }//fin editar

    public boolean eliminarCurso(String id) {
        String sql = "delete from curso where id_curso=?";
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

    public DTOcurso getCar() {
        return cur;
    }

    public void setCar(DTOcurso cur) {
        this.cur = cur;
    }
}//fin de la clase

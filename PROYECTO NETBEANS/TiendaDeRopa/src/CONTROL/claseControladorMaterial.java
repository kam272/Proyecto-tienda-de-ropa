/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.claseMaterial;
import MODEL.claseConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseControladorMaterial {
    
     private claseConexion conexionBD = new claseConexion();
     

    public List listAll(){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseMaterial> listadomaterial = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblmaterial");
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseMaterial material = new claseMaterial();
             material.setId(datos.getInt("id"));
             material.setNombre(datos.getString("nombre"));
             
            listadomaterial.add(material);
            
            }
            return listadomaterial;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"); 
            return listadomaterial;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
        public int findID(String nombre){
       int id = 0;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT id From tblmaterial WHERE nombre=?";
       consulta = miConexion.prepareStatement(sql);
       consulta.setString(1, nombre);
       datos = consulta.executeQuery();
            while (datos.next()) {                
                id= datos.getInt("id") ;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
        }
        return id;
    }
    
     public String findName(int id){
       String nombre = null;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT nombre From tblmaterial WHERE id=?";
       consulta = miConexion.prepareStatement(sql);
       consulta.setInt(1, id);
       datos = consulta.executeQuery();
            while (datos.next()) {                
                nombre= datos.getString("nombre") ;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
        }
      
       
        return nombre;
    }
     
      public String insert(claseMaterial material) {
        String mensaje = null; 
        
        try {
            Connection miConexion = conexionBD.getConnection();
            String sql ="INSERT INTO tblmaterial (nombre) VALUES (?)";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
            insertar.setString(1, material.getNombre());
            
            insertar.execute();
            mensaje = "Registro Guardado exitosamente";
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer la insercion"+e.getMessage());
            mensaje = "No se pudo conectar o hacer la insercion";
        }finally{
            conexionBD.setDesconectar();
        }
        
        return mensaje;
    }
      
     public String destroy(int id) {
        String mensaje = null; 
        PreparedStatement borrar;

        try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql ="DELETE FROM tblmaterial WHERE id=?";
                borrar = miConexion.prepareStatement(sql);
                borrar.setInt(1, id);
              borrar.executeUpdate();
            
          
            mensaje = "Registro eliminado exitosamente";
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer la eliminacion"+e.getMessage());
            mensaje = "No se pudo conectar o hacer la eliminacion";
        }
        finally{
            conexionBD.setDesconectar();
        }
        return mensaje;
    }
     
     public String edit(claseMaterial material){
         String mensaje = null;
           try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql = "UPDATE tblmaterial SET nombre=? WHERE id=?";
            PreparedStatement actualizar = miConexion.prepareStatement(sql);
            actualizar.setString(1,material.getNombre());
            actualizar.setInt(2, material.getId());
            actualizar.execute();
            
             mensaje = "Registro actualizado exitosamente";
            
        } catch (SQLException e) {
              System.err.println("No se pudo conectar o hacer la actualizacion"+e.getMessage()); 
               mensaje = "No se pudo conectar o hacer la actualizacion";
        }finally{
            conexionBD.setDesconectar();
        }
         return mensaje;
    }   
     
}

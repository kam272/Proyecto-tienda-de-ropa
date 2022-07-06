/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.claseConexion;
import MODEL.claseVenta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class claseControladorVenta {
       private claseConexion conexionBD = new claseConexion();
    
    public List listAll(){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseVenta> listadoventas = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblventa");
            
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseVenta venta = new claseVenta();
             venta.setCodigo(datos.getInt("cod"));
             venta.setFecha(datos.getString("fecha"));
             venta.setIdCliente(datos.getInt("idCliente"));
            venta.setFechaCreacion(datos.getString("fechaCreacion"));
            venta.setFechaModificacion(datos.getString("fechaModificacion"));
          
            listadoventas.add(venta);
            
            }
            return listadoventas;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"); 
            return listadoventas;
        }finally{
            conexionBD.setDesconectar();
        }
        
    } 
    
        public List Find(int codigo){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseVenta> listadoventas = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblventa WHERE cod=?");
            consulta.setInt(1,codigo);
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseVenta venta = new claseVenta();
             venta.setCodigo(datos.getInt("cod"));
             venta.setFecha(datos.getString("fecha"));
             venta.setIdCliente(datos.getInt("idCliente"));
            venta.setFechaCreacion(datos.getString("fechaCreacion"));
            venta.setFechaModificacion(datos.getString("fechaModificacion"));
            listadoventas.add(venta);
            
            }
            return listadoventas;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadoventas;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
         public String insert(claseVenta venta) {
        String mensaje = null; 
        
        try {
            
            Connection miConexion = conexionBD.getConnection();
            String sql ="INSERT INTO tblventa (fecha,idCliente,	fechaCreacion,fechaModificacion) VALUES (?,?,?,?)";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
            insertar.setString(1, venta.getFecha());
            insertar.setInt(2, venta.getIdCliente());
            insertar.setString(3, venta.getFechaCreacion());
            insertar.setString(4, venta.getFechaModificacion());
            
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
         
         public String edit(claseVenta venta){
         String mensaje = null;
           try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql = "UPDATE tblventa SET fecha=?,idCliente=?,fechaModificacion=? WHERE cod=?";
            PreparedStatement actualizar = miConexion.prepareStatement(sql);
            actualizar.setString(1,venta.getFecha());
            actualizar.setInt(2,venta.getIdCliente());
            actualizar.setString(3, venta.getFechaModificacion());
            actualizar.setInt(4, venta.getCodigo());
            
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
    
         public String destroy(int codigo) {
        String mensaje = null; 
        PreparedStatement borrar;

        try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql ="DELETE FROM tblventa WHERE cod=?";
                borrar = miConexion.prepareStatement(sql);
                borrar.setInt(1, codigo);
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
    
}

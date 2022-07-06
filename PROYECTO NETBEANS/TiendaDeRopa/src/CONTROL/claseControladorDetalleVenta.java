/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.claseConexion;
import MODEL.claseDetalleVenta;
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
public class claseControladorDetalleVenta {
    private claseConexion conexionBD = new claseConexion();
    
    public List Find(int numeroventa){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseDetalleVenta> listadodetallesventas = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tbldetalleventa WHERE numeroventa=?");
            consulta.setInt(1,numeroventa);
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseDetalleVenta detalle = new claseDetalleVenta();
             detalle.setConsecutivo(datos.getInt("consecutivo"));
             detalle.setNumeroventa(datos.getInt("numeroventa"));
             detalle.setCodprenda(datos.getInt("codprenda"));
             detalle.setCantidad(datos.getInt("cantidad"));
             detalle.setValorUnitario(datos.getInt("valorUnitario"));           
            listadodetallesventas.add(detalle);
            
            }
            return listadodetallesventas;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadodetallesventas;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
     public String insert(claseDetalleVenta detalleventa) {
        String mensaje = null; 
        
        try {
            
            Connection miConexion = conexionBD.getConnection();
            String sql ="INSERT INTO tbldetalleventa (numeroventa,codprenda,cantidad,valorUnitario) VALUES (?,?,?,?)";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
            
            insertar.setInt(1, detalleventa.getNumeroventa());
            insertar.setInt(2, detalleventa.getCodprenda());
            insertar.setInt(3, detalleventa.getCantidad());
            insertar.setDouble(4, detalleventa.getValorUnitario());
            
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
     
     public String destroy(int consecutivo) {
        String mensaje = null; 
        PreparedStatement borrar;

        try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql ="DELETE FROM tbldetalleventa WHERE consecutivo=?";
                borrar = miConexion.prepareStatement(sql);
                borrar.setInt(1, consecutivo);
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
     
      public String edit(claseDetalleVenta detalleventa){
         String mensaje = null;
           try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql = "UPDATE tbldetalleventa SET numeroventa=?,codprenda=?,cantidad=?,valorUnitario=? WHERE consecutivo=?";
            PreparedStatement actualizar = miConexion.prepareStatement(sql);
            actualizar.setInt(1,detalleventa.getNumeroventa());
            actualizar.setInt(2,detalleventa.getCodprenda());
            actualizar.setInt(3, detalleventa.getCantidad());
            actualizar.setDouble(4, detalleventa.getValorUnitario());  
            actualizar.setDouble(5, detalleventa.getConsecutivo());  
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

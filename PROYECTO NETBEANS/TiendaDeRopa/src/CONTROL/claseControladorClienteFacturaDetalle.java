/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.claseClienteFacturaDetalle;
import MODEL.claseConexion;
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
public class claseControladorClienteFacturaDetalle {
    
     private claseConexion conexionBD = new claseConexion();
     
    public List listAll(){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseClienteFacturaDetalle> listadoclifacdet = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tablaclientefacturadetalle");
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseClienteFacturaDetalle clifacdet = new claseClienteFacturaDetalle();
             clifacdet.setId(datos.getInt("id"));
            clifacdet.setNombreCliente(datos.getString("Nombre_cliente"));
            clifacdet.setApellidos(datos.getString("apellidos"));
            clifacdet.setCodigo_venta(datos.getInt("codigo_venta"));
            clifacdet.setConsecutivo_venta(datos.getInt("Consecutivo_de_la_venta"));
            clifacdet.setFechaCreacion(datos.getString("fechaCreacion"));
            clifacdet.setNombreprenda(datos.getString("Nombreprenda"));
            clifacdet.setCategoria(datos.getString("Categoria"));
            clifacdet.setCategoria(datos.getString("Categoria"));
            clifacdet.setCantidad(datos.getInt("cantidad"));
            clifacdet.setValorunitario(datos.getDouble("valorUnitario"));
            
            listadoclifacdet.add(clifacdet);
            
            }
            return listadoclifacdet;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadoclifacdet;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
        public List listafiltrada(String codigoventa, String consecutivoventa, String opcion) {
        PreparedStatement consulta;
        ResultSet datos;
        List<claseClienteFacturaDetalle> listaclifacdet = new ArrayList<>();
        String sql;
        try {
            Connection miConexion = conexionBD.getConnection();
            if (!codigoventa.equals("") && !consecutivoventa.equals("") && opcion.equals("Y")) {
                sql = "SELECT * FROM tablaclientefacturadetalle "
                        + "WHERE  codigo_venta like '%" +codigoventa+"%' and Consecutivo_de_la_venta like '%" + consecutivoventa + "%' "
                        + "ORDER BY fechaCreacion asc";

            } else if (!codigoventa.equals("") && !consecutivoventa.equals("") && opcion.equals("O")) {
                sql = "SELECT * FROM tablaclientefacturadetalle "
                        + "WHERE  codigo_venta like '%"+codigoventa +"%' or Consecutivo_de_la_venta like '%" + consecutivoventa + "%' "
                        + "ORDER BY fechaCreacion asc";
            } else if (!codigoventa.equals("") && consecutivoventa.equals("")) {
                sql = "SELECT * FROM tablaclientefacturadetalle "
                        + "WHERE  codigo_venta like '%" + codigoventa + "%' "
                        + "ORDER BY fechaCreacion asc";
            } else if (codigoventa.equals("") && !consecutivoventa.equals("")) {
                sql = "SELECT * FROM tablaclientefacturadetalle "
                        + "WHERE  Consecutivo_de_la_venta like '%" + consecutivoventa + "%' "
                        + "ORDER BY fechaCreacion asc";
            } else {
                sql = "SELECT * FROM tablaclientefacturadetalle ORDER BY fechaCreacion asc";
            }
            consulta = miConexion.prepareStatement(sql);
            datos = consulta.executeQuery();

            while (datos.next()) {
              claseClienteFacturaDetalle clifacdet = new claseClienteFacturaDetalle();
             clifacdet.setId(datos.getInt("id"));
            clifacdet.setNombreCliente(datos.getString("Nombre_cliente"));
            clifacdet.setApellidos(datos.getString("apellidos"));
            clifacdet.setCodigo_venta(datos.getInt("codigo_venta"));
            clifacdet.setConsecutivo_venta(datos.getInt("Consecutivo_de_la_venta"));
            clifacdet.setFechaCreacion(datos.getString("fechaCreacion"));
            clifacdet.setNombreprenda(datos.getString("Nombreprenda"));
            clifacdet.setCategoria(datos.getString("Categoria"));
            clifacdet.setCategoria(datos.getString("Categoria"));
            clifacdet.setCantidad(datos.getInt("cantidad"));
            clifacdet.setValorunitario(datos.getDouble("valorUnitario"));
            
            listaclifacdet.add(clifacdet);

            }
            return listaclifacdet;

        } catch (Exception e) {
            System.err.println("No se pudo conectar o hacer consulta");
            return listaclifacdet;
        } finally {
            conexionBD.setDesconectar();
        }
    }
    
}

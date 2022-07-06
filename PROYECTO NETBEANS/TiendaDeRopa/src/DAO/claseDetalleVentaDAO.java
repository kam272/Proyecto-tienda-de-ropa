/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorDetalleVenta;
import MODEL.claseDetalleVenta;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseDetalleVentaDAO {
    
    claseControladorDetalleVenta cdetalle =new claseControladorDetalleVenta();
    
    public boolean listarporNVentas(JTable tabla, int numerofactura) {
        boolean val = false;
        DefaultTableModel modelo;
        String[] titulos = {"Consecutivo", "numero de venta", "Codigo prenda", "Cantidad","Valor unitario","Subtotal"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseDetalleVenta> datos = cdetalle.Find(numerofactura);
        if (datos.isEmpty()) {
            val = false;
        } else {
            String[] datosdetalle = new String[6];
            tabla.setBackground(Color.white);
            tabla.getTableHeader().setBackground(Color.cyan);

            for (claseDetalleVenta tblp : datos) {
                datosdetalle[0] = tblp.getConsecutivo() + "";
                datosdetalle[1] = tblp.getNumeroventa() + "";
                datosdetalle[2] = tblp.getCodprenda()+ "";
                datosdetalle[3] = tblp.getCantidad()+ "";
                datosdetalle[4] = tblp.getValorUnitario()+ "";
                Double subtotal = tblp.getCantidad()*tblp.getValorUnitario();
                datosdetalle[5] = subtotal+ "";
                modelo.addRow(datosdetalle);
            }
            tabla.setModel(modelo);
            val = true;
        }
        return val;
    }
    
  public String agregar(int numeroventa,int codprenda,int cantidad,double valorunitario) {
       String mensaje = null; 
       claseDetalleVenta detalleventa = new claseDetalleVenta();
       detalleventa.setNumeroventa(numeroventa);
      detalleventa.setCodprenda(codprenda);
      detalleventa.setCantidad(cantidad);
      detalleventa.setValorUnitario(valorunitario);
      
       mensaje = cdetalle.insert(detalleventa);
        
        return mensaje;
    }
     public String eliminar(int consecutivo){
        String mensaje=null;
        
        mensaje =cdetalle.destroy(consecutivo);
        
        return mensaje;
        
    }
     
      public String editar(int numeroventa,int codprenda,int cantidad,double valorunitario,int consecutivo) {
       String mensaje = null; 
       claseDetalleVenta detalleventa = new claseDetalleVenta();
       detalleventa.setNumeroventa(numeroventa);
      detalleventa.setCodprenda(codprenda);
      detalleventa.setCantidad(cantidad);
      detalleventa.setValorUnitario(valorunitario);
      detalleventa.setConsecutivo(consecutivo);
      
       mensaje = cdetalle.edit(detalleventa);
        
        return mensaje;
    }
}

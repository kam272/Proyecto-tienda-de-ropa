/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorClienteFacturaDetalle;
import MODEL.claseClienteFacturaDetalle;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseClienteFacturaDetalleDAO {
     claseControladorClienteFacturaDetalle cclifacdet =new claseControladorClienteFacturaDetalle();
    
    public void listarClienteFacturaDetalle(JTable tabla) {
        
        DefaultTableModel modelo;
        String[] titulos = {"Id cliente","Nombre cliente","Apellidos cliente","Codigo venta","Consecutivo venta","Fecha creacion","Nombre prenda","Categoria", "Cantidad","Valor unitario","Subtotal"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseClienteFacturaDetalle> datos = cclifacdet.listAll();
        
            String[] datosclifacdet = new String[11];
            tabla.setBackground(Color.white);
            tabla.getTableHeader().setBackground(Color.cyan);

            for (claseClienteFacturaDetalle tblp : datos) {
                datosclifacdet[0] = tblp.getId()+ "";
                datosclifacdet[1] = tblp.getNombreCliente()+ "";
                datosclifacdet[2] = tblp.getApellidos()+ "";
                datosclifacdet[3] = tblp.getCodigo_venta()+ "";
                datosclifacdet[4] = tblp.getConsecutivo_venta()+ "";
                datosclifacdet[5] = tblp.getFechaCreacion()+ "";
                datosclifacdet[6] = tblp.getNombreprenda()+ "";
                datosclifacdet[7] = tblp.getCategoria()+ "";
                datosclifacdet[8] = tblp.getCantidad()+ "";
                datosclifacdet[9] = tblp.getValorunitario()+ "";
                Double subtotal = tblp.getCantidad()*tblp.getValorunitario();
                datosclifacdet[10] = subtotal+ "";
                modelo.addRow(datosclifacdet);
            }
            tabla.setModel(modelo);
           
    }
    
    public boolean listarClienteFacturaDetalleFind(JTable tabla,String codventa, String consecutivoventa, String opcion) {
        boolean val = false;
        DefaultTableModel modelo;
        String[] titulos = {"Id cliente","Nombre cliente","Apellidos cliente","Codigo venta","Consecutivo venta","Fecha creacion","Nombre prenda","Categoria", "Cantidad","Valor unitario","Subtotal"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseClienteFacturaDetalle> datos = cclifacdet.listafiltrada(codventa, consecutivoventa, opcion);
        if (datos.isEmpty()) {
            val = false;
        } else {
            String[] datosclifacdet = new String[11];
            tabla.setBackground(Color.white);
            tabla.getTableHeader().setBackground(Color.cyan);

            for (claseClienteFacturaDetalle tblp : datos) {
                datosclifacdet[0] = tblp.getId()+ "";
                datosclifacdet[1] = tblp.getNombreCliente()+ "";
                datosclifacdet[2] = tblp.getApellidos()+ "";
                datosclifacdet[3] = tblp.getCodigo_venta()+ "";
                datosclifacdet[4] = tblp.getConsecutivo_venta()+ "";
                datosclifacdet[5] = tblp.getFechaCreacion()+ "";
                datosclifacdet[6] = tblp.getNombreprenda()+ "";
                datosclifacdet[7] = tblp.getCategoria()+ "";
                datosclifacdet[8] = tblp.getCantidad()+ "";
                datosclifacdet[9] = tblp.getValorunitario()+ "";
                Double subtotal = tblp.getCantidad()*tblp.getValorunitario();
                datosclifacdet[10] = subtotal+ "";
                modelo.addRow(datosclifacdet);
            }
            tabla.setModel(modelo);
            val = true;
        }
        return val;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorVenta;
import MODEL.claseVenta;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseVentaDAO {

    claseControladorVenta cventas = new claseControladorVenta();

    public void listarVentas(JTable tabla) {
        DefaultTableModel modelo;
        String[] titulos = {"codigo", "fecha", "id cliente", "fecha creacion","fecha modificacion"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseVenta> datos = cventas.listAll();

        String[] datoscliente = new String[5];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);

        for (claseVenta tblp : datos) {
            datoscliente[0] = tblp.getCodigo() + "";
            datoscliente[1] = tblp.getFecha() + "";
            datoscliente[2] = tblp.getIdCliente() + "";
            datoscliente[3] = tblp.getFechaCreacion()+ "";
            datoscliente[4] = tblp.getFechaModificacion()+ "";
            modelo.addRow(datoscliente);
        }
        tabla.setModel(modelo);
    }

    public boolean listarVenta(JTable tabla, int codigo) {
        boolean val = false;
        DefaultTableModel modelo;
        String[] titulos = {"codigo", "fecha", "id cliente", "fecha creacion","fecha modificacion"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseVenta> datos = cventas.Find(codigo);
        if (datos.isEmpty()) {
            val = false;
        } else {
            String[] datoscliente = new String[5];
            tabla.setBackground(Color.white);
            tabla.getTableHeader().setBackground(Color.cyan);

            for (claseVenta tblp : datos) {
                datoscliente[0] = tblp.getCodigo() + "";
                datoscliente[1] = tblp.getFecha() + "";
                datoscliente[2] = tblp.getIdCliente() + "";
                datoscliente[3] = tblp.getFechaCreacion()+ "";
                datoscliente[4] = tblp.getFechaModificacion()+ "";
                modelo.addRow(datoscliente);
            }
            tabla.setModel(modelo);
            val = true;
        }
        return val;
    }

    public String agregar(String fecha, int idcliente, String FechaCreacion,String FechaModificacion) {
        String mensaje = null;
        claseVenta venta = new claseVenta();
        venta.setFecha(fecha);
        venta.setIdCliente(idcliente);
        venta.setFechaCreacion(FechaCreacion);
        venta.setFechaModificacion(FechaModificacion);
        mensaje = cventas.insert(venta);

        return mensaje;
    }
    
    public String editar(String fecha, int idcliente, String FechaModificacion,int codigo){
        String mensaje=null;
       claseVenta venta = new claseVenta();
       venta.setCodigo(codigo);
       venta.setFecha(fecha);
       venta.setIdCliente(idcliente);
       venta.setFechaModificacion(FechaModificacion);
       mensaje = cventas.edit(venta);    
        return mensaje;
        
    }
    
     public String eliminar(int codigo){
        String mensaje=null;
        
        mensaje =cventas.destroy(codigo);
        
        return mensaje;
        
    }
    
}

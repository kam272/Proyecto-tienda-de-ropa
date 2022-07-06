/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorTipoPrenda;
import MODEL.claseTipoPrenda;
import java.awt.Color;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class claseTipoPrendaDAO {
    claseControladorTipoPrenda ctipoprenda = new claseControladorTipoPrenda();
    
    public void listarTipoPrenda(JTable tabla) {
        DefaultTableModel modelo;
        String[] titulos = {"id", "nombre"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseTipoPrenda> datos = ctipoprenda.listAll();

        String[] datostipoprenda = new String[2];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);

        for (claseTipoPrenda tblp : datos) {
            datostipoprenda[0] = tblp.getId() + "";
            datostipoprenda[1] = tblp.getNombre() + "";

            modelo.addRow(datostipoprenda);
        }
        tabla.setModel(modelo);
    }
    
      public void getTipoPrendacmb(JComboBox cmbTipoPrenda){
        List <claseTipoPrenda> dato = ctipoprenda.listAll();
        cmbTipoPrenda.addItem("Seleccione una opcion: ");
        for (int i = 0; i < dato.size(); i++) {
            cmbTipoPrenda.addItem(dato.get(i).getNombre());
            
        }
    }
      
      public int getid(String n){
      return ctipoprenda.findID(n);
    }
    
     public String getNombre(int id){
      return ctipoprenda.findName(id);
    }
     
      public String agregar(String nombre) {
        String mensaje = null;
        claseTipoPrenda tipoprenda = new claseTipoPrenda();
        tipoprenda.setNombre(nombre);

        mensaje = ctipoprenda.insert(tipoprenda);

        return mensaje;
    }

    public String eliminar(int id) {
        String mensaje = null;
        mensaje = ctipoprenda.destroy(id);
        return mensaje;

    }

    public String editar(int id, String nombre) {
        String mensaje = null;
        claseTipoPrenda tipoprenda=  new claseTipoPrenda();
        tipoprenda.setId(id);
        tipoprenda.setNombre(nombre);
        
       mensaje =  ctipoprenda.edit(tipoprenda);
        return mensaje;

    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorMaterial;
import MODEL.claseMaterial;
import java.awt.Color;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseMaterialDAO {

    claseControladorMaterial cmaterial = new claseControladorMaterial();

    public void listarMateriales(JTable tabla) {
        DefaultTableModel modelo;
        String[] titulos = {"id", "nombre"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseMaterial> datos = cmaterial.listAll();

        String[] datosmaterial = new String[2];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);

        for (claseMaterial tblp : datos) {
            datosmaterial[0] = tblp.getId() + "";
            datosmaterial[1] = tblp.getNombre() + "";

            modelo.addRow(datosmaterial);
        }
        tabla.setModel(modelo);
    }

    public void getMaterialcmb(JComboBox cmbMaterial) {
        List<claseMaterial> dato = cmaterial.listAll();
        cmbMaterial.addItem("Seleccione una opcion: ");
        for (int i = 0; i < dato.size(); i++) {
            cmbMaterial.addItem(dato.get(i).getNombre());

        }
    }

    public int getid(String n) {
        return cmaterial.findID(n);
    }

    public String getNombre(int id) {
        return cmaterial.findName(id);
    }

    public String agregar(String nombre) {
        String mensaje = null;
        claseMaterial material = new claseMaterial();
        material.setNombre(nombre);

        mensaje = cmaterial.insert(material);

        return mensaje;
    }

    public String eliminar(int id) {
        String mensaje = null;
        mensaje = cmaterial.destroy(id);
        return mensaje;

    }

    public String editar(int id, String nombre) {
        String mensaje = null;
        claseMaterial material = new claseMaterial();
        material.setId(id);
        material.setNombre(nombre);
        
       mensaje =  cmaterial.edit(material);
        return mensaje;

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorCliente;
import MODEL.claseCLiente;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseClienteDAO {
    claseControladorCliente ccliente = new claseControladorCliente();
    
    public void listarClientes(JTable tabla) {
        DefaultTableModel modelo;
        String[] titulos = {"cedula", "nombres", "apellidos", "direccion", "telefono"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseCLiente> datos = ccliente.listAll();

          String[] datoscliente = new String[5];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);
        
        for (claseCLiente tblp : datos) {
            datoscliente[0] = tblp.getId() + "";
            datoscliente[1] = tblp.getNombre() + "";
            datoscliente[2] = tblp.getApellidos() + "";
            datoscliente[3] = tblp.getDireccion()+ "";
            datoscliente[4] = tblp.getTelefono()+ "";
             modelo.addRow(datoscliente);    
              }
        tabla.setModel(modelo);
    }
    
        public boolean listarCliente(JTable tabla,String nombre) {
        boolean val=false;
           
        DefaultTableModel modelo;
        String[] titulos = {"cedula", "nombres", "apellidos", "direccion", "telefono"};
        modelo = new DefaultTableModel(null, titulos);

        List<claseCLiente> datos = ccliente.Find(nombre);
        
            if (datos.isEmpty()) {
                val=false;
            }else{
          String[] datoscliente = new String[5];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);
        
        for (claseCLiente tblp : datos) {
            datoscliente[0] = tblp.getId() + "";
            datoscliente[1] = tblp.getNombre() + "";
            datoscliente[2] = tblp.getApellidos() + "";
            datoscliente[3] = tblp.getDireccion()+ "";
            datoscliente[4] = tblp.getTelefono()+ "";
             modelo.addRow(datoscliente);    
              }
        tabla.setModel(modelo);
        val =true;
            }
            return val;
    }
    
      public String agregar(int cedula, String nombre, String apellidos, String direccion,String telefeno,File imagen) {
       String mensaje = null;
       claseCLiente cliente = new claseCLiente();
       cliente.setId(cedula);
       cliente.setNombre(nombre);
       cliente.setApellidos(apellidos);
       cliente.setDireccion(direccion);
       cliente.setTelefono(telefeno);
       mensaje = ccliente.insert(cliente, imagen);
        
        return mensaje;
    }
    
    public String eliminar(int id){
        String mensaje=null;
        
        mensaje =ccliente.destroy(id);
        
        return mensaje;
        
    }
    
        public String editar(int cedula, String nombre, String apellidos, String direccion,String telefeno){
        String mensaje=null;
       claseCLiente cliente = new claseCLiente();
       cliente.setId(cedula);
       cliente.setNombre(nombre);
       cliente.setApellidos(apellidos);
       cliente.setDireccion(direccion);
       cliente.setTelefono(telefeno);
       mensaje = ccliente.edit(cliente);    
        return mensaje;
        
    }
        public String editarfoto(int cedula, String nombre, String apellidos, String direccion,String telefeno,File imagen){
        String mensaje=null;
       claseCLiente cliente = new claseCLiente();
       cliente.setId(cedula);
       cliente.setNombre(nombre);
       cliente.setApellidos(apellidos);
       cliente.setDireccion(direccion);
       cliente.setTelefono(telefeno);
       mensaje = ccliente.editImagen(cliente,imagen);    
        return mensaje;
        
    }
        
    
    
    public void getClienteCmb(JComboBox cmbPersona){
        List <claseCLiente> dato = ccliente.listAll();
        cmbPersona.addItem("Seleccione una opcion: ");
        for (int i = 0; i < dato.size(); i++) {
            cmbPersona.addItem(dato.get(i).getNombre());
            
        }
    }
    
    public int getid(String n){
      return ccliente.findID(n);
    }
    
     public String getNombre(int id){
      return ccliente.findName(id);
    }
     
    public void getImagenCliente(JPanel jimagen, int id){
        ccliente.showImage(id, jimagen);
    }
    
    public InputStream getImageRute(int id){
      return ccliente.getImageRute(id);
    }
            
}

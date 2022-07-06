/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorPrenda;
import MODEL.clasePrenda;
import java.awt.Color;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class clasePrendaDAO {
     claseControladorPrenda cprenda = new claseControladorPrenda();
    
    public void listarPrendas(JTable tabla) {
        DefaultTableModel modelo;
        String[] titulos = {"ID","Nombre","Color", "Categoria", "Cod material", "Cod tipo prenda","Precio","Fecha creacion","Fecha modificacion"};
        modelo = new DefaultTableModel(null, titulos);

        List<clasePrenda> datos = cprenda.listAll();

          String[] datosprenda = new String[9];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);
        
        for (clasePrenda tblp : datos) {
            datosprenda[0] = tblp.getId() + "";
            datosprenda[1] = tblp.getNombre() + "";
            datosprenda[2] = tblp.getColor()+ "";
            datosprenda[3] = tblp.getCategoria() + "";
            datosprenda[4] = tblp.getCodmaterial()+ "";
            datosprenda[5] = tblp.getCodtipoprenda()+ "";
            datosprenda[6] = tblp.getPrecio()+ "";
            datosprenda[7] = tblp.getFechacreacion()+ "";
            datosprenda[8] = tblp.getFechamodificacion()+ "";
             modelo.addRow(datosprenda);    
              }
        tabla.setModel(modelo);
    }
    
    public boolean listarPrenda(JTable tabla,String nombre) {
        boolean val=false;
           
        DefaultTableModel modelo;
       String[] titulos = {"ID","Nombre","Color", "Categoria", "Cod material", "Cod tipo prenda","Precio","Fecha creacion","Fecha modificacion"};
        modelo = new DefaultTableModel(null, titulos);

       List<clasePrenda> datos = cprenda.Find(nombre);
        
            if (datos.isEmpty()) {
                val=false;
            }else{
                
        String[] datosprenda = new String[9];
        tabla.setBackground(Color.white);
        tabla.getTableHeader().setBackground(Color.cyan);
        
        for (clasePrenda tblp : datos) {
            datosprenda[0] = tblp.getId() + "";
            datosprenda[1] = tblp.getNombre() + "";
            datosprenda[2] = tblp.getColor()+ "";
            datosprenda[3] = tblp.getCategoria() + "";
            datosprenda[4] = tblp.getCodmaterial()+ "";
            datosprenda[5] = tblp.getCodtipoprenda()+ "";
            datosprenda[6] = tblp.getPrecio()+ "";
            datosprenda[7] = tblp.getFechacreacion()+ "";
            datosprenda[8] = tblp.getFechamodificacion()+ "";
             modelo.addRow(datosprenda);    
              }
        tabla.setModel(modelo);
        val =true;
            }
            return val;
    }
    
     public void getPrendaCmb(JComboBox cmbPrenda){
        List <clasePrenda> dato = cprenda.listAll();
        cmbPrenda.addItem("Seleccione una opcion: ");
        for (int i = 0; i < dato.size(); i++) {
            cmbPrenda.addItem(dato.get(i).getNombre());
            
        }
    }
    
    public int getid(String n){
      return cprenda.FindID(n);
    }
      public String getNombre(int id){
      return cprenda.findName(id);
    }
    
    public double getPrecio(int id){
        return cprenda.FindPrecio(id);
    }
    
    public InputStream getImageRute(int id){
      return cprenda.getImageRute(id);
    }
    
     
    public String eliminar(int id){
        String mensaje=null;
        
        mensaje =cprenda.destroy(id);
        
        return mensaje;
        
    }
      public String agregar(String nombre,String color,String categoria,int codmaterial, int codtipoprenda, double precio, String fechacreacion, String fechamodificacion,File imagen) {
       String mensaje = null;
       clasePrenda prenda = new clasePrenda();
       prenda.setNombre(nombre);
       prenda.setColor(color);
       prenda.setCategoria(categoria);
       prenda.setCodmaterial(codmaterial);
       prenda.setCodtipoprenda(codtipoprenda);
       prenda.setPrecio(precio);
       prenda.setFechacreacion(fechacreacion);
       prenda.setFechamodificacion(fechamodificacion);
       
       mensaje = cprenda.insert(prenda, imagen);
        
        return mensaje;
    }
      
       public String editar(int id,String nombre,String color,String categoria,int codmaterial, int codtipoprenda, double precio, String fechamodificacion){
        String mensaje=null;
       clasePrenda prenda = new clasePrenda();
       prenda.setId(id);
       prenda.setNombre(nombre);
       prenda.setColor(color);
       prenda.setCategoria(categoria);
       prenda.setCodmaterial(codmaterial);
       prenda.setCodtipoprenda(codtipoprenda);
       prenda.setPrecio(precio);
       prenda.setFechamodificacion(fechamodificacion);
       
       mensaje = cprenda.edit(prenda);    
        return mensaje;
        
    }
        public String editarfoto(int id,String nombre,String color,String categoria,int codmaterial, int codtipoprenda, double precio, String fechamodificacion,File imagen){
        String mensaje=null;
       clasePrenda prenda = new clasePrenda();
       prenda.setId(id);
       prenda.setNombre(nombre);
       prenda.setColor(color);
       prenda.setCategoria(categoria);
       prenda.setCodmaterial(codmaterial);
       prenda.setCodtipoprenda(codtipoprenda);
       prenda.setPrecio(precio);
       prenda.setFechamodificacion(fechamodificacion);
       
       mensaje = cprenda.editImagen(prenda,imagen);    
        return mensaje;
        
    }
        
}

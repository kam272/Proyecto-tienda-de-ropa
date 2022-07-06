/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.claseConexion;
import MODEL.claseUsuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class claseControladorUsuario {
    private claseConexion conexionBD = new claseConexion();
    
    public List validar (String usu, String contrasena){
      
       List <claseUsuario> listausuario = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            if (usu.equals("")|| contrasena.equals("")) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario y la contrasena deben ser digitadas");
              
            }else{
            String sql ="SELECT id,nombre,apellidos,usuario,contrasena FROM tblusuario "
                    + "WHERE usuario='"+usu+"' and contrasena='"+contrasena+"'";
            PreparedStatement consulta = miConexion.prepareStatement(sql);
            ResultSet datos = consulta.executeQuery();
                if (datos.next()) {
                    claseUsuario usuario = new claseUsuario();
                    usuario.setIdentificacion(datos.getInt(1));
                    usuario.setNombre(datos.getString(2));
                    usuario.setApellidos(datos.getString(3));
                    usuario.setUsuario(datos.getString(4));
                    usuario.setContrasena(datos.getString(5));
                    
                    
                    listausuario.add(usuario);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario y/o contrasena equivocadas");
                }
                
            }
        } catch (Exception e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage());
            
        }
        return listausuario;
    }
}

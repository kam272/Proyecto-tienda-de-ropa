/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CONTROL.claseControladorUsuario;
import MODEL.claseUsuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class claseUsuarioDAO {
    private claseControladorUsuario cusuario = new claseControladorUsuario(); 
    
    public boolean validar (String usuario, String contrasena){
       boolean val=false;
        List<claseUsuario> datos = cusuario.validar(usuario, contrasena);
        
            if (datos.isEmpty()) {
                val=false;
            }else{
                val =true;
            }
            return val;
    }
}

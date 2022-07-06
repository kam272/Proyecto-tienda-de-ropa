
package MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *}
 * 
 * 
 * @author Karen Espitia
 */
public class claseConexion {
    //Declarar objetos de conexion
    Connection miConexion; //para definir la conexion
    PreparedStatement consulta;//para realizar las consultas SQL
    ResultSet datos; //para almacenar los resultados de consulta
     
    public Connection getConnection(){
        
        String BD= "tiendaropa";//Variable para el nombre de la base de datos
        String usuario="userTiendaRopa";//variable para el nombre del usuaro de MySql
        String contrasena= "12345";//variavle para la contrasena del usuario de MySql
       try {
           //llmar la clase o driver de jdbc de MySql
          Class.forName("com.mysql.cj.jdbc.Driver");//driver
          String servidor = "jdbc:mysql://localhost:3306/"+BD; //CONFIGURACION DEL SERVIDOR
          //realizar conexion
          miConexion = (Connection) DriverManager.getConnection(servidor,usuario,contrasena);
         
         
       } catch (ClassNotFoundException e) {
            System.err.println("No se encontro el driver de Mysql");
            miConexion = null;
        }catch (SQLException ex){
            System.err.println("No se pudo conectar la base de datos");
            miConexion = null;
        }
        return miConexion;
        
    }
    
    public void setDesconectar(){
        try {
            miConexion.close(); //cerramos la conexion
        } catch (Exception e) {
            System.err.println("No se pudo desconectar la base de datos");
        }
    }
    
}

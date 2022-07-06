
package CONTROL;

import MODEL.claseCLiente;
import MODEL.claseConexion;
import MODEL.claseImagenMySQL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Usuario
 */
public class claseControladorCliente {
     private claseConexion conexionBD = new claseConexion();
    
    public List listAll(){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseCLiente> listadocliente = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblcliente ORDER BY nombre");
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseCLiente cliente = new claseCLiente();
             cliente.setId(datos.getInt("id"));
             cliente.setNombre(datos.getString("nombre"));
             cliente.setApellidos(datos.getString("apellidos"));
            cliente.setDireccion(datos.getString("direccion"));
            cliente.setTelefono(datos.getString("telefono"));
            listadocliente.add(cliente);
            
            }
            return listadocliente;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"); 
            return listadocliente;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
        public List Find(String nombre){
        PreparedStatement consulta;
        ResultSet datos;
        List <claseCLiente> listadocliente = new ArrayList<>();
        
       
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblcliente WHERE nombre LIKE ?");
            consulta.setString(1, "%"+nombre+"%");
           
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            claseCLiente cliente = new claseCLiente();
             cliente.setId(datos.getInt("id"));
             cliente.setNombre(datos.getString("nombre"));
             cliente.setApellidos(datos.getString("apellidos"));
            cliente.setDireccion(datos.getString("direccion"));
            cliente.setTelefono(datos.getString("telefono"));
            listadocliente.add(cliente);
            
            }
            return listadocliente;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadocliente;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
    
     public void showImage (int id,JPanel jpimagen){
          PreparedStatement ps;
        ResultSet rs;
        claseConexion miConexion = new claseConexion();
        try {
            Connection con = miConexion.getConnection();
            ps = con.prepareStatement("SELECT imagen FROM tblcliente WHERE id=?");
            ps.setInt(1,id); //Coloco un id listo para no hacer un listado
           
            rs = ps.executeQuery();
            
            BufferedImage buffimg = null;
            while (rs.next()) {
                InputStream img = rs.getBinaryStream(1);
                buffimg = ImageIO.read(img);
                claseImagenMySQL imagen = new claseImagenMySQL(jpimagen.getHeight(),jpimagen.getWidth(),buffimg);
                jpimagen.add(imagen);
                jpimagen.repaint();
             
            }           
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error al gestionar el archivo"+ex.getMessage());
        } catch (IOException ex) {
              JOptionPane.showMessageDialog(null, "Error al gestionar el archivo"+ex.getMessage());
        }
    }
     
       public InputStream getImageRute (int id){
          PreparedStatement ps;
        ResultSet rs;
        claseConexion miConexion = new claseConexion();
        InputStream img = null;
        try {
            Connection con = miConexion.getConnection();
            ps = con.prepareStatement("SELECT imagen FROM tblcliente WHERE id=?");
            ps.setInt(1,id); //Coloco un id listo para no hacer un listado
           
            rs = ps.executeQuery();

            while (rs.next()) {
                img = rs.getBinaryStream(1);
                System.out.println(""+img);
               // buffimg = ImageIO.read(img);
            }           
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error al gestionar el archivo"+ex.getMessage());
        }
         
         return img;
    }
    
    public int findID(String nombre){
       int id = 0;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT id From tblcliente WHERE nombre=?";
       consulta = miConexion.prepareStatement(sql);
       consulta.setString(1, nombre);
       datos = consulta.executeQuery();
            while (datos.next()) {                
                id= datos.getInt("id") ;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
        }
        return id;
    }
    
     public String findName(int id){
       String nombre = null;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT nombre From tblcliente WHERE id=?";
       consulta = miConexion.prepareStatement(sql);
       consulta.setInt(1, id);
       datos = consulta.executeQuery();
            while (datos.next()) {                
                nombre= datos.getString("nombre") ;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
        }
      
       
        return nombre;
    }
     
      public String insert(claseCLiente cliente,File archivo) {
        String mensaje = null; 
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            Connection miConexion = conexionBD.getConnection();
            String sql ="INSERT INTO tblcliente (id,nombre,apellidos,direccion,telefono,imagen) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
            
            insertar.setInt(1, cliente.getId());
            insertar.setString(2, cliente.getNombre());
            insertar.setString(3, cliente.getApellidos());
            insertar.setString(4, cliente.getDireccion());
            insertar.setString(5, cliente.getTelefono());
            insertar.setBinaryStream(6, fis, (int) archivo.length());
            
            insertar.execute();
            mensaje = "Registro Guardado exitosamente";
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer la insercion"+e.getMessage());
            mensaje = "No se pudo conectar o hacer la insercion";
        } catch(FileNotFoundException ex){
          System.err.println("No se pudo conectar o hacer la insercion"+ex.getMessage());
      }
        finally{
            conexionBD.setDesconectar();
        }
        
        return mensaje;
    }
      
     public String destroy(int id) {
        String mensaje = null; 
        PreparedStatement borrar;

        try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql ="DELETE FROM tblcliente WHERE id=?";
                borrar = miConexion.prepareStatement(sql);
                borrar.setInt(1, id);
              borrar.executeUpdate();
            
          
            mensaje = "Registro eliminado exitosamente";
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer la eliminacion"+e.getMessage());
            mensaje = "No se pudo conectar o hacer la eliminacion";
        }
        finally{
            conexionBD.setDesconectar();
        }
        return mensaje;
    }
     
     public String edit(claseCLiente cli){
         String mensaje = null;
           try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql = "UPDATE tblcliente SET nombre=?,apellidos=?,direccion=?,telefono=? WHERE id=?";
            PreparedStatement actualizar = miConexion.prepareStatement(sql);
            actualizar.setString(1,cli.getNombre());
            actualizar.setString(2,cli.getApellidos());
            actualizar.setString(3, cli.getDireccion());
            actualizar.setString(4, cli.getTelefono());
            actualizar.setInt(5, cli.getId());
            actualizar.execute();
            
             mensaje = "Registro actualizado exitosamente";
            
        } catch (SQLException e) {
              System.err.println("No se pudo conectar o hacer la actualizacion"+e.getMessage()); 
               mensaje = "No se pudo conectar o hacer la actualizacion";
        }finally{
            conexionBD.setDesconectar();
        }
         return mensaje;
    }   
     
      public String editImagen(claseCLiente cliente,File archivo) {
        String mensaje = null; 
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            Connection miConexion = conexionBD.getConnection();
            String sql ="UPDATE tblcliente SET nombre=?,apellidos=?,direccion=?,telefono=?,imagen=? WHERE id=?";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
          
            insertar.setString(1, cliente.getNombre());
            insertar.setString(2, cliente.getApellidos());
            insertar.setString(3, cliente.getDireccion());
            insertar.setString(4, cliente.getTelefono());
            insertar.setBinaryStream(5, fis, (int) archivo.length());
            insertar.setInt(6, cliente.getId());
            
            insertar.execute();
            mensaje = "Registro Guardado exitosamente";
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer la insercion"+e.getMessage());
            mensaje = "No se pudo conectar o hacer la insercion";
        } catch(FileNotFoundException ex){
          System.err.println("No se pudo conectar o hacer la insercion"+ex.getMessage());
      }
        finally{
            conexionBD.setDesconectar();
        }
        
        return mensaje;
    }
     
     
}

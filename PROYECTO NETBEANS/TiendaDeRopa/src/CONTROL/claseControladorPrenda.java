
package CONTROL;


import MODEL.claseConexion;
import MODEL.clasePrenda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class claseControladorPrenda {
     private claseConexion conexionBD = new claseConexion();
     
      public List listAll(){
        PreparedStatement consulta;
        ResultSet datos;
        List <clasePrenda> listadoprendas = new ArrayList<>();
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblprenda");
            
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
            clasePrenda prenda = new clasePrenda();
             prenda.setId(datos.getInt("id"));
             prenda.setNombre(datos.getString("nombre"));
             prenda.setColor(datos.getString("color"));
            prenda.setCategoria(datos.getString("Categoria"));
            prenda.setCodmaterial(datos.getInt("codmaterial"));
            prenda.setCodtipoprenda(datos.getInt("codtipoprenda"));
            prenda.setPrecio(datos.getDouble("precio"));
            prenda.setFechacreacion(datos.getString("fechacreacion"));
            prenda.setFechamodificacion(datos.getString("fechamodificacion"));
            listadoprendas.add(prenda);
            
            }
            return listadoprendas;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadoprendas;
        }finally{
            conexionBD.setDesconectar();
        }
        
    } 
      
       public List Find(String nombre){
        PreparedStatement consulta;
        ResultSet datos;
         List <clasePrenda> listadoprendas = new ArrayList<>();
        
       
        try {
            Connection miConexion = conexionBD.getConnection();
            consulta = miConexion.prepareStatement("SELECT * FROM tblprenda WHERE nombre LIKE ?");
            consulta.setString(1, "%"+nombre+"%");
           
            datos = consulta.executeQuery();
            
            while (datos.next()) {                
              clasePrenda prenda = new clasePrenda();
             prenda.setId(datos.getInt("id"));
             prenda.setNombre(datos.getString("nombre"));
             prenda.setColor(datos.getString("color"));
            prenda.setCategoria(datos.getString("Categoria"));
            prenda.setCodmaterial(datos.getInt("codmaterial"));
            prenda.setCodtipoprenda(datos.getInt("codtipoprenda"));
            prenda.setPrecio(datos.getDouble("precio"));
            prenda.setFechacreacion(datos.getString("fechacreacion"));
            prenda.setFechamodificacion(datos.getString("fechamodificacion"));
            listadoprendas.add(prenda);
            
            }
            return listadoprendas;
            
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
            return listadoprendas;
        }finally{
            conexionBD.setDesconectar();
        }
        
    }
    
      public int FindID(String nombre){
        int id = 0;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT id From tblprenda WHERE nombre=?";
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
      public double FindPrecio (int id){
          double precio = 0;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT precio From tblprenda WHERE id=?";
       consulta = miConexion.prepareStatement(sql);
       consulta.setInt(1, id);
       datos = consulta.executeQuery();
            while (datos.next()) {                
                precio= datos.getDouble("precio") ;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar o hacer consulta"+e.getMessage()); 
        }
        return precio;
          
      }
      
        public String findName(int id){
       String nombre = null;
       PreparedStatement consulta;
       ResultSet datos;
        try {
             Connection miConexion = conexionBD.getConnection();
       String sql = "SELECT nombre From tblprenda WHERE id=?";
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
        
      public InputStream getImageRute (int id){
          PreparedStatement ps;
        ResultSet rs;
        claseConexion miConexion = new claseConexion();
        InputStream img = null;
        try {
            Connection con = miConexion.getConnection();
            ps = con.prepareStatement("SELECT imagen FROM tblprenda WHERE id=?");
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
        
     public String insert(clasePrenda prenda,File archivo) {
        String mensaje = null; 
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            Connection miConexion = conexionBD.getConnection();
            String sql ="INSERT INTO tblprenda (nombre,color,Categoria,codmaterial,codtipoprenda,precio,fechacreacion,fechamodificacion,imagen) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement insertar =  miConexion.prepareStatement(sql);
            insertar.setString(1, prenda.getNombre());
            insertar.setString(2, prenda.getColor());
            insertar.setString(3, prenda.getCategoria());
            insertar.setInt(4, prenda.getCodmaterial());
            insertar.setInt(5, prenda.getCodtipoprenda());
            insertar.setDouble(6, prenda.getPrecio());
            insertar.setString(7, prenda.getFechacreacion());
            insertar.setString(8, prenda.getFechamodificacion());
            insertar.setBinaryStream(9, fis, (int) archivo.length());
            
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
     
      public String edit(clasePrenda prenda){
         String mensaje = null;
           try {
           
            Connection miConexion = conexionBD.getConnection();
            String sql = "UPDATE tblprenda SET nombre=?,color=?,Categoria=?,codmaterial=?,codtipoprenda=?,precio=?,fechamodificacion=? WHERE id=?";
            PreparedStatement actualizar = miConexion.prepareStatement(sql);
            actualizar.setString(1,prenda.getNombre());
            actualizar.setString(2,prenda.getColor());
            actualizar.setString(3,prenda.getCategoria());
            actualizar.setInt(4,prenda.getCodmaterial());
            actualizar.setInt(5,prenda.getCodtipoprenda());
            actualizar.setDouble(6,prenda.getPrecio());
            actualizar.setString(7,prenda.getFechamodificacion());
            actualizar.setInt(8,prenda.getId());
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
     
      public String editImagen(clasePrenda prenda,File archivo) {
        String mensaje = null; 
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            Connection miConexion = conexionBD.getConnection();
            String sql ="UPDATE tblprenda SET nombre=?,color=?,Categoria=?,codmaterial=?,codtipoprenda=?,precio=?,fechamodificacion=?,imagen=? WHERE id=?";
            PreparedStatement actualizar =  miConexion.prepareStatement(sql);
          
            actualizar.setString(1,prenda.getNombre());
            actualizar.setString(2,prenda.getColor());
            actualizar.setString(3,prenda.getCategoria());
            actualizar.setInt(4,prenda.getCodmaterial());
            actualizar.setInt(5,prenda.getCodtipoprenda());
            actualizar.setDouble(6,prenda.getPrecio());
            actualizar.setString(7,prenda.getFechamodificacion());
            actualizar.setBinaryStream(8, fis, (int) archivo.length());
            actualizar.setInt(9,prenda.getId());
            
            actualizar.execute();
          
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
            String sql ="DELETE FROM tblprenda WHERE id=?";
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
    
}

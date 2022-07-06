
package MODEL;

/**
 *
 * @author Usuario
 */
public class claseMaterial {
    private int id;
    private String nombre;

    public claseMaterial() {
    }

    public claseMaterial(int material, String nombre) {
        this.id = material;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuario
 */
public class clasePrenda {
    private int id;
    private String nombre;
    private String color;
    private String categoria;
    private int codmaterial;
    private int codtipoprenda;
    private double precio;
    private String fechacreacion;
    private String fechamodificacion;

    public clasePrenda() {
    }

    public clasePrenda(int id, String nombre, String color, String categoria, int codmaterial, int codtipoprenda, double precio, String fechacreacion, String fechamodificacion) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.categoria = categoria;
        this.codmaterial = codmaterial;
        this.codtipoprenda = codtipoprenda;
        this.precio = precio;
        this.fechacreacion = fechacreacion;
        this.fechamodificacion = fechamodificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCodmaterial() {
        return codmaterial;
    }

    public void setCodmaterial(int codmaterial) {
        this.codmaterial = codmaterial;
    }

    public int getCodtipoprenda() {
        return codtipoprenda;
    }

    public void setCodtipoprenda(int codtipoprenda) {
        this.codtipoprenda = codtipoprenda;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(String fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class claseClienteFacturaDetalle {
  private int id;
  private String NombreCliente;
  private String apellidos;
  private int codigo_venta;
  private int consecutivo_venta;
  private String fechaCreacion;
  private String nombreprenda;
  private String categoria;
  private int cantidad;
  private double valorunitario;

    public claseClienteFacturaDetalle() {
    }

    public claseClienteFacturaDetalle(int id, String NombreCliente, String apellidos, int codigo_venta, int consecutivo_venta, String fechaCreacion, String nombreprenda, String categoria, int cantidad, double valorunitario) {
        this.id = id;
        this.NombreCliente = NombreCliente;
        this.apellidos = apellidos;
        this.codigo_venta = codigo_venta;
        this.consecutivo_venta = consecutivo_venta;
        this.fechaCreacion = fechaCreacion;
        this.nombreprenda = nombreprenda;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.valorunitario = valorunitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(int codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public int getConsecutivo_venta() {
        return consecutivo_venta;
    }

    public void setConsecutivo_venta(int consecutivo_venta) {
        this.consecutivo_venta = consecutivo_venta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreprenda() {
        return nombreprenda;
    }

    public void setNombreprenda(String nombreprenda) {
        this.nombreprenda = nombreprenda;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }
  
  
  
}

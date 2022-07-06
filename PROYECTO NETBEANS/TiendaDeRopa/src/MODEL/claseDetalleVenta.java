/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuario
 */
public class claseDetalleVenta {
    private int consecutivo;
    private int numeroventa;
    private int codprenda;
    private int cantidad;
    private double valorUnitario;

    public claseDetalleVenta() {
    }

    public claseDetalleVenta(int consecutivo, int numeroventa, int codprenda, int cantidad, double valorUnitario) {
        this.consecutivo = consecutivo;
        this.numeroventa = numeroventa;
        this.codprenda = codprenda;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getNumeroventa() {
        return numeroventa;
    }

    public void setNumeroventa(int numeroventa) {
        this.numeroventa = numeroventa;
    }

    public int getCodprenda() {
        return codprenda;
    }

    public void setCodprenda(int codprenda) {
        this.codprenda = codprenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    
}

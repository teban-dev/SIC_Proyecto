package Utilidades;

public class Producto {
    public String codigo;
    public String nombre;
    public double valor;
    public int cantidad;

    public Producto(String codigo, String nombre, double valor, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return valor;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public int getCantidad() {
        return cantidad;
    }
    
}
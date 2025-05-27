package Utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GestorInventario {
    public static class Producto {
        public String codigo;
        public String nombre;
        public double valor;
        public int cantidad;
        public int vecesComprado;

        public Producto(String codigo, String nombre, double valor, int cantidad) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.valor = valor;
            this.cantidad = cantidad;
            this.vecesComprado = 0;
        }
    }

    private static HashMap<String, Producto> inventario = new HashMap<>();
    private static final String ARCHIVO = "src/inventario.txt";
    
    public static void guardarInventarioEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : inventario.values()) {
                writer.println(p.codigo + ";" + p.nombre + ";" + p.valor + ";" + p.cantidad + ";" + p.vecesComprado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarInventarioDesdeArchivo() {
        inventario.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    double valor = Double.parseDouble(partes[2]);
                    int cantidad = Integer.parseInt(partes[3]);
                    int vecesComprado = (partes.length >= 5) ? Integer.parseInt(partes[4]) : 0;

                    Producto p = new Producto(codigo, nombre, valor, cantidad);
                    p.vecesComprado = vecesComprado;
                    inventario.put(codigo, p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void agregarProducto(Producto p) {
    inventario.put(p.codigo, p);
    guardarInventarioEnArchivo();
    
    }

    public static void cargarInventario(ArrayList<Producto> productos) {
        inventario.clear();
        for (Producto p : productos) {
            inventario.put(p.codigo, p);
        }
    }
    
    public static Collection<Producto> obtenerTodosLosProductos() {
        return inventario.values();
    }

    public static Producto buscarProducto(String codigo) {
        return inventario.get(codigo);
    }

    public static boolean descontarStock(String codigo, int cantidad) {
        Producto p = inventario.get(codigo);
        if (p != null && p.cantidad >= cantidad) {
            p.cantidad -= cantidad;
            p.vecesComprado += cantidad;
            guardarInventarioEnArchivo();
            return true;
        }
        return false;
    }

    public static ArrayList<Producto> obtenerTopProductosVendidos(int n) {
        ArrayList<Producto> productos = new ArrayList<>(inventario.values());
        productos.sort((p1, p2) -> Integer.compare(p2.vecesComprado, p1.vecesComprado));

        while (productos.size() < n) {
            productos.add(new Producto("null", "null", 0.0, 0));
        }
        return new ArrayList<>(productos.subList(0, n));
    }
    
    
    
}
package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Productos {

    private int Id_Producto;
    private String Nombre;
    private String Descripcion;
    private double Precio;
    private int Id_Tipo;

    public Productos(int Id_Producto, String Nombre, String Descripcion, double Precio, int Id_Tipo) {
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Id_Tipo = Id_Tipo;
    }

    public Productos() {
    }

    // GETTERS & SETTERS
    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        this.Precio = precio;
    }

    public int getId_Tipo() {
        return Id_Tipo;
    }

    public void setId_Tipo(int id_Tipo) {
        this.Id_Tipo = id_Tipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "Id_Producto=" + Id_Producto + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", Precio=" + Precio + ", Id_Tipo=" + Id_Tipo + '}';
    }

    public static String toCadena(Productos producto) {
        return "Producto{"
                + "\"Id_Producto\"=" + producto.getId_Producto() + ", "
                + "Nombre=" + producto.getNombre() + ", "
                + "Descripcion=" + producto.getDescripcion() + ", "
                + "Precio=" + producto.getPrecio() + ", "
                + "Id_Tipo=" + producto.getId_Tipo() + '}';
    }

    public static String fromArrayToJson(ArrayList<Productos> productos) {
        String resp = "[";
        for (Productos producto : productos) {
            resp += "{"
                    + "\"Id_Producto\"=" + producto.getId_Producto() + ", "
                    + "Nombre=" + producto.getNombre() + ", "
                    + "Descripcion=" + producto.getDescripcion() + ", "
                    + "Precio=" + producto.getPrecio() + ", "
                    + "Id_Tipo=" + producto.getId_Tipo() + '}';
            resp += ",";
        }
        if (!productos.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Productos> productos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(productos);
    }
}

package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Clientes {
    private int id_cliente;
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;

    public Clientes(int id_cliente, String nombre, String email, String contrasena, String telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    public Clientes() {
    }

    // GETTERS & SETTERS
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", email=" + email + ", contrasena=" + contrasena + ", telefono=" + telefono + '}';
    }

    public static String toCadena(Clientes cliente) {
        return "Cliente{"
                + "\"id_cliente\"=" + cliente.getId_cliente() + ", "
                + "nombre=" + cliente.getNombre() + ", "
                + "email=" + cliente.getEmail() + ", "
                + "contrasena=" + cliente.getContrasena() + ", "
                + "telefono=" + cliente.getTelefono() + '}';
    }

    public static String fromArrayToJson(ArrayList<Clientes> clientes) {
        String resp = "[";
        for (Clientes cliente : clientes) {
            resp += "{"
                    + "\"id_cliente\"=" + cliente.getId_cliente() + ", "
                    + "nombre=" + cliente.getNombre() + ", "
                    + "email=" + cliente.getEmail() + ", "
                    + "contrasena=" + cliente.getContrasena() + ", "
                    + "telefono=" + cliente.getTelefono() + '}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Clientes> clientes) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(clientes);
    }
}

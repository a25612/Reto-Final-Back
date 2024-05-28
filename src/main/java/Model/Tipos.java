package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Tipos {

    private int Id_Tipo;
    private String Nombre;

    // Constructor con parámetros
    public Tipos(int Id_Tipo, String Nombre) {
        this.Id_Tipo = Id_Tipo;
        this.Nombre = Nombre;
    }

    // Constructor vacío
    public Tipos() {
    }

    // GETTERS Y SETTERS
    public int getId_Tipo() {
        return Id_Tipo;
    }

    public void setId_Tipo(int id_Tipo) {
        Id_Tipo = id_Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tipos{" + "Id_Tipo=" + Id_Tipo + ", Nombre=" + Nombre + '}';
    }

    // Metodo estático para convertir un objeto Tipos a cadena
    public static String toCadena(Tipos tipo) {
        return "Tipos{"
                + "\"Id_Tipo\"=" + tipo.getId_Tipo() + ", "
                + "Nombre=" + tipo.getNombre() + '}';
    }

    // Metodo estático para convertir un ArrayList de Tipos a JSON
    public static String fromArrayToJson(ArrayList<Tipos> tipos) {
        String resp = "[";
        for (Tipos tipo : tipos) {
            resp += "{"
                    + "\"Id_Tipo\"=" + tipo.getId_Tipo() + ", "
                    + "Nombre=" + tipo.getNombre() + '}';
            resp += ",";
        }
        if (!tipos.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }

    // Metodo estatico para convertir un ArrayList de Tipos a JSON usando Gson
    public static String toArrayJSon(ArrayList<Tipos> tipos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(tipos);
    }
}

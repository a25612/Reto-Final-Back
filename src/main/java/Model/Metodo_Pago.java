package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Metodo_Pago {

    private int Id_Metodo_Pago;
    private String Metodo_Pago;

    // Constructor con parámetros
    public Metodo_Pago(int Id_Metodo_Pago, String Metodo_Pago) {
        this.Id_Metodo_Pago = Id_Metodo_Pago;
        this.Metodo_Pago = Metodo_Pago;
    }

    // Constructor vacío
    public Metodo_Pago() {
    }

    // GETTERS Y SETTERS
    public int getId_Metodo_Pago() {
        return Id_Metodo_Pago;
    }
    public void setId_Metodo_Pago(int id_Metodo_Pago) {
        Id_Metodo_Pago = id_Metodo_Pago;
    }

    public String getMetodo_Pago() {
        return Metodo_Pago;
    }
    public void setMetodo_Pago(String metodo_Pago) {
        Metodo_Pago = metodo_Pago;
    }

    @Override
    public String toString() {
        return "Tipos{" + "Id_Metodo_Pago=" + Id_Metodo_Pago + ", Metodo_Pago=" + Metodo_Pago + '}';
    }

    // Metodo estático para convertir un objeto Tipos a cadena
    public static String toCadena(Metodo_Pago metodoPago) {
        return "Metodo_Pago{"
                + "\"Id_Metodo_Pago\"=" + metodoPago.getId_Metodo_Pago() + ", "
                + "Metodo_Pago=" + metodoPago.getMetodo_Pago() + '}';
    }

    // Metodo estático para convertir un ArrayList de Tipos a JSON
    public static String fromArrayToJson(ArrayList<Metodo_Pago> metodoPagos) {
        String resp = "[";
        for (Metodo_Pago metodoPago : metodoPagos) {
            resp += "{"
                    + "\"Id_Metodo_Pago\"=" + metodoPago.getMetodo_Pago() + ", "
                    + "Metodo_Pago=" + metodoPago.getMetodo_Pago() + '}';
            resp += ",";
        }
        if (!metodoPagos.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }

    // Metodo estatico para convertir un ArrayList de Tipos a JSON usando Gson
    public static String toArrayJSon(ArrayList<Metodo_Pago> metodoPagos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(metodoPagos);
    }
}

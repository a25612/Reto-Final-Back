package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private int Id_Pedido;
    private String Estado_Pedido;
    private Date Fecha_Hora;
    private String Direccion;
    private int Id_Cliente;
    private int Id_Empleado;
    private int Id_Metodo_Pago;

    public Pedidos(int Id_Pedido, String Estado_Pedido, Date Fecha_Hora, String Direccion, int Id_Cliente, int Id_Empleado, int Id_Metodo_Pago) {
        this.Id_Pedido = Id_Pedido;
        this.Estado_Pedido = Estado_Pedido;
        this.Fecha_Hora = Fecha_Hora;
        this.Direccion = Direccion;
        this.Id_Cliente = Id_Cliente;
        this.Id_Empleado = Id_Empleado;
        this.Id_Metodo_Pago = Id_Metodo_Pago;
    }

    public Pedidos() {
    }

    // GETTERS & SETTERS
    public int getId_Pedido() {
        return Id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        Id_Pedido = id_Pedido;
    }

    public String getEstado_Pedido() {
        return Estado_Pedido;
    }

    public void setEstado_Pedido(String estado_Pedido) {
        Estado_Pedido = estado_Pedido;
    }

    public Date getFecha_Hora() {
        return Fecha_Hora;
    }

    public void setFecha_Hora(Date fecha_Hora) {
        Fecha_Hora = fecha_Hora;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        Id_Empleado = id_Empleado;
    }

    public int getId_Metodo_Pago() {
        return Id_Metodo_Pago;
    }

    public void setId_Metodo_Pago(int id_Metodo_Pago) {
        Id_Metodo_Pago = id_Metodo_Pago;
    }

    @Override
    public String toString() {
        return "Pedido{" + "Id_Pedido=" + Id_Pedido + ", Estado_Pedido=" + Estado_Pedido + ", Fecha_Hora=" + Fecha_Hora + ", Direccion=" + Direccion + ", Id_Cliente=" + Id_Cliente + ", Id_Empleado=" + Id_Empleado + ", Id_Metodo_Pago=" + Id_Metodo_Pago + '}';
    }

    public static String toCadena(Pedidos pedido) {
        return "Pedido{"
                + "\"Id_Pedido\"=" + pedido.getId_Pedido() + ", "
                + "Estado_Pedido=" + pedido.getEstado_Pedido() + ", "
                + "Fecha_Hora=" + pedido.getFecha_Hora() + ", "
                + "Direccion=" + pedido.getDireccion() + ", "
                + "Id_Cliente=" + pedido.getId_Cliente() + ", "
                + "Id_Empleado=" + pedido.getId_Empleado() + ", "
                + "Id_Metodo_Pago=" + pedido.getId_Metodo_Pago() + '}';
    }

    public static String fromArrayToJson(ArrayList<Pedidos> pedidos) {
        String resp = "[";
        for (Pedidos pedido : pedidos) {
            resp += "{"
                    + "\"Id_Pedido\"=" + pedido.getId_Pedido() + ", "
                    + "Estado_Pedido=" + pedido.getEstado_Pedido() + ", "
                    + "Fecha_Hora=" + pedido.getFecha_Hora() + ", "
                    + "Direccion=" + pedido.getDireccion() + ", "
                    + "Id_Cliente=" + pedido.getId_Cliente() + ", "
                    + "Id_Empleado=" + pedido.getId_Empleado() + ", "
                    + "Id_Metodo_Pago=" + pedido.getId_Metodo_Pago() + '}';
            resp += ",";
        }
        if (!pedidos.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Pedidos> pedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(pedidos);
    }
}

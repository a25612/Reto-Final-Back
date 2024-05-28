package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

public class Detalle_Pedido {

    private int Id_Detalle_Pedido;
    private int Cantidad;
    private double precio;
    private int Id_Pedido;
    private int Id_Producto;

    public Detalle_Pedido(int Id_Detalle_Pedido, int Cantidad, Date Fecha_Hora, String Direccion, int Id_Cliente, int Id_Empleado, int Id_Metodo_Pago) {
        this.Id_Pedido = Id_Pedido;
        this.Id_Detalle_Pedido = Id_Detalle_Pedido;
        this.Cantidad = Cantidad;
        this.precio = precio;
        this.Id_Pedido = Id_Pedido;
        this.Id_Producto = Id_Producto;
    }

    public Detalle_Pedido() {
    }

    // GETTERS & SETTERS

    public int getId_Detalle_Pedido() {
        return Id_Detalle_Pedido;
    }

    public void setId_Detalle_Pedido(int id_Detalle_Pedido) {
        Id_Detalle_Pedido = id_Detalle_Pedido;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_Pedido() {
        return Id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        Id_Pedido = id_Pedido;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        Id_Producto = id_Producto;
    }

    @Override
    public String toString() {
        return "Detalle_Pedido{" + "Id_Detalle_Pedido=" + Id_Detalle_Pedido + ", Cantidad=" + Cantidad + ", Precio=" + precio + ", Id_Pedido=" + Id_Pedido + ", Id_Producto=" + Id_Producto + '}';
    }

    public static String toCadena(Detalle_Pedido detallePedido) {
        return "Detalle_Pedido{"
                + "\"Id_Detalle_Pedido\"=" + detallePedido.getId_Detalle_Pedido() + ", "
                + "Cantidad=" + detallePedido.getCantidad() + ", "
                + "Precio=" + detallePedido.getPrecio() + ", "
                + "Id_Pedido=" + detallePedido.getId_Pedido() + ", "
                + "Id_Producto=" + detallePedido.getId_Producto() +'}';
    }

    public static String fromArrayToJson(ArrayList<Detalle_Pedido> detallePedidos) {
        String resp = "[";
        for (Detalle_Pedido detallePedido  : detallePedidos) {
            resp += "{"
                    + "\"Id_Detalle_Pedido\"=" + detallePedido.getId_Detalle_Pedido() + ", "
                    + "Cantidad=" + detallePedido.getCantidad() + ", "
                    + "Precio=" + detallePedido.getPrecio() + ", "
                    + "Id_Pedido=" + detallePedido.getId_Pedido() + ", "
                    + "Id_Producto=" + detallePedido.getId_Producto() +'}';
            resp += ",";
        }
        if (!detallePedidos.isEmpty()) {
            resp = resp.substring(0, resp.length() - 1);
        }
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Detalle_Pedido> detallePedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(detallePedidos);
    }
}

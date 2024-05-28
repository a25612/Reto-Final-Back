package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Empleados {

    private String nombre, apellidos, cargo, telefono, DNI;
    private int Id_Empleado;

    public Empleados(int Id_Empleado, String nombre, String apellidos, String cargo, String telefono, String DNI) {
        this.Id_Empleado = Id_Empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.DNI = DNI;
        this.telefono = telefono;
    }

    public Empleados() {
    }

    //GETTERS & SETTERS
    public int getId_Empleado() {
        return Id_Empleado;
    }
    public void setId_Empleado(int id_Empleado) {
        Id_Empleado = id_Empleado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {

        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" + "Id_Empleado=" + Id_Empleado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", cargo=" + cargo + ", DNI=" + DNI + ", telefono=" + telefono +'}';
    }


    public static String toCadena(Empleados empleados) {
        return "Empleado{"
                + "\"Id_Empleado\"=" + empleados.getId_Empleado() + ", "
                + "nombre=" + empleados.getNombre() + ", "
                + "apellido=" + empleados.getApellidos() + ","
                + " cargo=" + empleados.getCargo() + ", "
                + "DNI=" + empleados.getDNI() + ", "
                + "telefono=" + empleados.getTelefono()+ '}';
    }

    public static String fromArrayToJson(ArrayList<Empleados> empleados) {
        String resp = "[";
        for (Empleados empleado : empleados) {
            resp += "{"
                    + "\"Id_Empleado\"=" + empleado.getId_Empleado() + ", "
                    + "nombre=" + empleado.getNombre() + ", "
                    + "apellido=" + empleado.getApellidos() + ","
                    + " cargo=" + empleado.getCargo() + ", "
                    + "DNI=" + empleado.getDNI() + ", "
                    + "telefono=" + empleado.getTelefono()+ '}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Empleados> empleados) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(empleados);
    }
}

package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class EmpleadosAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FIND_ALL":
                strReturn = findAll();
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        EmpleadosDAO empleadosDAO = new EmpleadosDAO();
        ArrayList<Empleados> empleados = empleadosDAO.findAll(null);
        return Empleados.toArrayJSon(empleados);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));
            String nombre = request.getParameter("NOMBRE");
            String apellidos = request.getParameter("APELLIDOS");
            String dni = request.getParameter("DNI");
            String telefono = request.getParameter("TELEFONO");
            String cargo = request.getParameter("CARGO");

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty() || apellidos == null || apellidos.isEmpty() ||
                    dni == null || dni.isEmpty() || telefono == null || telefono.isEmpty() ||
                    cargo == null || cargo.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            EmpleadosDAO empleadosDAO = new EmpleadosDAO();
            Empleados empleado = new Empleados();
            empleado.setId_Empleado(id_empleado);
            empleado.setNombre(nombre);
            empleado.setApellidos(apellidos);
            empleado.setDNI(dni);
            empleado.setTelefono(telefono);
            empleado.setCargo(cargo);

            int result = empleadosDAO.add(empleado);

            if (result > 0) {
                return "Empleado añadido con éxito.";
            } else {
                return "Error al añadir empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));

            EmpleadosDAO empleadosDAO = new EmpleadosDAO();
            int result = empleadosDAO.delete(id_empleado);

            if (result > 0) {
                return "Empleado eliminado con éxito.";
            } else {
                return "Error al eliminar empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String update(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));
            String nombre = request.getParameter("NOMBRE");
            String apellidos = request.getParameter("APELLIDOS");
            String dni = request.getParameter("DNI");
            String telefono = request.getParameter("TELEFONO");
            String cargo = request.getParameter("CARGO");

            // Validar que el ID esté presente
            if (id_empleado <= 0) {
                return "Error: El ID del empleado es obligatorio y debe ser un número entero positivo.";
            }

            EmpleadosDAO empleadosDAO = new EmpleadosDAO();
            Empleados empleado = new Empleados();
            empleado.setId_Empleado(id_empleado);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                empleado.setNombre(nombre);
            }
            if (apellidos != null && !apellidos.isEmpty()) {
                empleado.setApellidos(apellidos);
            }
            if (dni != null && !dni.isEmpty()) {
                empleado.setDNI(dni);
            }
            if (telefono != null && !telefono.isEmpty()) {
                empleado.setTelefono(telefono);
            }
            if (cargo != null && !cargo.isEmpty()) {
                empleado.setCargo(cargo);
            }

            int result = empleadosDAO.update(empleado);

            if (result > 0) {
                return "Empleado actualizado con éxito.";
            } else {
                return "Error al actualizar empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}


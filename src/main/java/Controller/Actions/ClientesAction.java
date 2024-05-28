package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class ClientesAction implements IAction {
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
            case "LOGIN":
                strReturn = login(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        ClientesDAO clientesDAO = new ClientesDAO();
        ArrayList<Clientes> clientes = clientesDAO.findAll(null);
        return Clientes.toArrayJSon(clientes);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
            String nombre = request.getParameter("NOMBRE");
            String email = request.getParameter("EMAIL");
            String contrasena = request.getParameter("CONTRASENA");
            String telefono = request.getParameter("TELEFONO");

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty() || email == null || email.isEmpty() ||
                    contrasena == null || contrasena.isEmpty() || telefono == null || telefono.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            ClientesDAO clientesDAO = new ClientesDAO();
            Clientes cliente = new Clientes();
            cliente.setId_cliente(id_cliente);
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            cliente.setContrasena(contrasena);
            cliente.setTelefono(telefono);

            int result = clientesDAO.add(cliente);

            if (result > 0) {
                return "Cliente añadido con éxito.";
            } else {
                return "Error al añadir cliente.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del cliente debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));

            ClientesDAO clientesDAO = new ClientesDAO();
            int result = clientesDAO.delete(id_cliente);

            if (result > 0) {
                return "Cliente eliminado con éxito.";
            } else {
                return "Error al eliminar cliente.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del cliente debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String update(HttpServletRequest request) {
        try {
            int id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
            String nombre = request.getParameter("NOMBRE");
            String email = request.getParameter("EMAIL");
            String contrasena = request.getParameter("CONTRASENA");
            String telefono = request.getParameter("TELEFONO");

            // Validar que el ID esté presente
            if (id_cliente <= 0) {
                return "Error: El ID del cliente es obligatorio y debe ser un número entero positivo.";
            }

            ClientesDAO clientesDAO = new ClientesDAO();
            Clientes cliente = new Clientes();
            cliente.setId_cliente(id_cliente);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                cliente.setNombre(nombre);
            }
            if (email != null && !email.isEmpty()) {
                cliente.setEmail(email);
            }
            if (contrasena != null && !contrasena.isEmpty()) {
                cliente.setContrasena(contrasena);
            }
            if (telefono != null && !telefono.isEmpty()) {
                cliente.setTelefono(telefono);
            }

            int result = clientesDAO.update(cliente);

            if (result > 0) {
                return "Cliente actualizado con éxito.";
            } else {
                return "Error al actualizar cliente.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del cliente debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    private String login(HttpServletRequest request) {
        String nombre = request.getParameter("username");
        String contrasena = request.getParameter("password");

        if (nombre == null || nombre.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            return "Error: Todos los campos son obligatorios.";
        }

        ClientesDAO clientesDAO = new ClientesDAO();
        Clientes cliente = new Clientes();
        cliente.setNombre(nombre);
        cliente.setContrasena(contrasena);

        ArrayList<Clientes> clientes = clientesDAO.findAll(cliente);

        if (clientes.size() == 1) {
            return "Login exitoso";
        } else {
            return "Error: Nombre o contraseña incorrectos";
        }
    }
}
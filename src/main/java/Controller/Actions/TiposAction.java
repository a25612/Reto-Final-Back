package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class TiposAction implements IAction {
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
        TiposDAO tiposDAO = new TiposDAO();
        ArrayList<Tipos> tipos = tiposDAO.findAll(null);
        return Tipos.toArrayJSon(tipos);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_tipo = Integer.parseInt(request.getParameter("ID_TIPO"));
            String nombre = request.getParameter("NOMBRE");

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            TiposDAO tiposDAO = new TiposDAO();
            Tipos tipo = new Tipos();
            tipo.setId_Tipo(id_tipo);
            tipo.setNombre(nombre);

            int result = tiposDAO.add(tipo);

            if (result > 0) {
                return "Tipo añadido con éxito.";
            } else {
                return "Error al añadir tipo.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del tipo debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_tipo = Integer.parseInt(request.getParameter("ID_TIPO"));

            TiposDAO tiposDAO = new TiposDAO();
            int result = tiposDAO.delete(id_tipo);

            if (result > 0) {
                return "Tipo eliminado con éxito.";
            } else {
                return "Error al eliminar tipo.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del tipo debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String update(HttpServletRequest request) {
        try {
            int id_tipo = Integer.parseInt(request.getParameter("ID_TIPO"));
            String nombre = request.getParameter("NOMBRE");

            // Validar que el ID esté presente
            if (id_tipo <= 0) {
                return "Error: El ID del tipo es obligatorio y debe ser un número entero positivo.";
            }

            TiposDAO tiposDAO = new TiposDAO();
            Tipos tipo = new Tipos();
            tipo.setId_Tipo(id_tipo);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                tipo.setNombre(nombre);
            }

            int result = tiposDAO.update(tipo);

            if (result > 0) {
                return "Tipo actualizado con éxito.";
            } else {
                return "Error al actualizar tipo.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del tipo debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}


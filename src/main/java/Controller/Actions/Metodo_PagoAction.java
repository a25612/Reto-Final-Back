package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class Metodo_PagoAction implements IAction {
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
        Metodo_PagoDAO metodoPagoDAO = new Metodo_PagoDAO();
        ArrayList<Metodo_Pago> metodoPagos = metodoPagoDAO.findAll(null);
        return Metodo_Pago.toArrayJSon(metodoPagos);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_metodo_pago = Integer.parseInt(request.getParameter("ID_METODO_PAGO"));
            String metodo_pago = request.getParameter("METODO_PAGO");

            // Validar que todos los parámetros estén presentes
            if (metodo_pago == null || metodo_pago.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            Metodo_PagoDAO metodoPagoDAO = new Metodo_PagoDAO();
            Metodo_Pago metodoPago = new Metodo_Pago();
            metodoPago.setId_Metodo_Pago(id_metodo_pago);
            metodoPago.setMetodo_Pago(metodo_pago);

            int result = metodoPagoDAO.add(metodoPago);

            if (result > 0) {
                return "Método de pago añadido con éxito.";
            } else {
                return "Error al añadir método de pago.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del método de pago debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_metodo_pago = Integer.parseInt(request.getParameter("ID_METODO_PAGO"));

            Metodo_PagoDAO metodoPagoDAO = new Metodo_PagoDAO();
            int result = metodoPagoDAO.delete(id_metodo_pago);

            if (result > 0) {
                return "Método de pago eliminado con éxito.";
            } else {
                return "Error al eliminar método de pago.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del método de pago debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String update(HttpServletRequest request) {
        try {
            int id_metodo_pago = Integer.parseInt(request.getParameter("ID_METODO_PAGO"));
            String metodo_pago = request.getParameter("METODO_PAGO");

            // Validar que el ID esté presente
            if (id_metodo_pago <= 0) {
                return "Error: El ID del método de pago es obligatorio y debe ser un número entero positivo.";
            }

            Metodo_PagoDAO metodoPagoDAO = new Metodo_PagoDAO();
            Metodo_Pago metodoPago = new Metodo_Pago();
            metodoPago.setId_Metodo_Pago(id_metodo_pago);

            // Only set non-null fields
            if (metodo_pago != null && !metodo_pago.isEmpty()) {
                metodoPago.setMetodo_Pago(metodo_pago);
            }

            int result = metodoPagoDAO.update(metodoPago);

            if (result > 0) {
                return "Método de pago actualizado con éxito.";
            } else {
                return "Error al actualizar método de pago.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del método de pago debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}


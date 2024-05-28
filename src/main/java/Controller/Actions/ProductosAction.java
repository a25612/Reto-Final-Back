package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;

public class ProductosAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FIND_ALL":
                strReturn = findAll(request);
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

    private String findAll(HttpServletRequest request) {
        ProductosDAO productosDAO = new ProductosDAO();
        Productos filtro = null;

        String idTipoStr = request.getParameter("ID_TIPO");
        if (idTipoStr != null && !idTipoStr.isEmpty()) {
            try {
                int idTipo = Integer.parseInt(idTipoStr);
                filtro = new Productos();
                filtro.setId_Tipo(idTipo);
            } catch (NumberFormatException e) {
                return "Error: El ID del tipo debe ser un número entero.";
            }
        }

        ArrayList<Productos> productos = productosDAO.findAll(filtro);
        return Productos.toArrayJSon(productos);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));
            String nombre = request.getParameter("NOMBRE");
            String descripcion = request.getParameter("DESCRIPCION");
            String precioStr = request.getParameter("PRECIO").replace(",", ".");
            double precio = Double.parseDouble(precioStr);
            int id_tipo = Integer.parseInt(request.getParameter("ID_TIPO"));

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty() || descripcion == null || descripcion.isEmpty() ||
                    precio <= 0 || id_tipo <= 0) {
                return "Error: Todos los campos son obligatorios.";
            }

            ProductosDAO productosDAO = new ProductosDAO();
            Productos producto = new Productos();
            producto.setId_Producto(id_producto);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setId_Tipo(id_tipo);

            int result = productosDAO.add(producto);

            if (result > 0) {
                return "Producto añadido con éxito.";
            } else {
                return "Error al añadir producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: Los campos deben tener valores numéricos válidos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));

            ProductosDAO productosDAO = new ProductosDAO();
            int result = productosDAO.delete(id_producto);

            if (result > 0) {
                return "Producto eliminado con éxito.";
            } else {
                return "Error al eliminar producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del producto debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String update(HttpServletRequest request) {
        try {
            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));
            String nombre = request.getParameter("NOMBRE");
            String descripcion = request.getParameter("DESCRIPCION");
            double precio = Double.parseDouble(request.getParameter("PRECIO"));
            int id_tipo = Integer.parseInt(request.getParameter("ID_TIPO"));

            // Validar que el ID esté presente
            if (id_producto <= 0) {
                return "Error: El ID del producto es obligatorio y debe ser un número entero positivo.";
            }

            ProductosDAO productosDAO = new ProductosDAO();
            Productos producto = new Productos();
            producto.setId_Producto(id_producto);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                producto.setNombre(nombre);
            }
            if (descripcion != null && !descripcion.isEmpty()) {
                producto.setDescripcion(descripcion);
            }
            if (precio > 0) {
                producto.setPrecio(precio);
            }
            if (id_tipo > 0) {
                producto.setId_Tipo(id_tipo);
            }

            int result = productosDAO.update(producto);

            if (result > 0) {
                return "Producto actualizado con éxito.";
            } else {
                return "Error al actualizar producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: Los campos deben tener valores numéricos válidos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}


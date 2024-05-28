package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductosDAO implements IDao<Productos, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTOS WHERE 1=1";
    private final String SQL_ADD_PRODUCTO = "INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, PRECIO, ID_TIPO) VALUES";
    private final String SQL_DELETE_PRODUCTO = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = ";
    private final String SQL_UPDATE_PRODUCTO = "UPDATE PRODUCTOS SET ";

    @Override
    public int add(Productos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            String sql = SQL_ADD_PRODUCTO + "("
                    + bean.getId_Producto() + ", '"
                    + bean.getNombre() + "', '"
                    + bean.getDescripcion() + "', "
                    + bean.getPrecio() + ", "
                    + bean.getId_Tipo() + ")";

            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Producto insertado con éxito.");
        } else {
            System.out.println("Error al insertar producto.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_PRODUCTO + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el producto.");
        }
        return resp;
    }

    @Override
    public int update(Productos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            StringBuilder sql = new StringBuilder(SQL_UPDATE_PRODUCTO);

            if (bean.getNombre() != null) {
                sql.append("NOMBRE = '").append(bean.getNombre()).append("', ");
            }
            if (bean.getDescripcion() != null) {
                sql.append("DESCRIPCION = '").append(bean.getDescripcion()).append("', ");
            }
            if (bean.getPrecio() != 0) {
                sql.append("PRECIO = ").append(bean.getPrecio()).append(", ");
            }
            if (bean.getId_Tipo() != 0) {
                sql.append("ID_TIPO = ").append(bean.getId_Tipo()).append(", ");
            }

            // Remove the last comma and space
            sql.setLength(sql.length() - 2);

            sql.append(" WHERE ID_PRODUCTO = ").append(bean.getId_Producto());

            resp = motor.execute(sql.toString());
            System.out.println(sql.toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Producto actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el producto.");
        }
        return resp;
    }

    @Override
    public ArrayList<Productos> findAll(Productos bean) {
        ArrayList<Productos> productos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;

            if (bean != null && bean.getId_Tipo() != 0) {
                sql = "SELECT P.ID_PRODUCTO, P.NOMBRE, P.DESCRIPCION, P.PRECIO, P.ID_TIPO " +
                        "FROM PRODUCTOS P " +
                        "INNER JOIN TIPOS T ON T.ID_TIPO = P.ID_TIPO " +
                        "WHERE P.ID_TIPO = " + bean.getId_Tipo();
            }

            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Productos producto = new Productos();

                producto.setId_Producto(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setPrecio(rs.getDouble("PRECIO"));
                producto.setId_Tipo(rs.getInt("ID_TIPO"));

                productos.add(producto);
            }

        } catch (Exception ex) {
            productos.clear();
        } finally {
            motor.disconnect();
        }
        return productos;
    }
}


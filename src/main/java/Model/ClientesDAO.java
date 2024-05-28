package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDAO implements IDao <Clientes, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTES WHERE 1=1";

    private final String SQL_ADD_CLIENTE = "INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, EMAIL, CONTRASENA, TELEFONO) VALUES";

    private final String SQL_DELETE_CLIENTE = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ";

    private final String SQL_UPDATE_CLIENTE = "UPDATE CLIENTES SET ";


    @Override
    public int update(Clientes bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            StringBuilder sql = new StringBuilder(SQL_UPDATE_CLIENTE);

            if (bean.getNombre() != null) {
                sql.append("NOMBRE = '").append(bean.getNombre()).append("', ");
            }
            if (bean.getEmail() != null) {
                sql.append("EMAIL = '").append(bean.getEmail()).append("', ");
            }
            if (bean.getContrasena() != null) {
                sql.append("CONTRASENA = '").append(bean.getContrasena()).append("', ");
            }
            if (bean.getTelefono() != null) {
                sql.append("TELEFONO = '").append(bean.getTelefono()).append("', ");
            }

            // Remove the last comma and space
            sql.setLength(sql.length() - 2);

            sql.append(" WHERE ID_CLIENTE = ").append(bean.getId_cliente());

            resp = motor.execute(sql.toString());
            System.out.println(sql.toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Cliente actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }
        return resp;
    }

    @Override
    public ArrayList<Clientes> findAll(Clientes bean) {
        ArrayList<Clientes> clientes = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_cliente() != 0) {
                    sql += " AND ID_CLIENTE='" + bean.getId_cliente() + "'";
                }
                if (bean.getNombre() != null) {
                    sql += " AND NOMBRE='" + bean.getNombre() + "'";
                }
                if (bean.getEmail() != null) {
                    sql += " AND EMAIL='" + bean.getEmail() + "'";
                }
                if (bean.getContrasena() != null) {
                    sql += " AND CONTRASENA='" + bean.getContrasena() + "'";
                }
                if (bean.getTelefono() != null) {
                    sql += " AND TELEFONO='" + bean.getTelefono() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Clientes cliente = new Clientes();

                cliente.setId_cliente(rs.getInt("ID_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setContrasena(rs.getString("CONTRASENA"));
                cliente.setTelefono(rs.getString("TELEFONO"));

                clientes.add(cliente);
            }

        } catch (Exception ex) {
            clientes.clear();
        } finally {
            motor.disconnect();
        }
        return clientes;
    }


    @Override
    public int add(Clientes bean) {
        Clientes clientes = (Clientes) bean;
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            String sql = SQL_ADD_CLIENTE + "("
                    + clientes.getId_cliente() + ", '"
                    + clientes.getNombre() + "', '"
                    + clientes.getEmail() + "', '"
                    + clientes.getContrasena() + "', '"
                    + clientes.getTelefono() +"')";

            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Usuario insertada con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        motor.connect();
        try {
            String sql = SQL_DELETE_CLIENTE + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }
}



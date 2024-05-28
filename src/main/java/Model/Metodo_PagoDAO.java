package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Metodo_PagoDAO implements IDao<Metodo_Pago, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM METODO_PAGO WHERE 1=1";
    private final String SQL_ADD_METODO_PAGO = "INSERT INTO METODO_PAGO (ID_METODO_PAGO, METODO_PAGO) VALUES";
    private final String SQL_DELETE_METODO_PAGO = "DELETE FROM METODO_PAGO WHERE ID_METODO_PAGO = ";
    private final String SQL_UPDATE_METODO_PAGO = "UPDATE METODO_PAGO SET ";

    @Override
    public int add(Metodo_Pago bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            String sql = SQL_ADD_METODO_PAGO + "("
                    + bean.getId_Metodo_Pago() + ", '"
                    + bean.getMetodo_Pago() + "')";

            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Método de pago insertado con éxito.");
        } else {
            System.out.println("Error al insertar método de pago.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_METODO_PAGO + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Método de pago eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el método de pago.");
        }
        return resp;
    }

    @Override
    public int update(Metodo_Pago bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            StringBuilder sql = new StringBuilder(SQL_UPDATE_METODO_PAGO);

            if (bean.getMetodo_Pago() != null) {
                sql.append("METODO_PAGO = '").append(bean.getMetodo_Pago()).append("', ");
            }

            // Remove the last comma and space
            sql.setLength(sql.length() - 2);

            sql.append(" WHERE ID_METODO_PAGO = ").append(bean.getId_Metodo_Pago());

            resp = motor.execute(sql.toString());
            System.out.println(sql.toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Método de pago actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el método de pago.");
        }
        return resp;
    }

    @Override
    public ArrayList<Metodo_Pago> findAll(Metodo_Pago bean) {
        ArrayList<Metodo_Pago> metodoPagos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Metodo_Pago() != 0) {
                    sql += " AND ID_METODO_PAGO=" + bean.getId_Metodo_Pago();
                }
                if (bean.getMetodo_Pago() != null) {
                    sql += " AND METODO_PAGO='" + bean.getMetodo_Pago() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Metodo_Pago metodoPago = new Metodo_Pago();

                metodoPago.setId_Metodo_Pago(rs.getInt("ID_METODO_PAGO"));
                metodoPago.setMetodo_Pago(rs.getString("METODO_PAGO"));

                metodoPagos.add(metodoPago);
            }

        } catch (Exception ex) {
            metodoPagos.clear();
        } finally {
            motor.disconnect();
        }
        return metodoPagos;
    }
}


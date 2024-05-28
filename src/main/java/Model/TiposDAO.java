package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TiposDAO implements IDao<Tipos, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM TIPOS WHERE 1=1";
    private final String SQL_ADD_TIPO = "INSERT INTO TIPOS (ID_TIPO, NOMBRE) VALUES";
    private final String SQL_DELETE_TIPO = "DELETE FROM TIPOS WHERE ID_TIPO = ";
    private final String SQL_UPDATE_TIPO = "UPDATE TIPOS SET ";

    @Override
    public int add(Tipos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            String sql = SQL_ADD_TIPO + "("
                    + bean.getId_Tipo() + ", '"
                    + bean.getNombre() + "')";

            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Tipo insertado con éxito.");
        } else {
            System.out.println("Error al insertar tipo.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_TIPO + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Tipo eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el tipo.");
        }
        return resp;
    }

    @Override
    public int update(Tipos bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            StringBuilder sql = new StringBuilder(SQL_UPDATE_TIPO);

            if (bean.getNombre() != null) {
                sql.append("NOMBRE = '").append(bean.getNombre()).append("', ");
            }

            // Remove the last comma and space
            sql.setLength(sql.length() - 2);

            sql.append(" WHERE ID_TIPO = ").append(bean.getId_Tipo());

            resp = motor.execute(sql.toString());
            System.out.println(sql.toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Tipo actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el tipo.");
        }
        return resp;
    }

    @Override
    public ArrayList<Tipos> findAll(Tipos bean) {
        ArrayList<Tipos> tipos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Tipo() != 0) {
                    sql += "AND ID_TIPO=" + bean.getId_Tipo() + " ";
                }
                if (bean.getNombre() != null) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Tipos tipo = new Tipos();
                tipo.setId_Tipo(rs.getInt("ID_TIPO"));
                tipo.setNombre(rs.getString("NOMBRE"));
                tipos.add(tipo);
            }

        } catch (Exception ex) {
            tipos.clear();
        } finally {
            motor.disconnect();
        }
        return tipos;
    }
}


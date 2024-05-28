package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadosDAO implements IDao<Empleados, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS WHERE 1=1";
    private final String SQL_ADD_EMPLEADO = "INSERT INTO EMPLEADOS (ID_EMPLEADO, NOMBRE, APELLIDOS, DNI, TELEFONO, CARGO) VALUES";
    private final String SQL_DELETE_EMPLEADO = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ";
    private final String SQL_UPDATE_EMPLEADO = "UPDATE EMPLEADOS SET ";

    @Override
    public int add(Empleados bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            String sql = SQL_ADD_EMPLEADO + "("
                    + bean.getId_Empleado() + ", '"
                    + bean.getNombre() + "', '"
                    + bean.getApellidos() + "', '"
                    + bean.getDNI() + "', '"
                    + bean.getTelefono() + "', '"
                    + bean.getCargo() + "')";

            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Empleado insertado con éxito.");
        } else {
            System.out.println("Error al insertar empleado.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_EMPLEADO + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Empleado eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el empleado.");
        }
        return resp;
    }

    @Override
    public int update(Empleados bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            StringBuilder sql = new StringBuilder(SQL_UPDATE_EMPLEADO);

            if (bean.getNombre() != null) {
                sql.append("NOMBRE = '").append(bean.getNombre()).append("', ");
            }
            if (bean.getApellidos() != null) {
                sql.append("APELLIDOS = '").append(bean.getApellidos()).append("', ");
            }
            if (bean.getDNI() != null) {
                sql.append("DNI = '").append(bean.getDNI()).append("', ");
            }
            if (bean.getTelefono() != null) {
                sql.append("TELEFONO = '").append(bean.getTelefono()).append("', ");
            }
            if (bean.getCargo() != null) {
                sql.append("CARGO = '").append(bean.getCargo()).append("', ");
            }

            // Remove the last comma and space
            sql.setLength(sql.length() - 2);

            sql.append(" WHERE ID_EMPLEADO = ").append(bean.getId_Empleado());

            resp = motor.execute(sql.toString());
            System.out.println(sql.toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Empleado actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el empleado.");
        }
        return resp;
    }

    @Override
    public ArrayList<Empleados> findAll(Empleados bean) {
        ArrayList<Empleados> empleados = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Empleado() != 0) {
                    sql += " AND ID_EMPLEADO=" + bean.getId_Empleado();
                }
                if (bean.getNombre() != null) {
                    sql += " AND NOMBRE='" + bean.getNombre() + "'";
                }
                if (bean.getApellidos() != null) {
                    sql += " AND APELLIDOS='" + bean.getApellidos() + "'";
                }
                if (bean.getDNI() != null) {
                    sql += " AND DNI='" + bean.getDNI() + "'";
                }
                if (bean.getTelefono() != null) {
                    sql += " AND TELEFONO='" + bean.getTelefono() + "'";
                }
                if (bean.getCargo() != null) {
                    sql += " AND CARGO='" + bean.getCargo() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Empleados empleado = new Empleados();

                empleado.setId_Empleado(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE"));
                empleado.setApellidos(rs.getString("APELLIDOS"));
                empleado.setDNI(rs.getString("DNI"));
                empleado.setTelefono(rs.getString("TELEFONO"));
                empleado.setCargo(rs.getString("CARGO"));

                empleados.add(empleado);
            }

        } catch (Exception ex) {
            empleados.clear();
        } finally {
            motor.disconnect();
        }
        return empleados;
    }
}



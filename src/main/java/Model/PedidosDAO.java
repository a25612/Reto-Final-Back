package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidosDAO implements IDao<Pedidos, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PEDIDOS WHERE 1=1 ";

    @Override
    public int add(Pedidos bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Pedidos bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Pedidos> findAll(Pedidos bean) {
        ArrayList<Pedidos> pedidos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Pedido() != 0) {
                    sql += "AND ID_PEDIDO=" + bean.getId_Pedido() + " ";
                }
                if (bean.getEstado_Pedido() != null) {
                    sql += "AND ESTADO_PEDIDO='" + bean.getEstado_Pedido() + "' ";
                }
                if (bean.getFecha_Hora() != null) {
                    sql += "AND FECHA_HORA=TO_DATE('" + new java.sql.Date(bean.getFecha_Hora().getTime()) + "', 'YYYY-MM-DD HH24:MI:SS') ";
                }
                if (bean.getDireccion() != null) {
                    sql += "AND DIRECCION='" + bean.getDireccion() + "' ";
                }
                if (bean.getId_Cliente() != 0) {
                    sql += "AND ID_CLIENTE=" + bean.getId_Cliente() + " ";
                }
                if (bean.getId_Empleado() != 0) {
                    sql += "AND ID_EMPLEADO=" + bean.getId_Empleado() + " ";
                }
                if (bean.getId_Metodo_Pago() != 0) {
                    sql += "AND ID_METODO_PAGO=" + bean.getId_Metodo_Pago() + " ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedidos pedido = new Pedidos();

                pedido.setId_Pedido(rs.getInt("ID_PEDIDO"));
                pedido.setEstado_Pedido(rs.getString("ESTADO_PEDIDO"));
                pedido.setFecha_Hora(rs.getDate("FECHA_HORA"));
                pedido.setDireccion(rs.getString("DIRECCION"));
                pedido.setId_Cliente(rs.getInt("ID_CLIENTE"));
                pedido.setId_Empleado(rs.getInt("ID_EMPLEADO"));
                pedido.setId_Metodo_Pago(rs.getInt("ID_METODO_PAGO"));

                pedidos.add(pedido);
            }

        } catch (Exception ex) {
            pedidos.clear();
        } finally {
            motor.disconnect();
        }
        return pedidos;
    }
}

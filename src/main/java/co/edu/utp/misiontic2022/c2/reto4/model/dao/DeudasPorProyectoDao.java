package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class DeudasPorProyectoDao {
    
    public List<DeudasPorProyectoVo> listarProyectosAdeudados(Double limite) throws SQLException {
        List<DeudasPorProyectoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        var query = " SELECT p.ID_Proyecto AS ID, SUM(c.Cantidad * mc.Precio_Unidad) AS VALOR"
            + " FROM Compra c"
            + " INNER JOIN Proyecto p ON (c.ID_Proyecto = p.ID_Proyecto)"
            + " INNER JOIN MaterialConstruccion mc ON (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion)"
            + " WHERE c.Pagado = 'No'"
            + " GROUP BY p.ID_Proyecto"
            + " HAVING SUM(c.Cantidad * mc.Precio_Unidad) > ?"
            + " ORDER BY SUM(c.Cantidad * mc.Precio_Unidad) DESC";
        try (var stmt = conn.prepareStatement(query)){
            stmt.setDouble(1, limite);
            try (var rset = stmt.executeQuery()) {
                while (rset.next()){
                    var vo = new DeudasPorProyectoVo();
                    vo.setId(rset.getInt("ID"));
                    vo.setValor(rset.getDouble("VALOR"));
                    respuesta.add(vo);
                }
            }
        } finally {
            if (conn != null){
                conn.close();
            }
        }
        return respuesta;
    }
}

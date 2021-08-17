package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class ComprasDeLiderDao {
    
    public List<ComprasDeLiderVo> listarComprasLider() throws SQLException {
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        var query = " SELECT l.Nombre||' '||l.Primer_Apellido||' '||l.Segundo_Apellido AS LIDER, SUM(c.Cantidad * mc.Precio_Unidad) AS VALOR"
            + " FROM Lider l"
            + " INNER JOIN Proyecto p ON (p.ID_Lider = l.ID_Lider)"
            + " INNER JOIN Compra c ON (c.ID_Proyecto = p.ID_Proyecto)"
            + " INNER JOIN MaterialConstruccion mc ON (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion)"
            + " GROUP BY l.Nombre, l.Primer_Apellido, l.Segundo_Apellido"
            + " ORDER BY SUM(c.Cantidad * mc.Precio_Unidad) DESC"
            + " LIMIT 10";
        try (var stmt = conn.prepareStatement(query)){
            try (var rset = stmt.executeQuery()) {
                while (rset.next()){
                    var vo = new ComprasDeLiderVo();
                    vo.setLider(rset.getString("LIDER"));
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

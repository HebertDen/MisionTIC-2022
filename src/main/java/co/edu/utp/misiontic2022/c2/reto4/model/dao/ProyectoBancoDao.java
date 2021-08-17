package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class ProyectoBancoDao {

    public List<ProyectoBancoVo> listarProyectos(String banco) throws SQLException {
        List<ProyectoBancoVo> respuesta = new ArrayList<>(); //Lista creada para enviar al controlador
        var conn = JDBCUtilities.getConnection(); //Conexión a la base de datos
        //Parámetros para generar la plantilla
        var query = " SELECT p.ID_Proyecto AS ID, p.Constructora AS CONSTRUCTORA, p.Ciudad AS CIUDAD, p.Clasificacion AS CLASIFICACION, t.Estrato AS ESTRATO, l.Nombre||' '||l.Primer_Apellido||' '||l.Segundo_Apellido AS LIDER"
                + " FROM Proyecto p"
                + " INNER JOIN Tipo t ON (p.ID_Tipo = t.ID_Tipo)"
                + " INNER JOIN Lider l ON (p.ID_Lider = l.ID_Lider)"
                + " WHERE p.Banco_Vinculado = ?"
                + " ORDER BY p.Fecha_Inicio DESC, p.Ciudad ASC, p.Constructora";
        try (var stmt = conn.prepareStatement(query)){ //Elaboración de plantilla, inicio y cierre del flujo de datos
            stmt.setString(1, banco); //Asigna al primer item (?), el parámetro asignado
            try (var rset = stmt.executeQuery()){ //Ejecuta la plantilla, inicio y cierre del flujo de datos
                while (rset.next()){ //Mientras que rset tenga a donde pasar, es decir, que tenga una nueva línea de información
                    var vo = new ProyectoBancoVo(); //Instancio objeto de la clase ProyectoBancoVo que va a contener la información extraída de la base de datos
                    //Estraigo la información de la base de datos a los atributos que posee la clase ProyectoBancoVo
                    vo.setId(rset.getInt("ID"));
                    vo.setConstructora(rset.getString("CONSTRUCTORA"));
                    vo.setCiudad(rset.getString("CIUDAD"));
                    vo.setClasificacion(rset.getString("CLASIFICACION"));
                    vo.setEstrato(rset.getInt("ESTRATO"));
                    vo.setLider(rset.getString("Lider"));
                    
                    respuesta.add(vo); //Añado el objeto instanciado con los valores necesarios a una lista que será la respuesta/solución al problema
                }
            }
        } finally {
            if (conn != null){ //Si la conexión es exitosa cerrar el flujo de datos
                conn.close();
            }
        }
        return respuesta;
    }

}

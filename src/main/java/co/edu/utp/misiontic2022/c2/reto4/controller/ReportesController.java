package co.edu.utp.misiontic2022.c2.reto4.controller;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.DeudasPorProyectoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

public class ReportesController {

    private ProyectoBancoDao proyectoBancoDao;
    private DeudasPorProyectoDao deudasPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;

    public ReportesController() {
        proyectoBancoDao = new ProyectoBancoDao();
        deudasPorProyectoDao = new DeudasPorProyectoDao();
        comprasDeLiderDao = new ComprasDeLiderDao();
    }

    public List<ProyectoBancoVo> listarProyectosFinanciadosPorBanco(String banco) throws SQLException {
        return proyectoBancoDao.listarProyectos(banco);
    }

    public List<DeudasPorProyectoVo> listarProyectosAdeudados(Double limite) throws SQLException {
        return deudasPorProyectoDao.listarProyectosAdeudados(limite);
    }

    public List<ComprasDeLiderVo> listarComprasLider() throws SQLException {
        return comprasDeLiderDao.listarComprasLider();
    }
}

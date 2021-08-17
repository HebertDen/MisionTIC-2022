package co.edu.utp.misiontic2022.c2.reto4.view;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.reto4.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

public class ReportesView {

    private ReportesController controller;

    public ReportesView() {
        controller = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }
    
    public void proyectosFinanciadosPorBanco(String banco) {
        try {
            System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
            if (banco != null && !banco.isBlank()) {
                System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s",
                    "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
                System.out.println(repitaCaracter('-', 105));
                
                var lista = controller.listarProyectosFinanciadosPorBanco(banco);
                for (ProyectoBancoVo proyectoBancoVo : lista) {
                    System.out.printf(String.format("%3d %-25s %-20s %-15s %7d %-30s %n",
                        proyectoBancoVo.getId(), proyectoBancoVo.getConstructora(), proyectoBancoVo.getCiudad(), proyectoBancoVo.getClasificacion(), proyectoBancoVo.getEstrato(), proyectoBancoVo.getLider()));
                }
            }
        } catch (SQLException e) {
            System.out.print("Error: " + e);
            e.printStackTrace();
        }
    }
    
    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        try {
            System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
                + repitaCaracter('=', 1));
            if (limiteInferior != null) {
                System.out.println(String.format("%3s %14s", "ID", "VALOR "));
                System.out.println(repitaCaracter('-', 29));

                var lista = controller.listarProyectosAdeudados(limiteInferior);
                for (DeudasPorProyectoVo deudasPorProyectoVo : lista) {
                    System.out.println(String.format("%3d %,15.1f",
                        deudasPorProyectoVo.getId(), deudasPorProyectoVo.getValor()));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void lideresQueMasGastan() {
        try {
            System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
                +  repitaCaracter('=', 7));
            System.out.println(String.format("%-25s %14s", "LIDER", "VALOR "));
            System.out.println(repitaCaracter('-', 41));

            var lista = controller.listarComprasLider();
            for (ComprasDeLiderVo comprasDeLiderVo : lista) {
                System.out.println(String.format("%-25s %,15.1f",
                    comprasDeLiderVo.getLider(), comprasDeLiderVo.getValor()));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}

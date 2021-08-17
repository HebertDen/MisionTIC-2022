package co.edu.utp.misiontic2022.c2.reto4.view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.reto4.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

public class ReportesGUI extends JFrame {

    private JTable tbl;
    private JButton btnProyectosF, btnProyectosAd, btnLideres;
    private ReportesController controller;
    
    public ReportesGUI() {
        controller = new ReportesController();
        initComponets();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initComponets() {
        setTitle("Demo del reto 4 y 5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var panel = new JPanel();
        getContentPane().add(panel, BorderLayout.PAGE_START);
        
        btnProyectosF = new JButton("Proyectos Financiados por un Banco");
        btnProyectosF.addActionListener(e -> CargarTblProyectosFinanciados());

        btnProyectosAd = new JButton("Total Adeudado por cada Proyecto");
        btnProyectosAd.addActionListener(e -> CargarTblDeudasProyectos());

        btnLideres = new JButton("Top 10 de Líderes que más Gastan");
        btnLideres.addActionListener(e -> CargarTblComprasLideres());
        
        panel.add(btnProyectosF);
        panel.add(btnProyectosAd);
        panel.add(btnLideres);

        
        tbl = new JTable();
        getContentPane().add(new JScrollPane(tbl), BorderLayout.CENTER);
    }

    private void CargarTblProyectosFinanciados() {
        try {
            var tblModel = new ProyectosTableModel();
            var banco = JOptionPane.showInputDialog(null, "Ingrese el nombre del banco correctamente");
            if (banco.equals("")){
                JOptionPane.showMessageDialog(null, "Lo sentimos, nombre ingresado no válido", getTitle(), JOptionPane.ERROR_MESSAGE);
            } else{
                var lst = controller.listarProyectosFinanciadosPorBanco(banco);
                tblModel.setData(lst);
                tbl.setModel(tblModel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CargarTblDeudasProyectos() {
        try {
            var tblModel = new DeudasTableModel();
            var limite = JOptionPane.showInputDialog(null, "Ingrese el límite inferior dado");
            if (limite.equals("")){
                JOptionPane.showMessageDialog(null, "Lo sentimos, valor ingresado no válido", getTitle(), JOptionPane.ERROR_MESSAGE);
            } else{
                var lst = controller.listarProyectosAdeudados(Double.valueOf(limite));
                tblModel.setData(lst);
                tbl.setModel(tblModel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CargarTblComprasLideres() {
        try {
            var tblModel = new ComprasLiderTableModel();
            var lst = controller.listarComprasLider();
            tblModel.setData(lst);
            tbl.setModel(tblModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ProyectosTableModel extends AbstractTableModel {

        List<ProyectoBancoVo> proyectos;

        public ProyectosTableModel() {
            proyectos = new ArrayList<>();
        }

        public void setData(List<ProyectoBancoVo> data) {
            proyectos = data;
            fireTableDataChanged();
        }

        @Override
        public String getColumnName(int column) {
            switch (column){
                case 0: return "ID";
                case 1: return "CONSTRUCTORA";
                case 2: return "CIUDAD";
                case 3: return "CLASIFICACIÓN";
                case 4: return "ESTRATO";
                case 5: return "LIDER";
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return proyectos.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var proyecto = proyectos.get(rowIndex);
            switch (columnIndex){
                case 0: return proyecto.getId();
                case 1: return proyecto.getConstructora();
                case 2: return proyecto.getCiudad();
                case 3: return proyecto.getClasificacion();
                case 4: return proyecto.getEstrato();
                case 5: return proyecto.getLider();
            }
            return null;
        }
    }

    private class DeudasTableModel extends AbstractTableModel {

        List<DeudasPorProyectoVo> proyectos;

        public DeudasTableModel() {
            proyectos = new ArrayList<>();
        }

        public void setData(List<DeudasPorProyectoVo> data) {
            proyectos = data;
            fireTableDataChanged();
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column){
                case 0: return "ID";
                case 1: return "VALOR";
            }
            return super.getColumnName(column);
        }        

        @Override
        public int getRowCount() {
            return proyectos.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var proyecto = proyectos.get(rowIndex);
            switch (columnIndex){
                case 0: return proyecto.getId();
                case 1: return proyecto.getValor();
            }
            return null;
        }
    }

    private class ComprasLiderTableModel extends AbstractTableModel {

        List<ComprasDeLiderVo> proyectos;

        public ComprasLiderTableModel() {
            proyectos = new ArrayList<>();
        }

        private void setData(List<ComprasDeLiderVo> data) {
            proyectos = data;
            fireTableDataChanged();
        }

        @Override
        public String getColumnName(int column) {
            switch (column){
                case 0: return "LIDER";
                case 1: return "VALOR";
            }
            return super.getColumnName(column);
        }
        
        @Override
        public int getRowCount() {
            return proyectos.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var proyecto = proyectos.get(rowIndex);
            switch (columnIndex){
                case 0: return proyecto.getLider();
                case 1: return proyecto.getValor();
            }
            return null;
        }
    }
}
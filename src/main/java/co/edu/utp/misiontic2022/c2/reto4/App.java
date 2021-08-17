package co.edu.utp.misiontic2022.c2.reto4;

import co.edu.utp.misiontic2022.c2.reto4.view.ReportesGUI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try {
            var frm = new ReportesGUI();
            frm.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}

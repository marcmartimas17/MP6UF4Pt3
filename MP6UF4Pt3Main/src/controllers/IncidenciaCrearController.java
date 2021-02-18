package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import model.Model;
import model.classes.Incidencia;
import utilscontroller.Utils;
import views.IncidenciaCrear;
import views.IncidenciaPrincipal;

/**
 *
 * @author Marc Martí Mas
 */
public class IncidenciaCrearController {
    private static IncidenciaCrear view;
    private static Model model;
    
    public IncidenciaCrearController (IncidenciaCrear v, Model m) {
        view = v;
        model = m;
        iniciar ();
    }
    
    public void iniciar () {
        view.getTitolAfegir().setText("Afegir incidència");
        view.getAssumpteLabel().setText("Assumpte");
        view.getMissatgeLabel().setText("Missatge");
        view.getPrioritatLabel().setText("Prioritat");
        view.getBtnAfegir().setText("Afegir");
        view.getAssumpteField().setText("");
        view.getMissatgeField().setText("");
        view.getPrioritatBox().removeAllItems();
        view.getPrioritatBox().addItem("NORMAL");
        view.getPrioritatBox().addItem("URGENT");
        view.getPrioritatBox().addItem("BAIXA");
        view.getBtnTornar().setText("Tornar");
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Crear Incidència");
        
        view.getBtnTornar().addActionListener(e -> {
            view.dispose();
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);
        });
        
        view.getBtnAfegir().addActionListener(e -> {
            String assumpte = view.getAssumpteField().getText();
            String missatge = view.getMissatgeField().getText();
            String prioritat;
            if (view.getPrioritatBox().getSelectedIndex() == 0) {
                prioritat = "NORMAL";
            }
            else if (view.getPrioritatBox().getSelectedIndex() == 1) {
                prioritat = "URGENT";
            }
            else {
                prioritat = "BAIXA";
            }
            model.afegirIncidencia(assumpte, missatge, prioritat);
            view.dispose();
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);

        });
        
        view.setDefaultCloseOperation(IncidenciaCrear.DO_NOTHING_ON_CLOSE);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                model.tancarPrograma();
            }
        });
    }
}

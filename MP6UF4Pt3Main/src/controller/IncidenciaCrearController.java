package controller;

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
        view.getAssumpteLabel().setText("Assumpte");
        view.getMissatgeLabel().setText("Missatge");
        view.getPrioritatLabel().setText("Prioritat");
        view.getBtnAfegir().setText("Afegir");
        view.getAssumpteField().setText("");
        view.getMissatgeField().setText("");
        view.getPrioritatBox().removeAllItems();
        view.getPrioritatBox().addItem("Normal");
        view.getPrioritatBox().addItem("Urgent");
        view.getPrioritatBox().addItem("Baixa");
        view.getBtnTornar().setText("Tornar");
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Crear Incidència");
        
        view.getBtnTornar().addActionListener(e -> {
            view.dispose();
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);
        });
    }
}

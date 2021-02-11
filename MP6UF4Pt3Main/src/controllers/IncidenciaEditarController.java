package controllers;

import model.Model;
import model.classes.Incidencia;
import views.IncidenciaEditar;
import views.IncidenciaPrincipal;

/**
 *
 * @author Marc Martí Mas
 */
public class IncidenciaEditarController {
    private static IncidenciaEditar view;
    private static Model model;
    private Incidencia incidencia;
    
    public IncidenciaEditarController (IncidenciaEditar v, Model m, Incidencia i) {
        view = v;
        model = m;
        incidencia = i;
        iniciar ();
    }
    
    public void iniciar () {
        view.getTitolEditar().setText("Editar incidència");
        view.getAssumpteLabel().setText("Assumpte");
        view.getMissatgeLabel().setText("Missatge");
        view.getPrioritatLabel().setText("Prioritat");
        view.getBtnEditar().setText("Editar");
        view.getAssumpteField().setText("");
        view.getMissatgeField().setText("");
        view.getPrioritatBox().removeAllItems();
        view.getPrioritatBox().addItem("Normal");
        view.getPrioritatBox().addItem("Urgent");
        view.getPrioritatBox().addItem("Baixa");
        view.getBtnTornar().setText("Tornar");        
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Editar Incidència");
        
        view.getBtnTornar().addActionListener(e -> {
            view.dispose();
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);
        });
        
        view.getBtnEditar().addActionListener(e -> {
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
            //model.editarIncidencia(id, assumpte, missatge, prioritat);
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);

        });
    }
}

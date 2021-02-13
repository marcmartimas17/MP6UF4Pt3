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
        view.getPrioritatBox().removeAllItems();
        view.getPrioritatBox().addItem("NORMAL");
        view.getPrioritatBox().addItem("URGENT");
        view.getPrioritatBox().addItem("BAIXA");
        // Assignar valors als camps
        view.getAssumpteField().setText(incidencia.get2_assumpte());
        view.getMissatgeField().setText(incidencia.get3_missatge());
        view.getPrioritatBox().setSelectedItem(incidencia.get4_prioritat()); 
        
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
            model.editarIncidencia(incidencia.get1_id(), assumpte, missatge, prioritat);
            view.dispose();
            new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);

        });
    }
}

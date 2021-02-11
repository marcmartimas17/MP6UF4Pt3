package controller;

import beans.Connexio;
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
public class IncidenciaPrincipalController {
    
    private static IncidenciaPrincipal view;
    private static Model model;
    
    public IncidenciaPrincipalController (IncidenciaPrincipal v, Model m) {
        view = v;
        model = m;
        iniciar ();
    }
    
    public void iniciar () {
        view.getTitolIncidencies().setText("Incidències");
        view.getBtnCrear().setText("Crear");
        view.getBtnEditar().setText("Editar");
        view.getBtnEliminar().setText("Eliminar");
        
        Collection incidencies = model.selectTotesIncidencies();
        System.out.println(incidencies.size());
        Utils.loadTable(incidencies, view.getIncidenciesTable(), Incidencia.class, true, true);
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Incidències");
        
        view.getBtnCrear().addActionListener( e-> {
            view.dispose();
            new IncidenciaCrearController(new IncidenciaCrear(), model);
        });
    }

}

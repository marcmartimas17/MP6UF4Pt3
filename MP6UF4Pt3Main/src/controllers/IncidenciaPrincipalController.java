package controllers;

import beans.Connexio;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Model;
import model.classes.Incidencia;
import utilscontroller.Utils;
import views.IncidenciaCrear;
import views.IncidenciaEditar;
import views.IncidenciaPrincipal;

/**
 *
 * @author Marc Martí Mas
 */
public class IncidenciaPrincipalController {
    
    private static IncidenciaPrincipal view;
    private static Model model;
    private Incidencia incidenciaSelected = null;
    private TableColumn tc;
    private int filaActual = -1;
    
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
        
        //Collection incidencies = model.selectTotesIncidencies();
        view.getIncidenciesTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actualitzarTaula();
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Incidències");
        
        view.getIncidenciesTable().getSelectionModel().addListSelectionListener(e-> {
            filaActual = view.getIncidenciesTable().getSelectedRow();
        });
        
        view.getBtnCrear().addActionListener( e-> {
            view.dispose();
            new IncidenciaCrearController(new IncidenciaCrear(), model);
        });
        
        view.getBtnEditar().addActionListener( e -> {            
            agafarIncidencia();
            
            if (incidenciaSelected != null) {
                new IncidenciaEditarController(new IncidenciaEditar(), model, incidenciaSelected);
                view.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Has de seleccionar una incidència!");
            }
        });
        
        view.getBtnEliminar().addActionListener( e-> {
            agafarIncidencia();
            if (incidenciaSelected != null) {
                int input = JOptionPane.showConfirmDialog(null, "Estàs a punt d'esborrar una incidència. Vols continuar?", "Eliminar incidència",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == 0) {
                    model.eliminarIncidencia(incidenciaSelected.get1_id());
                    actualitzarTaula();
                }
                
            }
            else {
                JOptionPane.showMessageDialog(null, "Has de seleccionar una incidència!");
            }
        });
        
        view.setDefaultCloseOperation(IncidenciaPrincipal.DO_NOTHING_ON_CLOSE);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                model.tancarPrograma();
            }
        });
    }
    
    public void actualitzarTaula () {
        tc = Utils.loadTable(model.selectTotesIncidencies(), view.getIncidenciesTable(), Incidencia.class, true, true);
    }
    
    public void agafarIncidencia () {
        TableColumnModel tcm = view.getIncidenciesTable().getColumnModel();
        try {
            tcm.addColumn(tc);
            incidenciaSelected = (Incidencia)view.getIncidenciesTable().getValueAt(filaActual, (view.getIncidenciesTable().getColumnCount() - 1));
        }
        catch (java.lang.ArrayIndexOutOfBoundsException ex) {
        }
        finally {
            tcm.removeColumn(tc);
            filaActual = -1; // Tornar a deseleccionar la fila
        }
    }       

}

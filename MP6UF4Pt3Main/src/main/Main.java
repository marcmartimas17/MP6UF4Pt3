package main;

import beans.*;
import controller.IncidenciaPrincipalController;
import java.beans.PropertyVetoException;
import model.Model;
import views.*;

/**
 *
 * @author Marc Martí Mas
 */

public class Main {
    public static void main(String[] args) {
        
        Model model = new Model ();
        Connexio in = new Connexio ();
        
        model.connectarBaseDades();
        model.crearTaules();        
        
        new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);
        
    }
}

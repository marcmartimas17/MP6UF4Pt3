package main;

import controllers.IncidenciaPrincipalController;
import model.Model;
import views.*;

/**
 *
 * @author Marc Martí Mas
 */

public class Main {
    public static void main(String[] args) {
        
        Model model = new Model ();
        
        model.connectarBaseDades();
        model.crearTaules();        
        
        new IncidenciaPrincipalController(new IncidenciaPrincipal(), model);
        
    }
}

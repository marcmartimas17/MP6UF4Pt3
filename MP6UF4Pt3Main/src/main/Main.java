package main;

import beans.*;
import java.beans.PropertyVetoException;
/**
 *
 * @author Marc Martí Mas
 */

public class Main {
    public static void main(String[] args) {
        
        String crearTaules = "CREATE TABLE IF NOT EXISTS incidencia (" +
                    "id SERIAL PRIMARY KEY," + 
                    "assumpte VARCHAR(250)," +
                    "missatge TEXT," +
                    "prioritat VARCHAR(250)," +
                    "dataCreacio DATE" + 
                    ");";
        
        String select = "SELECT * FROM incidencies;";
        
        Incidencia in = new Incidencia ();
        
        try {
            in.setPropsDb ("config.properties");
        } catch (PropertyVetoException ex) {
            System.out.println("No s'ha pogut establir la connexió");
        }
        
        try {
            in.setUpdate(crearTaules);
            System.out.println("Taula creada");
        } catch (PropertyVetoException ex) {
            System.out.println("No s'ha pogut establir la connexió");
        }         
        
    }
}

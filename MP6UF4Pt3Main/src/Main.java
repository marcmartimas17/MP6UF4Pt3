import beans.*;
import java.beans.PropertyVetoException;
/**
 *
 * @author Marc Martí Mas
 */

public class Main {
    public static void main(String[] args) {
        Incidencia in = new Incidencia ();
        try {
            in.setPropsDb ("config.properties");
        } catch (PropertyVetoException ex) {
            System.out.println("No s'ha pogut establir la connexió");
        }
         
        
    }
}

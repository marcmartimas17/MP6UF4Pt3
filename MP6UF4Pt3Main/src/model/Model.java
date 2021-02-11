package model;

import beans.Connexio;
import java.beans.PropertyVetoException;
import java.util.Collection;
import java.sql.*;
import java.util.TreeSet;
import model.classes.Incidencia;

/**
 *
 * @author Marc Martí Mas
 */
public class Model {
    
    public static final String nomFitxerConfiguracio = "config.properties";
    public Connexio connexio = new Connexio ();
    private Collection <Incidencia> llistaIncidencies = new TreeSet <>();

    
    public void connectarBaseDades () {
        try {
            connexio.setPropsDb (nomFitxerConfiguracio);
        } catch (PropertyVetoException ex) {
            System.out.println("No s'ha pogut establir la connexió");
        }
    }
    
    public void crearTaules () {
        String crearTaules = 
                "CREATE TABLE IF NOT EXISTS incidencia (" +
                    "id SERIAL PRIMARY KEY," + 
                    "assumpte VARCHAR(250)," +
                    "missatge TEXT," +
                    "prioritat VARCHAR(250)," +
                    "dataCreacio DATE" + 
                ");";
        try {
            connexio.setUpdate(crearTaules);
        } catch (PropertyVetoException ex) {
            System.out.println("No s'ha pogut establir la connexió");
        }  
    }
    
    public Collection selectTotesIncidencies () {
        ResultSet rs = null;
        
        try {
            connexio.setSelect("SELECT * FROM incidencia;");
            rs = connexio.getResult();
            try {                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String assumpte = rs.getString("assumpte");
                    String missatge = rs.getString("missatge");
                    String prioritat = rs.getString("prioritat");
                    Date data = rs.getDate("dataCreacio");
                    Incidencia incidencia = new Incidencia (id, assumpte, missatge, prioritat, data);
                    llistaIncidencies.add(incidencia);
                }
            } catch (SQLException ex) {
                System.out.println("error");
            }            
        
        } catch (PropertyVetoException ex) {
            System.out.println("S'ha perdut la connexió amb la base de dades");
        }
        
        return llistaIncidencies;
        
    }
    

}

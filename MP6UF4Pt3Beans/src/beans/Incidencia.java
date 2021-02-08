package beans;

import java.beans.*;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc Martí Mas
 */
public class Incidencia implements Serializable, VetoableChangeListener {
    
    /* Suports */
    private transient final PropertyChangeSupport propertySupport;
    private transient final VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    
    private Connection conn = null;
    
    private String propsDb;
    public static final String PROP_PROPSDB = "propsDb";
    
    private String update;
    public static final String PROP_UPDATE = "update";
    
    private String select;

    public static final String PROP_SELECT = "select";

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) throws PropertyVetoException {
        String oldSelect = this.select;
        vetoableChangeSupport.fireVetoableChange(PROP_SELECT, oldSelect, select);
        this.select = select;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) throws PropertyVetoException {
        String oldUpdate = this.update;        
        vetoableChangeSupport.fireVetoableChange(PROP_UPDATE, oldUpdate, update);
        this.update = update;
    }


    public String getPropsDb() {
        return propsDb;
    }

    public void setPropsDb(String propsDb)throws PropertyVetoException {
        String oldPropsDb = this.propsDb;
        vetoableChangeSupport.fireVetoableChange(PROP_PROPSDB, oldPropsDb, propsDb);
        this.propsDb = propsDb;
    }
    
    /* Constructors */  
    
    public Incidencia() {
        propertySupport = new PropertyChangeSupport(this);
        this.addVetoableChangeListener(this);
    }
    
    /* Mètodes dels suports */
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public void vetoableChange(PropertyChangeEvent ev) throws PropertyVetoException {
        ResultSet rs = null;
        
        switch (ev.getPropertyName()) {
            
            case Incidencia.PROP_PROPSDB: 
                Properties properties = new Properties ();
                try {
                    properties.load(new FileInputStream((String) ev.getNewValue()));
                    String url = properties.getProperty("url");
                    String usuari = properties.getProperty("usuari");
                    String contrasenya = properties.getProperty("contrasenya");
                    conn = DriverManager.getConnection(url, usuari, contrasenya);
                    System.out.println("Conectat en exit");
                    
                } catch (Exception ex) {
                    throw new PropertyVetoException("", ev);
                }                             
                
                break;
                
            case Incidencia.PROP_UPDATE:
                try {
                    System.out.println((String)ev.getNewValue());
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate((String) ev.getNewValue());
                } catch (Exception ex) {
                    throw new PropertyVetoException("", ev);                  
                }                
            
                break;
                
            case Incidencia.PROP_SELECT:
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeQuery((String) ev.getNewValue());

                } catch (Exception ex) {
                    throw new PropertyVetoException("", ev);                  
                }               

                break;

        }
    } 
}

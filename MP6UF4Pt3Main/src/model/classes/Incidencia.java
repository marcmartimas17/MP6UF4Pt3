package model.classes;

import java.util.Date;

/**
 *
 * @author Marc Martí Mas
 */
public class Incidencia implements Comparable <Incidencia>{
    /* Atributs */
    private int _1_id;
    private String _2_assumpte;
    private String _3_missatge;
    private String _4_prioritat;
    private Date _5_dataCreacio;
    
    
    /* Constructors */
    public Incidencia () {
        //
    }
    
    public Incidencia (int id, String assumpte, String missatge, String prioritat) {
        this._1_id = id;
        this._2_assumpte = assumpte;
        this._3_missatge = missatge;
        this._4_prioritat = prioritat;
        this._5_dataCreacio = new Date(System.currentTimeMillis()); // Agafar el temps actual
    }
    
    public Incidencia (int id, String assumpte, String missatge, String prioritat, Date data) {
        this._1_id = id;
        this._2_assumpte = assumpte;
        this._3_missatge = missatge;
        this._4_prioritat = prioritat;
        this._5_dataCreacio = data;
    }
    
    /* Getters i setters */
    public int get1_id() {
        return _1_id;
    }

    public void set1_id(int _1_id) {
        this._1_id = _1_id;
    }

    public String get2_assumpte() {
        return _2_assumpte;
    }

    public void set2_assumpte(String _2_assumpte) {
        this._2_assumpte = _2_assumpte;
    }

    public String get3_missatge() {
        return _3_missatge;
    }

    public void set3_missatge(String _3_missatge) {
        this._3_missatge = _3_missatge;
    }

    public String get4_prioritat() {
        return _4_prioritat;
    }

    public void set4_prioritat(String _4_prioritat) {
        this._4_prioritat = _4_prioritat;
    }

    public Date get5_dataCreacio() {
        return _5_dataCreacio;
    }

    public void set5_dataCreacio(Date _5_dataCreacio) {
        this._5_dataCreacio = _5_dataCreacio;
    }    

    @Override
    public int compareTo(Incidencia inc1) {
        return this._1_id - inc1.get1_id();
    }
    
    public String toString () {
        
        return "Incidencia { id: " + _1_id + ", assumpte: " +_2_assumpte + ", missatge: " + _3_missatge + ", prioritat: " + _4_prioritat + "}";
        
    }
    
}

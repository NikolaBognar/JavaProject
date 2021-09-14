/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Nikola
 */
public class Sediste {
    private int id;
    private String vrsta;
    private String rezervisano;
    private String oznaka;
    private int salaId;

    public Sediste() {
    }

    public Sediste(int id, String vrsta, String rezervisano, String oznaka, int salaId) {
        this.id = id;
        this.vrsta = vrsta;
        this.rezervisano = rezervisano;
        this.oznaka = oznaka;
        this.salaId = salaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getRezervisano() {
        return rezervisano;
    }

    public void setRezervisano(String rezervisano) {
        this.rezervisano = rezervisano;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }
    
    
}

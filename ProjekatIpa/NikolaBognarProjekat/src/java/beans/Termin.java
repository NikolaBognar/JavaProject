/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nikola
 */
public class Termin {
    private int id;
    private Date datum;
    private Time vreme;
    private double cena;
    private String nazivFilma;
    private int filmId;
    private int repertoarId;
    private String tehnologija;
    private int idSala;
    private int idBioskop;
    
    public Termin() {
    }

    public Termin(int id, Date datum, Time vreme, double cena, String nazivFilma, int filmId, int repertoarId, String tehnologija, int idSala, int idBioskop) {
        this.id = id;
        this.datum = datum;
        this.vreme = vreme;
        this.cena = cena;
        this.nazivFilma = nazivFilma;
        this.filmId = filmId;
        this.repertoarId = repertoarId;
        this.tehnologija = tehnologija;
        this.idSala = idSala;
        this.idBioskop = idBioskop;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getRepertoarId() {
        return repertoarId;
    }

    public void setRepertoarId(int repertoarId) {
        this.repertoarId = repertoarId;
    }

    public String getTehnologija() {
        return tehnologija;
    }

    public void setTehnologija(String tehnologija) {
        this.tehnologija = tehnologija;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdBioskop() {
        return idBioskop;
    }

    public void setIdBioskop(int idBioskop) {
        this.idBioskop = idBioskop;
    }
    
    
    
}

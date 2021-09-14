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
public class RezervacijaBean {
    private String username;
    private String imePrezime;
    private String nazivFilma;
    private int sala;
    private String datum;
    private String vreme;
    private String sedista;
    private int idProj;
    private int idKorisnik;
    private int idBioskop;
    
    public RezervacijaBean() {
    }

    public RezervacijaBean( String username, String imePrezime, String nazivFilma, int sala, String datum, String vreme, String sedista, int idProj, int idKorisnik,
            int idBioskop) {
        this.username = username;
        this.imePrezime = imePrezime;
        this.nazivFilma = nazivFilma;
        this.sala = sala;
        this.datum = datum;
        this.vreme = vreme;
        this.sedista = sedista;
        this.idProj = idProj;
        this.idKorisnik = idKorisnik;
        this.idBioskop = idBioskop;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getSedista() {
        return sedista;
    }

    public void setSedista(String sedista) {
        this.sedista = sedista;
    }

    public int getIdProj() {
        return idProj;
    }

    public void setIdProj(int idProj) {
        this.idProj = idProj;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public int getIdBioskop() {
        return idBioskop;
    }

    public void setIdBioskop(int idBioskop) {
        this.idBioskop = idBioskop;
    }
    
    
    
}

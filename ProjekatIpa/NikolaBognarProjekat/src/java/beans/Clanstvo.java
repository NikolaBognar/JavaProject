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
public class Clanstvo {
    private int id;
    private String naziv;
    private String pogodnosti;
    private double clanarina;
    private String slika;

    public Clanstvo() {
    }

    public Clanstvo(int id, String naziv, String pogodnosti, double clanarina, String slika) {
        this.id = id;
        this.naziv = naziv;
        this.pogodnosti = pogodnosti;
        this.clanarina = clanarina;
        this.slika = slika;
    }
    
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPogodnosti() {
        return pogodnosti;
    }

    public void setPogodnosti(String pogodnosti) {
        this.pogodnosti = pogodnosti;
    }

    public double getClanarina() {
        return clanarina;
    }

    public void setClanarina(double clanarina) {
        this.clanarina = clanarina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    
    
}

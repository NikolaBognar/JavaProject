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
public class Film {
    private int idFilm;
    private String naziv;
    private String trajanje;
    private String opis;
    private String trailer;
    private String slika;

    public Film() {
    }

    public Film(int idFilm, String naziv, String trajanje, String opis, String trailer, String slika) {
        this.idFilm = idFilm;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.opis = opis;
        this.trailer = trailer;
        this.slika = slika;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    
    
}

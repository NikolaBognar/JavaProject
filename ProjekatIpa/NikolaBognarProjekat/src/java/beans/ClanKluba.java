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
public class ClanKluba {
    private int idclanstvo_korisnik;
    private String naziv;
    private String imeKorisnika;
    private String datum;
    private String slika;
    private int id_clanstvo;
    private int id_korisnik;

    public ClanKluba() {
    }

    public ClanKluba(String naziv, String imeKorisnika, String datum, String slika) {
        this.naziv = naziv;
        this.imeKorisnika = imeKorisnika;
        this.datum = datum;
        this.slika = slika;
    }

    public int getIdclanstvo_korisnik() {
        return idclanstvo_korisnik;
    }

    public void setIdclanstvo_korisnik(int idclanstvo_korisnik) {
        this.idclanstvo_korisnik = idclanstvo_korisnik;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getImeKorisnika() {
        return imeKorisnika;
    }

    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public int getId_clanstvo() {
        return id_clanstvo;
    }

    public void setId_clanstvo(int id_clanstvo) {
        this.id_clanstvo = id_clanstvo;
    }

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }
    
    
}

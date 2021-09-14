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
public class Bioskop {
    private int iDbioskop;
    private String naziv;
    private String grad;
    private String slika;
    private String tehnologije;
    private String radnoVreme;
    private String kontakt;

    
    public Bioskop() {
    }

    public Bioskop(int iDbioskop, String naziv, String grad, String slika, String tehnologije, String radnoVreme, String kontakt) {
        this.iDbioskop = iDbioskop;
        this.naziv = naziv;
        this.grad = grad;
        this.slika = slika;
        this.tehnologije = tehnologije;
        this.radnoVreme = radnoVreme;
        this.kontakt = kontakt;
    }

    public int getiDbioskop() {
        return iDbioskop;
    }

    public void setiDbioskop(int iDbioskop) {
        this.iDbioskop = iDbioskop;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getTehnologije() {
        return tehnologije;
    }

    public void setTehnologije(String tehnologije) {
        this.tehnologije = tehnologije;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
    
    
}

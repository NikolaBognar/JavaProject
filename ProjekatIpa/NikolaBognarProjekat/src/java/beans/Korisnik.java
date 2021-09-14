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
public class Korisnik {
    private int id;
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String datum;
    private String pol;
    private String tip;
    private String email;
    private int poeni;
    
    public Korisnik() {
    }

    public Korisnik(String username, String password, String ime, String prezime, String datum, String pol, String tip, String email, int poeni) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.datum = datum;
        this.pol = pol;
        this.tip = tip;
        this.email = email;
        this.poeni = poeni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }
    
    
}

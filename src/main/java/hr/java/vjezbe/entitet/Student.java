package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Student {
    private String ime;
    private String prezime;
    private String jbmag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jbmag, LocalDate datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jbmag = jbmag;
        this.datumRodjenja = datumRodjenja;
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

    public String getJbmag() {
        return jbmag;
    }

    public void setJbmag(String jbmag) {
        this.jbmag = jbmag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}

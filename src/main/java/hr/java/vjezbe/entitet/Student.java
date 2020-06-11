package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Student extends Osoba{
    private String jbmag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jbmag, LocalDate datumRodjenja) {
        super(ime, prezime);
        this.jbmag = jbmag;
        this.datumRodjenja = datumRodjenja;
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

package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {
    public static final int BROJ_PROFESORA = 2;
    public static final int BROJ_PREDMETA = 3;
    public static final int BROJ_STUDENATA = 2;
    public static final String FORMAT_DATUMA = "dd.MM.yyyy.";
    public static final String FORMAT_DATUMA_I_VREMENA = "dd.MM.yyyy. HH:mm";
    public static final int BROJ_ROKOVA = 2;
    public static final int OCJENA_IZVRSTAN = 5;

    public static void main(String[] args) {

        Profesor[] profesori = new Profesor[BROJ_PROFESORA];
        Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
        Student[] studenti = new Student[BROJ_STUDENATA];
        Ispit[] ispiti = new Ispit[BROJ_ROKOVA];

        Scanner scan = new Scanner(System.in);

        System.out.print("Unesite broj ustanova: ");
        int brojUstanova = scan.nextInt();
        scan.nextLine();

        ObrazovnaUstanova[] obrazovnaUstanova = new ObrazovnaUstanova[brojUstanova];

        for (int k = 0; k < brojUstanova; k++) {
            System.out.println("Unesite podatke za " + (k + 1) + ". obrazovnu ustanovu: ");

            for (int i = 0; i < BROJ_PROFESORA; i++) {
                profesori[i] = unosProfesora(scan, i);
            }
            for (int i = 0; i < BROJ_PREDMETA; i++) {
                predmeti[i] = unosPredmeta(scan, i, profesori);
            }
            for (int i = 0; i < BROJ_STUDENATA; i++) {
                studenti[i] = unosStudenta(scan, i);
            }
            for (int i = 0; i < BROJ_ROKOVA; i++) {
                ispiti[i] = unosIspita(studenti, predmeti, scan, i);
            }

            for (Ispit ispit : ispiti) {
                if (ispit.getOcjena() == OCJENA_IZVRSTAN) {
                    System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime() + " je ostvario ocjenu 'izvrstan' na predmetu '" + ispit.getPredmet().getNaziv() + "'");
                }
            }

            System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");
            int odabirUstanove = scan.nextInt();
            scan.nextLine();

            switch (odabirUstanove) {
                case 1:
                    VeleucilisteJave novoVeleuciliste = new VeleucilisteJave(predmeti, profesori, studenti, ispiti);
                    obrazovnaUstanova[k] = novoVeleuciliste;
                    unosVeleucilistaJave(scan, novoVeleuciliste);
                    break;
                case 2:
                    FakultetRacunarstva noviFakultet = new FakultetRacunarstva(predmeti, profesori, studenti, ispiti);
                    obrazovnaUstanova[k] = noviFakultet;
                    unosFakultetRacunarsta(scan, noviFakultet);
                    break;
            }
        }
        scan.close();
    }


    private static void unosFakultetRacunarsta(Scanner scan, FakultetRacunarstva fakultet) {
        System.out.print("Unesite naziv obrazovne ustanove: ");
        String nazivUstanove = scan.nextLine();
        fakultet.setNazivObrazovneUstanove(nazivUstanove);

        Student[] studenti = fakultet.getStudent();

        for (Student student : studenti) {
            int ocjenaZavrsnog;
            do {
                System.out.print("Unesite ocjenu završnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                ocjenaZavrsnog = scan.nextInt();
                scan.nextLine();
            } while (ocjenaZavrsnog < 1 || ocjenaZavrsnog > 5);

            int ocjenaObrane;
            do {
                System.out.print("Unesite ocjenu obrane završnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                ocjenaObrane = scan.nextInt();
                scan.nextLine();
            } while (ocjenaObrane < 1 || ocjenaObrane > 5);

            System.out.println("Konačna ocjena studija studenta " + student.getIme() + " " + student.getPrezime() + " je " + fakultet.izracunajKonacnuOcjenuStudijaZaStudenta(fakultet.getIspit(), ocjenaZavrsnog, ocjenaObrane).round(new MathContext(1)));
        }

        Student najboljiStudent = fakultet
                .odrediNajuspjesnijegStudentaNaGodini(2020);
        System.out.println("Najbolji student 2018. godine je " + najboljiStudent.getIme() + " " + najboljiStudent.getPrezime() + " JMBAG: " + najboljiStudent.getJbmag());

        Student studentZaRektorovu = fakultet.odrediStudentaZaRektorovuNagradu();
        System.out.println("Student koji je osvojio rektorovu nagradu je " + studentZaRektorovu.getIme() + " " + studentZaRektorovu.getPrezime() + " JMBAG: " + studentZaRektorovu.getJbmag());
    }

    private static void unosVeleucilistaJave(Scanner scan, VeleucilisteJave veleuciliste) {
        System.out.print("Unesite naziv obrazovne ustanove: ");
        String nazivObrazovneUstanove = scan.nextLine();
        veleuciliste.setNazivObrazovneUstanove(nazivObrazovneUstanove);

        Student[] studenti = veleuciliste.getStudent();

        for (Student student : studenti) {
            int ocjenaZavrsnog;
            do {
                System.out.print("Unesite ocjenu zavrsnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                ocjenaZavrsnog = scan.nextInt();
            } while (ocjenaZavrsnog < 1 || ocjenaZavrsnog > 5);
            scan.nextLine();

            int ocjenaObrane;
            do {
                System.out.print("Unesite ocjenu obrane zavrsnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                ocjenaObrane = scan.nextInt();
            } while (ocjenaObrane < 1 || ocjenaObrane > 5);

            System.out.print("Konacna ocjena studija studenta " + student.getIme() + " " + student.getPrezime() + " je " + veleuciliste.izracunajKonacnuOcjenuStudijaZaStudenta(veleuciliste.getIspit(), ocjenaZavrsnog, ocjenaObrane).round(new MathContext(1)));

            scan.nextLine();
        }
        Student najboljiStudent = veleuciliste.odrediNajuspjesnijegStudentaNaGodini(2020);
        System.out.println("Najbolji student 2020. godine je " + najboljiStudent.getIme() + " " + najboljiStudent.getPrezime() + " JMBAG: " + najboljiStudent.getJbmag());
    }

    private static Ispit unosIspita(Student[] studenti, Predmet[] predmeti, Scanner scan, int i) {
        System.out.println("Unesite " + (1 + i) + ". ispitni rok: ");

        int odabirPredmeta;
        do {
            System.out.println("Odaberite predmet: ");
            for (int j = 0; j < BROJ_PREDMETA; j++) {
                System.out.println((j + 1) + ". " + predmeti[j].getNaziv());
            }
            System.out.print("Odabir -> ");
            odabirPredmeta = scan.nextInt();
        } while (odabirPredmeta < 1 || odabirPredmeta > BROJ_PREDMETA);
        scan.nextLine();

        Predmet odabraniPredmet = predmeti[odabirPredmeta - 1];

        int odabirStudenta;
        do {
            System.out.println("Odaberite studenta: ");
            for (int j = 0; j < BROJ_STUDENATA; j++) {
                System.out.println((j + 1) + ". " + studenti[j].getIme() + " " + studenti[j].getPrezime());
            }
            System.out.print("Odabir -> ");
            odabirStudenta = scan.nextInt();
        } while (odabirStudenta < 1 || odabirStudenta > BROJ_STUDENATA);
        scan.nextLine();

        Student odabraniStudent = studenti[odabirStudenta - 1];

        System.out.print("Unesite ocjenu (1-5): ");
        int ocjena = scan.nextInt();
        scan.nextLine();
        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy. HH:mm): ");
        String datumIVrijeme = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA_I_VREMENA);
        LocalDateTime datumIVrijemeIspita = LocalDateTime.parse(datumIVrijeme, formatter);

        return new Ispit(odabraniPredmet, odabraniStudent, ocjena, datumIVrijemeIspita);
    }

    private static Student unosStudenta(Scanner scan, int i) {
        System.out.println("Unesite " + (1 + i) + ". studenta: ");
        System.out.print("Unesite ime studenta: ");
        String imeStudenta = scan.nextLine();
        System.out.print("Unesite prezime studenta: ");
        String prezimeStudenta = scan.nextLine();
        System.out.print("Unesite datum rodjenja studenta " + imeStudenta + " " + prezimeStudenta + " u formatu (dd.MM.yyyy.): ");
        String datumRodjenjaStudenta = scan.nextLine();
        System.out.print("Unesite JMBAG studenta " + imeStudenta + " " + prezimeStudenta + ": ");
        String jmbagStudenta = scan.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA);
        LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaStudenta, formatter);

        return new Student(imeStudenta, prezimeStudenta, jmbagStudenta, datumRodjenja);
    }

    private static Predmet unosPredmeta(Scanner scan, int i, Profesor[] profesor) {
        System.out.println("Unesite " + (i + 1) + ". predmet: ");
        System.out.print("Unesite sifru predmeta: ");
        String sifraPredmeta = scan.nextLine();
        System.out.print("Unesite naziv predmeta: ");
        String nazivPredmeta = scan.nextLine();
        System.out.print("Unesite broj ECTS bodova za predmet " + "'" + nazivPredmeta + "': ");
        Integer brojEctsa = scan.nextInt();
        scan.nextLine();

        int odabirProfesora;
        do {
            System.out.println("Odaberite profesora: ");
            for (int j = 0; j < BROJ_PROFESORA; j++) {
                System.out.println((j + 1) + ". " + profesor[j].getIme() + " " + profesor[j].getPrezime());
            }
            System.out.print("Odabir -> ");
            odabirProfesora = scan.nextInt();
        } while (odabirProfesora < 1 || odabirProfesora > BROJ_PROFESORA);
        scan.nextLine();

        Profesor odabranProfesor = profesor[odabirProfesora - 1];

        System.out.print("Unesite broj studenata na predmetu: " + "'" + nazivPredmeta + "': ");
        Integer studentiNaPredmetu = scan.nextInt();
        scan.nextLine();

        return new Predmet(sifraPredmeta, nazivPredmeta, brojEctsa, odabranProfesor, studentiNaPredmetu);
    }

    private static Profesor unosProfesora(Scanner scan, int i) {
        System.out.println("Unesite " + (i + 1) + ". profesora: ");
        System.out.print("Unesite sifru profesora: ");
        String sifraProfesora = scan.nextLine();
        System.out.print("Unesite ime profesora: ");
        String imeProfesora = scan.nextLine();
        System.out.print("Unesite prezime profesora: ");
        String prezimeProfesora = scan.nextLine();
        System.out.print("Unesite titulu profesora: ");
        String tituluProfesora = scan.nextLine();
        return new Profesor(imeProfesora, prezimeProfesora, sifraProfesora, tituluProfesora);
    }

}

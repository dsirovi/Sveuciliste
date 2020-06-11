package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    public VeleucilisteJave(Predmet[] predmet, Profesor[] profesor, Student[] student, Ispit[] ispit) {
        super(predmet, profesor, student, ispit);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjena, int ocjenaObraneZavrsnogRada) {
        double konacnaOcjena = (2 * odrediProsjekOcjenaNaIspitima(ispiti).doubleValue() + ocjena + ocjenaObraneZavrsnogRada) / 4;
        BigDecimal rezultat = BigDecimal.valueOf(konacnaOcjena);
        return rezultat;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija) {

        Student[] studentiNaGodini = new Student[2];
        int[] brojIzvrsnihIspita = new int[Glavna.BROJ_ROKOVA];

        int index = 0;
        for (Student student : studentiNaGodini) {
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(this.getIspit(), student);

            for (Ispit ispit : ispitiStudenta) {
                if (ispit.getOcjena().equals(Glavna.OCJENA_IZVRSTAN)) {
                    brojIzvrsnihIspita[index]++;
                }
                index++;
            }
        }

        int maxBrojIspita = brojIzvrsnihIspita[0];
        int indexIspita = 0;
        for (int i = 0; i < brojIzvrsnihIspita.length; i++) {
            int trenutniBroj = brojIzvrsnihIspita[i];
            if (trenutniBroj > maxBrojIspita) {
                maxBrojIspita = trenutniBroj;
                indexIspita = i;
            }
        }
        return studentiNaGodini[indexIspita];
    }
}

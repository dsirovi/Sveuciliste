package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    public FakultetRacunarstva(Predmet[] predmet, Profesor[] profesor, Student[] student, Ispit[] ispit) {
        super(predmet, profesor, student, ispit);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija) {
        Student[] studentiNaGodini = new Student[2];
        int[] brojIzvrsnihIspita = new int[Glavna.BROJ_ROKOVA];

        int index = 0;

        for (Student student : studentiNaGodini) {
            Ispit[] ispitiStudenata = filtrirajIspitePoStudentu(this.getIspit(), student);

            for (Ispit ispit : ispitiStudenata) {
                if (ispit.getOcjena().equals(5)) {
                    brojIzvrsnihIspita[index]++;
                }
            }
            index++;
        }
        Integer maxBrojIspita = brojIzvrsnihIspita[0];
        int indexStudenta = 0;
        for (int i = 0; i < brojIzvrsnihIspita.length; i++) {
            Integer trenutniBroj = brojIzvrsnihIspita[i];
            if (trenutniBroj > maxBrojIspita) {
                maxBrojIspita = trenutniBroj;
                indexStudenta = i;
            }
        }
        return studentiNaGodini[indexStudenta];
    }


    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjena, int ocjenaObraneDiplomskogRada) {
        double konacnaOcjena = (3 * odrediProsjekOcjenaNaIspitima(ispiti).doubleValue() + ocjena + ocjenaObraneDiplomskogRada) / 5;
        BigDecimal rezultat = BigDecimal.valueOf(konacnaOcjena);
        return rezultat;
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        Student[] predlozeniStudent = this.getStudent();
        BigDecimal[] prosjeciStudenata = new BigDecimal[predlozeniStudent.length];

        int index = 0;

        for (Student student : predlozeniStudent){
            Ispit[] ispitiStudenata = filtrirajIspitePoStudentu(this.getIspit(), student);
            BigDecimal prosjekOcjeneStudenata = odrediProsjekOcjenaNaIspitima(ispitiStudenata);
            prosjeciStudenata[index] = prosjekOcjeneStudenata;
            index++;
        }

        double najveciProsjek = prosjeciStudenata[0].doubleValue();
        int indexStudenataSaNajvecimProsjekom = 0;
        for (int i = 0; i < prosjeciStudenata.length; i++) {
            double prosjek = prosjeciStudenata[i].doubleValue();
            if (prosjek > najveciProsjek){
                najveciProsjek = prosjek;
                indexStudenataSaNajvecimProsjekom = i;
            }else if (prosjek == najveciProsjek){
                Student prvi = predlozeniStudent[i];
                Student drugi = predlozeniStudent[indexStudenataSaNajvecimProsjekom];
                if (prvi.getDatumRodjenja().isBefore(drugi.getDatumRodjenja())){
                    najveciProsjek = prosjeciStudenata[i].doubleValue();
                    indexStudenataSaNajvecimProsjekom = i;
                }
            }
        }
        return predlozeniStudent[indexStudenataSaNajvecimProsjekom];
    }
}

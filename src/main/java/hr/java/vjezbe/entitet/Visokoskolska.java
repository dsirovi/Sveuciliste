package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjena, int ocjenaObraneZavrsnogRada);

    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) {
        Ispit[] polozeniIspiti = filtrirajPolozeneIspite(ispiti);

        double zbroj = 0;
        for (Ispit ispit : polozeniIspiti) {
            zbroj += ispit.getOcjena();
        }
        return BigDecimal.valueOf(zbroj / polozeniIspiti.length);
    }

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {
        Ispit[] odabrani = new Ispit[0];

        for (Ispit ispit : ispiti) {
            if (ispit.getOcjena() > 1 && ispit.getOcjena() < 6) {
                odabrani = Arrays.copyOf(odabrani, odabrani.length + 1);
                odabrani[odabrani.length - 1] = ispit;
            }
        }
        return odabrani;

    }

    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        Ispit[] odabrani = new Ispit[0];

        for (Ispit ispit : ispiti) {
            if (ispit.getStudent().getJbmag().equals(student.getJbmag())) {
                odabrani = Arrays.copyOf(odabrani, odabrani.length + 1);
                odabrani[odabrani.length - 1] = ispit;
            }
        }
        return odabrani;
    }
}

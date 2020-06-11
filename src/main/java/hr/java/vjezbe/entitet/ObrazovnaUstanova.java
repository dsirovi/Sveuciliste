package hr.java.vjezbe.entitet;

public abstract class ObrazovnaUstanova {
    private String nazivObrazovneUstanove;
    private Predmet[] predmet;
    private Profesor[] profesor;
    private Student[] student;
    private Ispit[] ispit;

    public ObrazovnaUstanova(Predmet[] predmet, Profesor[] profesor, Student[] student, Ispit[] ispit) {
        this.predmet = predmet;
        this.profesor = profesor;
        this.student = student;
        this.ispit = ispit;
    }

    public String getNazivObrazovneUstanove() {
        return nazivObrazovneUstanove;
    }

    public void setNazivObrazovneUstanove(String nazivObrazovneUstanove) {
        this.nazivObrazovneUstanove = nazivObrazovneUstanove;
    }

    public Predmet[] getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet[] predmet) {
        this.predmet = predmet;
    }

    public Profesor[] getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor[] profesor) {
        this.profesor = profesor;
    }

    public Student[] getStudent() {
        return student;
    }

    public void setStudent(Student[] student) {
        this.student = student;
    }

    public Ispit[] getIspit() {
        return ispit;
    }

    public void setIspit(Ispit[] ispit) {
        this.ispit = ispit;
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija);
}

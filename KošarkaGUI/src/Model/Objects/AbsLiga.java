package Model.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbsLiga {
    protected String naziv;
    protected List<Mec> mecevi;
    protected List<Tim> playOffTimovi;
    protected Tim sampion;
    protected int sizeMax;
    protected int size;
    public AbsLiga(String naziv) {
        this.naziv = naziv;
        mecevi=new ArrayList<>();
    }

    public abstract void dodajTim(Tim tim);
    public abstract void napraviMeceve();
    public abstract List<Tim> getTimovi();
    public abstract void napraviPlayOff();
    public abstract void restartujLigu();
    public abstract void SortByName();
    public abstract void ispisiTabelu();
    public abstract void odigrajSpecMec(Scanner sc);

    protected void dodajMec(Tim t1, Tim t2) {
        Mec novi = new Mec(t1, t2);
        mecevi.add(novi);
    }

    public String getNaziv() {
        return naziv;
    }

    public List<Mec> getMecevi() {
        return mecevi;
    }

    public List<Tim> getPlayOffTimovi() {
        return playOffTimovi;
    }

    public void odigrajMeceve() {
        for (Mec mec : mecevi) {
            mec.odigrajMec();
        }
        mecevi.clear();
    }

    public void ispisiMeceve() {
        int i = 1;
        for (Mec mec : mecevi) {
            System.out.print(i++ + ". ");
            mec.ispisiMec();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null ) return  false;
        if(!(obj instanceof AbsLiga)) return false;
        return naziv.equals(((AbsLiga)obj).getNaziv());
    }

    public boolean isFull(){
        return !(size<sizeMax);
    }
}

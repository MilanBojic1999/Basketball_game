public class Tim implements Comparable<Tim> {
    private String naziv;
    private int napad;
    private int odbrana;
    private int datiKosevi;
    private int primljeniKosevi;
    private int bodovi;

    public Tim(String naziv, int napad, int odbrana) {
        this.naziv = naziv;
        this.napad = napad;
        this.odbrana = odbrana;
        this.datiKosevi = 0;
        this.primljeniKosevi = 0;
        this.bodovi = 0;
    }

    public int getNapad() {

        return this.napad;
    }

    public int getOdbrana() {

        return this.odbrana;
    }

    public int getBodovi(){
        return this.bodovi;
    }

    public int getRazlikaKoseva(){
        return this.datiKosevi-this.primljeniKosevi;
    }

    public void dodajRezultat(int bodovi, int dKos, int pKos) {
        this.bodovi += bodovi;
        this.datiKosevi += dKos;
        this.primljeniKosevi += pKos;
    }

    @Override
    public String toString() {
        return this.naziv + " " + this.napad + ":" + this.odbrana;
    }

    public String dajRezultate() {

        return this.naziv + "\t| " + this.datiKosevi + ":" + this.primljeniKosevi + " - " + this.bodovi;
    }

    @Override
    public int compareTo(Tim tim) {
        if(this.bodovi>tim.getBodovi()) return -1;
        else if(this.bodovi<tim.getBodovi()) return 1;
        else {
            if((this.getRazlikaKoseva())>tim.getRazlikaKoseva()) return -1;
            else return 1;
        }
    }
}

/*
 * Prestavljanje Kluba u određenoj ligi
 * Tim ne postoji bez kluba i van lige
 * pored kluba, Tim određuju dati i primljeni koševi, kao i osvojeni bodovi
 */

public class Tim implements Comparable<Tim> {
    private Klub klub;
    private int datiKosevi;
    private int primljeniKosevi;
    private int bodovi;

    public Tim(Klub klub) {
        this.klub=klub;
        this.datiKosevi = 0;
        this.primljeniKosevi = 0;
        this.bodovi = 0;
    }
    public Klub getKlub() {
		return klub;
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
    	// TODO Auto-generated method stub
    	return klub.toString();
    }

    public String dajRezultate() {

        return String.format("%15s", this.klub.getNaziv()) + "\t| " + this.datiKosevi + ":" + this.primljeniKosevi + " - " + this.bodovi;
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
    
    @Override
    public boolean equals(Object obj) {
    	if(obj==null) return false;
    	if(!(obj instanceof Tim)) return false;
    	return klub.equals(((Tim)obj).getKlub());
    }
    public void restart() {
    	this.datiKosevi = 0;
        this.primljeniKosevi = 0;
        this.bodovi = 0;
    }
}

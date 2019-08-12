/*
 * Jedan klub može da bude član više liga, zbog čega je razdvojen od Tima
 * Odluku ga naziv,atribut za napad i atribut za odbranu
 */
public class Klub {
	private String naziv;
    private int napad;
    private int odbrana;
    
	public Klub(String naziv, int napad, int odbrana) {
		super();
		this.naziv = naziv;
		this.napad = napad;
		this.odbrana = odbrana;
	}
    
	
	public String getNaziv() {
		return naziv;
	}


	public int getNapad() {
		return napad;
	}


	public int getOdbrana() {
		return odbrana;
	}


	@Override
    public String toString() {
        return this.naziv + " - " + this.napad + " : " + this.odbrana;
    }
	
	@Override
    public boolean equals(Object obj) {
    	if(obj==null) return false;
    	if(!(obj instanceof Klub)) return false;
    	return this.naziv.equalsIgnoreCase(((Klub)obj).getNaziv());
    }
}

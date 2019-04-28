import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Liga {
    private String naziv;
    private ArrayList<Tim> tabela;
    private List<Mec> mecevi;
    private int velLige;

    public Liga(String naziv, int velicina) {
        this.tabela = new ArrayList<Tim>(velicina);
        this.mecevi = new ArrayList<Mec>();
        this.naziv = naziv;
        this.velLige = velicina;
    }

    public void dodajTim(Tim tim) {
    	if(tabela.contains(tim)) return;
        if (tabela.size() < velLige) {
            tabela.add(tim);
        } else {
            System.out.println("Popunjena je kvota timova");
        }
    }
    
    
    public List<Tim> getTabela() {
		return tabela;
	}

	private void dodajMec(Tim t1, Tim t2) {
        Mec novi = new Mec(t1, t2);
        mecevi.add(novi);
    }

    public void napraviMeceve() {
        for (int i = 0; i < tabela.size() - 1; i++) {
            for (int j = i + 1; j < tabela.size(); j++) {
                this.dodajMec(tabela.get(i), tabela.get(j));
            }
        }
    }

    public void ispisiMeceve() {
        int i = 1;
        for (Mec mec : mecevi) {
            System.out.print(i++ + ". ");
            mec.ispisiMec();
        }
    }

    public void odigrajMeceve() {
        for (Mec mec : mecevi) {
            mec.odigrajMec();
        }
        Collections.sort(tabela);
        mecevi.clear();
    }

    public void ispisiTabelu() {
        for (Tim tim : tabela) {
            System.out.println(tim.dajRezultate());
        }
    }

    public void odigrajSpecMec(Scanner sc) {
    	if(mecevi.size()==0) return;
        this.ispisiMeceve();
        System.out.print("Koji mec ocete da odigrate: ");
        int i = sc.nextInt() - 1;
        Mec mec = mecevi.get(i);
        mec.odigrajMec();
        mecevi.remove(i);
        Collections.sort(tabela);
        //sc.nextLine();
    }
    public void odigrajPlayOff() {
    	Mec polufinale1=new Mec(tabela.get(0),tabela.get(3));
    	Mec polufinale2=new Mec(tabela.get(1),tabela.get(2));
    	Tim finalista1=polufinale1.odigrajMec();
    	Tim finalista2=polufinale2.odigrajMec();
    	Mec finale=new Mec(finalista1,finalista2);
    	Tim pobednik=finale.odigrajMec();
    	System.out.println("Šampion je "+pobednik.getNaziv());
    }
    
    @Override
    public String toString() {
    	return this.naziv+" "+this.tabela.size()+" timova";
    }
    
    public void SortByName() {
    	Collections.sort(tabela, new Comparator<Tim>() {

			@Override
			public int compare(Tim tim1, Tim tim2) {
				return tim1.getNaziv().compareTo(tim2.getNaziv());
			}
		});
    }
}

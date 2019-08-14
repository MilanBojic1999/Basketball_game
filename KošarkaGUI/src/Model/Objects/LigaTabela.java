package Model.Objects;
/*
 * Liga se sastoji od Timova i Mečeva između timova
 * Svaka liga ima određen broj Timova koji može da primis
 */

import Model.Main;

import java.util.*;

public class LigaTabela extends AbsLiga {
    private List<Tim> tabela;
    private int size;
    private int brPlayOffTimova;

    public LigaTabela(String naziv, int velicina,int brPlayOffTimova) {
        super(naziv);
        this.tabela = new ArrayList<Tim>(velicina);
        this.sizeMax = velicina;
        size=0;
        this.brPlayOffTimova= Main.stepenDvojke(brPlayOffTimova);
    }

    public void dodajTim(Tim tim) {
    	if(tabela.contains(tim)) return;
        if (tabela.size() < sizeMax) {
            tabela.add(tim);
            mecevi.clear();
            restartujLigu();
            size++;
        } else {
            System.out.println("Popunjena je kvota timova");
        }
    }



    public void napraviMeceve() {
        for (int i = 0; i < tabela.size() - 1; i++) {
            for (int j = i + 1; j < tabela.size(); j++) {
                this.dodajMec(tabela.get(i), tabela.get(j));
            }
        }
    }

    @Override
    public List<Tim> getTimovi() {
        return this.tabela;
    }

    public void ispisiMeceve() {
        int i = 1;
        for (Mec mec : mecevi) {
            System.out.print(i++ + ". ");
            mec.ispisiMec();
        }
    }

    public void odigrajMeceve() {
        super.odigrajMeceve();
        Collections.sort(tabela);
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

    @Override
    public Tim odigrajPlayoff() {
        playOffTimovi.clear();
        if(mecevi.size()==1){
            sampion=mecevi.get(0).odigrajMec();
            return sampion;
        }
        for(Mec mec:mecevi){
            playOffTimovi.add(mec.odigrajMec());
        }
        return null;
    }

    @Override
    public void napraviPlayOff() {
        mecevi.clear();
        if(playOffTimovi==null){
            playOffTimovi=new ArrayList<>();
            for(int i=0;i<brPlayOffTimova;i++){
                playOffTimovi.add(tabela.get(i));
            }
        }
            for (int i = 0, j = playOffTimovi.size() - 1; (j - i) > 0; i++, j--) {
                mecevi.add(new Mec(playOffTimovi.get(i), playOffTimovi.get(j)));
            }


    	/**int brTimova=brPlayOffTimova;
    	List<Tim> timovi=new ArrayList<>();
        timovi.addAll(tabela);
        int brRunde=1;
    	while (brTimova!=1) {
    	    System.out.println("\n"+brRunde+". runda");
    	    System.out.println("----------------");
    	    brRunde++;
            for (int i = 0, j = brTimova - 1; i < brTimova / 2 && j >brTimova / 2-1; i++, j--) {
                mecevi.add(new Mec(timovi.get(i), timovi.get(j)));
            }
            timovi.clear();
            for (Mec mec:mecevi){
                timovi.add(mec.odigrajMec());
            }
            mecevi.clear();
            brTimova/=2;
    	}
            System.out.println("Šampion je "+timovi.get(0).getKlub().getNaziv());
            this.restartujLigu();**/
    }
    
    public void restartujLigu() {
    	for(Tim tim:tabela) {
    		tim.restart();
    	}
        sampion=null;
    }
    @Override
    public String toString() {
    	return this.naziv+" - "+this.tabela.size()+" - timova - "+this.brPlayOffTimova;
    }
    
    public void SortByName() {
    	Collections.sort(tabela, new Comparator<Tim>() {

			@Override
			public int compare(Tim tim1, Tim tim2) {
				return tim1.getKlub().getNaziv().compareTo(tim2.getKlub().getNaziv());
			}
		});
    }

    @Override
    public boolean isFull() {
        return !(tabela.size()<sizeMax);
    }
}

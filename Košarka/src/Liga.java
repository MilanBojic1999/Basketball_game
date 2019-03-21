import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Liga {
    private String naziv;
    private ArrayList<Tim> tabela;
    private ArrayList<Mec> mecevi;
    private int velLige;

    public Liga(String naziv, int velicina) {
        this.tabela = new ArrayList<Tim>(velicina);
        this.mecevi = new ArrayList<Mec>();
        this.naziv = naziv;
        this.velLige = velicina;
    }

    public void dodajTim(Tim tim) {
        if (tabela.size() < velLige) {
            tabela.add(tim);
        } else {
            System.out.println("Popunjena je kvota timova");
        }
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
    }

    public void ispisiTabelu() {
        for (Tim tim : tabela) {
            System.out.println(tim.dajRezultate());
        }
    }

    public void odigrajSpecMec(Scanner sc) {
        this.ispisiMeceve();
        System.out.print("Koji mec ocete da odigrate: ");
        int i = sc.nextInt() - 1;
        Mec mec = mecevi.get(i);
        mec.odigrajMec();
        mecevi.remove(i);
        Collections.sort(tabela);
        //sc.nextLine();
    }
}

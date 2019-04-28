import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Klub zvezda = new Klub("Crvena zvezda", 85, 90);
        Klub rad = new Klub("Rad", 78, 66);
        Klub OFKa = new Klub("OFK Beograd", 64, 70);
        Klub part = new Klub("Partizan", 78, 91);
        Klub vojv = new Klub("Vojvodina", 75, 75);
        Klub mega = new Klub("Mega Leks", 83, 69);
        Klub mor = new Klub("Mornar", 50, 99);

        Liga jelenSuper = new Liga("Jelen super liga", 10);

        jelenSuper.dodajTim(new Tim(zvezda));
        jelenSuper.dodajTim(new Tim(rad));
        jelenSuper.dodajTim(new Tim(mega));
        jelenSuper.dodajTim(new Tim(OFKa));
        jelenSuper.dodajTim(new Tim(part));
        jelenSuper.dodajTim(new Tim(mor));
        jelenSuper.dodajTim(new Tim(vojv));

        jelenSuper.ispisiTabelu();
        jelenSuper.napraviMeceve();
        jelenSuper.odigrajSpecMec(sc);
        jelenSuper.ispisiTabelu();
        jelenSuper.odigrajMeceve();
        jelenSuper.ispisiTabelu();
        Main.RadSaFajlom.upisiTimove(jelenSuper);
    }
}

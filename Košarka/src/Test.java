import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Tim zvezda = new Tim("Crvena zvezda", 85, 90);
        Tim rad = new Tim("Rad", 78, 66);
        Tim OFKa = new Tim("OFK Beograd", 64, 70);
        Tim part = new Tim("Partizan", 78, 91);
        Tim vojv = new Tim("Vojvodina", 75, 75);
        Tim mega = new Tim("Mega Leks", 83, 69);
        Tim mor = new Tim("Mornar", 50, 99);

        Liga jelenSuper = new Liga("Jelen super liga", 10);

        jelenSuper.dodajTim(zvezda);
        jelenSuper.dodajTim(rad);
        jelenSuper.dodajTim(mega);
        jelenSuper.dodajTim(OFKa);
        jelenSuper.dodajTim(vojv);
        jelenSuper.dodajTim(part);
        jelenSuper.dodajTim(mor);

        jelenSuper.ispisiTabelu();
        jelenSuper.napraviMeceve();
        jelenSuper.odigrajSpecMec(sc);
        jelenSuper.ispisiTabelu();
        jelenSuper.odigrajMeceve();
        jelenSuper.ispisiTabelu();
    }
}

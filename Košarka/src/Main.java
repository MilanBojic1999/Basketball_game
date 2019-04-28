import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Liga liga = new Liga("Super liga", 15);
        int i;
        do {
            Main.getInfo();
            i = sc.nextInt();
            switch (i) {
                case 1: {
                    Tim tim = Main.upisiTim(sc);
                    liga.dodajTim(tim);
                    break;
                }
                case 2: {
                    liga.napraviMeceve();
                    break;
                }
                case 3: {
                    liga.ispisiTabelu();
                    break;
                }
                case 4: {
                    liga.ispisiMeceve();
                    break;
                }
                case 5: {
                    liga.odigrajMeceve();
                    break;
                }
                case 6: {
                    liga.odigrajSpecMec(sc);
                    break;
                }
                case 7: {
                    break;
                }
                default:
                    System.out.println("Nema takvih komandi");
            }
        } while (i != 7);
    }

    private static void getInfo() {
        System.out.println("1) Napravite tim\n" + "2) Napravite meceve\n" + "3) Ispisite tabelu\n" + "4) Ispisite meceve\n" + "5) Odigraj sve meceve\n" + "6) Odigrajte specifični mec\n" + "7) Izađite iz programa");
    }

    private static Tim upisiTim(Scanner sc) {
        sc.nextLine();
        System.out.print("Kako se zove vas tim: ");
        String ime = sc.nextLine();
        System.out.print("Kakav je napad vaseg tima: ");
        int nap = sc.nextInt();
        System.out.print("Kakva je odbrana vaseg tima: ");
        int odb = sc.nextInt();
        sc.nextLine();
        return new Tim(ime, nap, odb);
    }
}

package Model;

import Model.DataWork.RadSaFajlovima;
import Model.Objects.AbsLiga;
import Model.Objects.Klub;
import Model.Objects.Tim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Klub> klubovi;
    static List<AbsLiga> lige;
    static AbsLiga trenutna;
    static {
        klubovi=new ArrayList<>();
        lige=new ArrayList<>();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        RadSaFajlovima.ucitajLige();
        PocetnaStracnica(sc);
        sc.close();
    }

    private static void PocetnaStracnica(Scanner sc){
        int i;
        do {
            Menus.PocetnaStrana();
            i=sc.nextInt();
            switch (i){
                case 1:{
                    Menus.OdabirLige();
                    int br=sc.nextInt();
                    sc.nextLine();
                    if(br==1){
                        trenutna=Kreator.napraviLigaTabelu(sc);
                    }else if(br==2){
                        trenutna=Kreator.napraviLigaGrupu(sc);
                    }else{
                        continue;
                    }
                    PravljenjeLige(sc,trenutna);
                    UpravljanjeLigom(sc,trenutna);
                    break;
                }
                case 2:{
                    int k=1;
                    if(lige.size()==0){
                        //RadSaFajlovima.ucitajLige();
                    }
                    for(AbsLiga liga:lige){
                        System.out.println(k+") "+liga.toString());
                        k++;

                    }
                    if(lige.size()==0) continue;
                    int izbor=sc.nextInt();
                    trenutna=lige.get(izbor-1);
                    UpravljanjeLigom(sc,trenutna);
                    break;
                }
                case 0:{
                    return;
                    //System.exit(0);
                }
                default:{
                    System.err.println("Neočekivana opcija");
                }
            }

        }while (true);
    }
    private static void UpravljanjeLigom(Scanner sc,AbsLiga liga){
        int i;
        System.out.println(liga.toString().toUpperCase());
        do {
            Menus.UpravljenjeLige();
            i = sc.nextInt();
            switch (i) {
                case 1: {
                    Klub klub = Kreator.napraviKlub(sc);
                    liga.dodajTim(new Tim(klub));
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
                case 7:{
                    //liga.odigrajPlayOff();
                    break;
                }
                case 8:{
                    RadSaFajlovima.UcitavanjeLige(liga.getNaziv());
                    break;
                }
                case 9:{
                    RadSaFajlovima.UpisivanjeLige(liga);
                    break;
                }
                case 0: {
                    sc.nextLine();
                    return;
                }
                default:
                    System.out.println("Nema takvih komandi");
            }
        } while (true);
    }

    static void PravljenjeLige(Scanner sc,AbsLiga liga){
        int br;
        do{
            Menus.PravljenjeLige();
            br=sc.nextInt();
            switch (br){
                case 1:{
                    liga.dodajTim(new Tim(Kreator.napraviKlub(sc)));
                    break;
                }
                case 2:{
                    int k=1;
                    for (Klub klub:klubovi){
                        System.out.println(k+") "+klub.toString());
                        k++;
                    }
                    if(klubovi.size()==0) continue;
                    k=sc.nextInt();
                    liga.dodajTim(new Tim(klubovi.get(k-1)));
                    break;
                }
                case 0:{
                    return;
                }
                default:{
                    System.err.println("Nema takvih komandi");
                }
            }
        }while (true);
    }
    static AbsLiga dodajUSistem(AbsLiga liga){
        if(lige.contains(liga)){
            System.out.println("Liga već postoji");
            return lige.get(lige.indexOf(liga));
        }else{
            lige.add(liga);
            return liga;
        }
    }

    public static int stepenDvojke(int br){
        int i=1;
        while (i*2<=br){
            i*=2;
        }
        if(i!=br)
            System.out.println("Broj nije stepen dvojke, vraćam najbliži stepen");
        return i;
    }

}

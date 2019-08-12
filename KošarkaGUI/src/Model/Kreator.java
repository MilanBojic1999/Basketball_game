package Model;

import Model.DataWork.DataBase;
import Model.Objects.Klub;
import Model.Objects.LigaGrupe;
import Model.Objects.LigaTabela;

import java.util.Scanner;

public class Kreator {
    public static LigaGrupe napraviLigaGrupu(String line){
        String[] info=line.split(" - ");
        String ime=info[0];
        int grupe=Integer.parseInt(info[1]);
        int timovi=Integer.parseInt(info[3]);
        return (LigaGrupe) Main.dodajUSistem(new LigaGrupe(ime,grupe,timovi));

    }

    public static LigaTabela napraviLigaTabelu(String line){
        String[] info=line.split(" - ");
        String ime=info[0];
        int grupe=Integer.parseInt(info[1]);
        int timovi=Integer.parseInt(info[3]);
        return (LigaTabela) Main.dodajUSistem(new LigaTabela(ime,grupe,timovi));
    }

    public static LigaTabela napraviLigaTabelu(Scanner sc){
        System.out.print("Upišite ime lige: ");
        String naziv=sc.nextLine();
        System.out.print("Upišite broj timova: ");
        int vel=sc.nextInt();
        System.out.print("Upišite broj playoff timova: ");
        int pleyoff=sc.nextInt();
        sc.nextLine();
        return (LigaTabela) Main.dodajUSistem(new LigaTabela(naziv,vel,pleyoff));
    }

    public static LigaGrupe napraviLigaGrupu(Scanner sc){
        System.out.print("Upišite ime lige: ");
        String naziv=sc.nextLine();
        System.out.print("Upišite broj grupa: ");
        int grupa=sc.nextInt();
        System.out.print("Upišite broj timova po grupi: ");
        int tim=sc.nextInt();
        sc.nextLine();
        return (LigaGrupe) Main.dodajUSistem(new LigaGrupe(naziv,grupa,tim));
    }

    public static Klub napraviKlub(Scanner sc) {
        sc.nextLine();
        System.out.print("Upišite ime tima: ");
        String ime = sc.nextLine();
        System.out.print("Upišite atribut napad: ");
        int nap = sc.nextInt();
        System.out.print("Upišite atribut odbrana: ");
        int odb = sc.nextInt();
        sc.nextLine();
        Klub klub = new Klub(ime, nap, odb);
        if(Main.klubovi.contains(klub)){
            return Main.klubovi.get(Main.klubovi.indexOf(klub));
        }else {
            Main.klubovi.add(klub);
            return klub;
        }
    }

    public static Klub napraviKlub(String line){
        DataBase dataBase=DataBase.getInstance();
        String[] info=line.split(" - ");
        String ime=info[0];
        info=info[1].split(" : ");
        int napad=Integer.parseInt(info[0]);
        int odbrana=Integer.parseInt(info[1]);
        Klub klub=new Klub(ime,napad,odbrana);
        //System.out.println(klub);
        if(DataBase.getInstance().getSviKlubovi().contains(klub)){
            System.out.println("Klub je već u sistemu");
            return dataBase.getSviKlubovi().get(dataBase.getSviKlubovi().indexOf(klub));
        }else{
            dataBase.addKlub(klub);
            return klub;
        }
    }
}

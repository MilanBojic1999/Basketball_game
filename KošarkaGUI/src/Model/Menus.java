package Model;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    public static void PocetnaStrana(){
        System.out.println( "1) Napravi novu ligu\n"+
                            "2) Ucitaj postojeću ligu\n"+
                            "0) Izađite iz programa\n");
    }
    public static void OdabirLige(){
        System.out.println( "1) Liga sa tabelama\n"+
                            "2) Liga sa grupama\n"+
                            "0) Izađite odavde\n");
    }
    public static void PravljenjeLige(){
        System.out.println( "1) Napravi novi klub/tim\n"+
                            "2) Ubacite vec postojeće klubove\n"+
                            "0) Završi sa ubacivanjem\n");
    }
    public static void UpravljenjeLige(){
        System.out.print("1) Napravite tim\n" +
                "2) Napravite meceve\n" +
                "3) Ispisite tabelu\n" +
                "4) Ispisite meceve\n" +
                "5) Odigraj sve meceve\n" +
                "6) Odigrajte specifični mec\n" +
                "7) Odigraj pley-off\n" +
                "8) Ucitajte Timova sa fajla\n" +
                "9) Upisite Timove u Fajl\n" +
                "0) Izađite iz programa\n");
    }

   public static List<String> PocetnaStranaTwo(){
       List<String> list=new ArrayList<>();
       list.add("1) Napravi novu ligu");
       list.add("2) Ucitaj postojeću ligu");
       list.add("0) Izađite iz programa");
       return list;
   }

   public static List<String> UpravljanjeLigeTwo(){
       List<String> list=new ArrayList<>();
       list.add("1) Napravite tim");
       list.add("2) Napravite meceve");
       list.add("3) Ispisite tabelu");
       list.add("4) Odigraj sve meceve");
       list.add("5) Odigrajte specifični mec");
       list.add("6) Odigraj pley-off");
       list.add("7) Ucitajte Timova sa fajla");
       list.add("8) Upisite Timove u Fajl");
       list.add("0) Izađite iz programa");
       return list;
   }
}

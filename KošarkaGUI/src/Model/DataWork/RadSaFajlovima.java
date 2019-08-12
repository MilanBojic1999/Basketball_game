package Model.DataWork;

import Model.DataWork.DataBase;
import Model.Kreator;
import Model.Objects.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RadSaFajlovima {

    public static void upisiLige(){
        File file=new File("Files"+File.separator+"SistemLiga");
        List<String> nazivi= new ArrayList<>();
        ucitajLige();

        try (BufferedWriter buff=new BufferedWriter(new FileWriter(file))){
            for(AbsLiga liga: DataBase.getInstance().getSveLige()){
                buff.write(liga.getNaziv()+" - ("+liga.getClass()+")");
                buff.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void UpisivanjeLige(AbsLiga liga){
        File file=new File("Files"+File.separator+liga.getNaziv());
        try {
            if (file.createNewFile()) {
                System.out.println("Napravljen novi Fajl");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        try(BufferedWriter buff=new BufferedWriter(new FileWriter(file))){
            if(liga instanceof LigaTabela){
                buff.write("T//");
            }else if(liga instanceof LigaGrupe){
                buff.write("G//");
            }
            buff.write(liga.toString());
            buff.newLine();
            for(Tim tim:liga.getTimovi()){
                buff.write(tim.getKlub().toString());
                buff.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        upisiLige();
    }

   public static AbsLiga UcitavanjeLige(String naziv){
       AbsLiga liga=null;
       File file=new File("Files"+File.separator+naziv);
       try {
           if (file.createNewFile()) {
               System.out.println("Napravljen novi Fajl");
           }
       }catch (IOException e){
           e.printStackTrace();
           System.exit(-1);
       }
       try(BufferedReader budd=new BufferedReader(new FileReader(file))){
            String ligaInfo=budd.readLine();
            String[] info=ligaInfo.split("//");
            if(info[0].equals("T")){
                liga= Kreator.napraviLigaTabelu(info[1]);
            }else{
                liga=Kreator.napraviLigaGrupu(info[1]);
            }
            System.out.println(liga.toString());
            String linija=budd.readLine();
            do{
                liga.dodajTim(new Tim(Kreator.napraviKlub(linija)));
                linija=budd.readLine();
            }while (linija!=null);
       }catch (IOException e){
           e.printStackTrace();
           System.exit(-1);
       }
        return liga;
   }
   public static List<String> ucitajLige(){
        File file=new File("Files"+File.separator+"SistemLiga");
        List<String> izlaz=new ArrayList<>();
        DataBase base = DataBase.getInstance();
        try(BufferedReader buff=new BufferedReader(new FileReader(file))){
            String linija=buff.readLine();
            if(file.length()==0) return izlaz;
            do {
                if(linija==null) continue;
                izlaz.add(linija);
                String[] info=linija.split(" - ");
                base.addLiga(UcitavanjeLige(info[0]));
                linija=buff.readLine();
            }while (linija!=null);

        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return izlaz;
   }

   public static void ucitajSveTimove(){
        File file=new File("Files"+File.separator+"Timovi");
        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            String line=br.readLine();
            while (line!=null){
                Kreator.napraviKlub(line);
                line=br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
   }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RadSaFajlovima {
    public static void UpisivanjeLige(AbsLiga liga){
        File file=new File("Košarka"+File.separator+"Files"+File.separator+liga.getNaziv());
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
       File file=new File("Košarka"+File.separator+"Files"+File.separator+naziv);
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
                liga=Kreator.napraviLigaTabelu(info[1]);
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
        File file=new File("Košarka"+File.separator+"Files"+File.separator+"SistemLiga");
        List<String> izlaz=new ArrayList<>();
        try(BufferedReader buff=new BufferedReader(new FileReader(file))){
            String linija=buff.readLine();
            if(linija==null) return izlaz;
            do {
                izlaz.add(linija);
                String[] info=linija.split(" - ");
                UcitavanjeLige(info[0]);
                linija=buff.readLine();
            }while (linija!=null);

        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return izlaz;
   }
   public static void upisiLige(){
       File file=new File("Košarka"+File.separator+"Files"+File.separator+"SistemLiga");
        List<String> nazivi= new ArrayList<>();
        ucitajLige();

        try (BufferedWriter buff=new BufferedWriter(new FileWriter(file))){
            for(AbsLiga liga:Main.lige){
                buff.write(liga.getNaziv()+" - ("+liga.getClass()+")");
                buff.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
   }
}

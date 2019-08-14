package Model.Objects;

import Model.Main;

import java.util.*;

public class LigaGrupe extends AbsLiga {
    private HashMap<String, List<Tim>> grupe;
    private int brojGrupa;
    private int brojTimovaPoGrupi;

    public LigaGrupe(String naziv, int brojGrupa, int brojTimovaPoGrupi) {
        super(naziv);
        this.brojGrupa = Main.stepenDvojke(brojGrupa);
        this.brojTimovaPoGrupi = brojTimovaPoGrupi;
        grupe=new HashMap<>();
        this.sizeMax=brojGrupa*brojTimovaPoGrupi;
        this.size=0;
        for(int i=0;i<this.brojGrupa;i++){
            char znak=(char)(65+i);
            grupe.putIfAbsent(Character.toString(znak),new ArrayList<>());
        }
    }

    @Override
    public void dodajTim(Tim tim) {
        int najmanjiBroj=brojTimovaPoGrupi;
        List<Tim> najbolja=null;
        for(List<Tim> grupa:grupe.values()){
            if(grupa.size()<najmanjiBroj){
                najmanjiBroj=grupa.size();
                najbolja=grupa;
            }
        }
        if(najbolja==null){
            System.out.println("Popunjen kapacitet grupa");
            return;
        }else{
            najbolja.add(tim);
            size++;
        }
        restartujLigu();
    }

    @Override
    public void napraviMeceve() {
        for (List<Tim> grupa:grupe.values()){
            for(int i=0;i<grupa.size()-1;i++){
                for (int j=i+1;j<grupa.size();j++){
                    dodajMec(grupa.get(i),grupa.get(j));
                }
            }
        }
    }

    @Override
    public List<Tim> getTimovi() {
        List<Tim> timovi=new ArrayList<>();
        for(List<Tim> grupa:grupe.values()){
            timovi.addAll(grupa);
        }
        return timovi;
    }

    @Override
    public void odigrajMeceve() {
        super.odigrajMeceve();
        for(List<Tim> grupa:grupe.values()){
            Collections.sort(grupa);
        }
    }

    @Override
    public Tim odigrajPlayoff() {
        playOffTimovi.clear();
        for(Mec mec:mecevi)
            playOffTimovi.add(mec.odigrajMec());
        if(playOffTimovi.size()==1)
            return playOffTimovi.get(0);
        else return null;
    }

    @Override
    public void napraviPlayOff() {
        mecevi.clear();
        if(playOffTimovi==null){
            playOffTimovi=new ArrayList<>();
            for(List<Tim> grupa:grupe.values()){
                try {
                    playOffTimovi.add(grupa.get(0));
                    playOffTimovi.add(grupa.get(1));
                }catch (IndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                }
            }
        }
            Random r=new Random();
            while (playOffTimovi.size() != 0) {
                int br1 = r.nextInt(playOffTimovi.size());
                int br2 = r.nextInt(playOffTimovi.size());
                if (br1 == br2) continue;
                dodajMec(playOffTimovi.get(br1), playOffTimovi.get(br2));
                playOffTimovi.remove(Math.max(br1,br2));
                playOffTimovi.remove(Math.min(br1,br2));


        }
        /**List<Tim> pobednici=new ArrayList<>();
        for(List<Tim> grupa:grupe.values()){
            try {
                pobednici.add(grupa.get(0));
                pobednici.add(grupa.get(1));
            }catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
        Random r=new Random();
        int i=1;
        while (pobednici.size()!=1) {
            while (pobednici.size() != 0) {
                int br1 = r.nextInt(pobednici.size());
                int br2 = r.nextInt(pobednici.size());
                if (br1 == br2) continue;
                dodajMec(pobednici.get(br1), pobednici.get(br2));
                pobednici.remove(Math.max(br1,br2));
                pobednici.remove(Math.min(br1,br2));
            }
            System.out.println("\n"+i+". runda");
            System.out.println("----------");
            i++;
            for(Mec mec:mecevi){
                pobednici.add(mec.odigrajMec());
            }
            mecevi.clear();
        }
        System.out.println("Šampion je "+pobednici.get(0).getKlub().getNaziv());
        this.restartujLigu();**/

    }

    @Override
    public void restartujLigu() {
        for(List<Tim> grupa:grupe.values()){
            for(Tim tim:grupa){
                tim.restart();
            }
        }
        sampion=null;
    }

    @Override
    public void SortByName() {
        for(List<Tim> grupa:grupe.values()){
            Collections.sort(grupa, Comparator.comparing(tim -> tim.getKlub().getNaziv()));
        }
    }

    @Override
    public void ispisiTabelu() {
        for(Map.Entry<String,List<Tim>> entry:grupe.entrySet()){
            System.out.println("\n"+entry.getKey()+" grupa");
            System.out.println("------------");
            for(Tim tim:entry.getValue()){
                System.out.println(tim.dajRezultate());
            }
        }
    }

    @Override
    public void odigrajSpecMec(Scanner sc) {
        if(mecevi.size()==0) return;
        this.ispisiMeceve();
        System.out.print("Koji mec ocete da odigrate:");
        int i = sc.nextInt() - 1;
        Mec mec = mecevi.get(i);
        mec.odigrajMec();
        mecevi.remove(i);
        for(List<Tim> grupa:grupe.values()){
            Collections.sort(grupa);
        }
    }

    @Override
    public String toString() {
        return this.naziv+" - "+this.brojGrupa+" - grupa sa po - "+this.brojTimovaPoGrupi+" - timova";
    }

    public HashMap<String, List<Tim>> getGrupe() {
        return grupe;
    }
}

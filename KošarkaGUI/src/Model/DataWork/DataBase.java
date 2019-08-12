package Model.DataWork;

import Model.Objects.AbsLiga;
import Model.Objects.Klub;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase instance;
    private List<AbsLiga> sveLige;
    private List<Klub> sviKlubovi;

    public static DataBase getInstance() {
        if(instance==null)
            instance=new DataBase();
        return instance;
    }

    public DataBase() {
        sveLige=new ArrayList<>();
        sviKlubovi=new ArrayList<>();
    }

    public List<AbsLiga> getSveLige() {
        return sveLige;
    }

    public List<Klub> getSviKlubovi() {
        return sviKlubovi;
    }

    public void addLiga(AbsLiga liga){
        if(sveLige.contains(liga)) return;
        sveLige.add(liga);
    }
    public void addKlub(Klub Klub){
        if(sviKlubovi.contains(Klub)) return;
        sviKlubovi.add(Klub);
    }
}

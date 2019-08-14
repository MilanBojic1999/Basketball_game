package View;

import Model.DataWork.DataBase;
import Model.Objects.AbsLiga;
import Model.Objects.Klub;
import Model.Objects.Tim;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.List;

public class AllExistingTeamsWindow extends Stage {

    private static AllExistingTeamsWindow instance;
    private AbsLiga liga;
    private ListView<Tim> listOfTeams;

    public static AllExistingTeamsWindow getInstance(AbsLiga liga) {
        if(instance==null)
            instance=new AllExistingTeamsWindow(liga);
        instance.setLiga(liga);
        instance.fillListWithTeams();
        instance.show();
        return instance;
    }

    private void fillListWithTeams() {
        List<Klub> allClubs= DataBase.getInstance().getSviKlubovi();
        for(Klub klub:allClubs){
            Tim tim=new Tim(klub);
            if(liga.getTimovi().contains(tim))
                continue;
            listOfTeams.getItems().add(tim);
        }
    }

    public AllExistingTeamsWindow(AbsLiga liga) {
        this.liga=liga;
        listOfTeams=new ListView<>();

        Group group=new Group(listOfTeams);
        setScene(new Scene(group));

        listOfTeams.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER){
                Tim tim=listOfTeams.getSelectionModel().getSelectedItem();
                System.out.println(tim);
                liga.dodajTim(tim);
            }
        });
    }

    public void setLiga(AbsLiga liga) {
        this.liga = liga;
    }
}

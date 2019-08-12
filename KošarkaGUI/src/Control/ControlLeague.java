package Control;

import Model.Objects.AbsLiga;
import View.AllMatchesWindow;
import View.NapraviNoviTimWindow;
import View.UpravljajLigom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControlLeague implements EventHandler<ActionEvent> {
    AbsLiga liga;
    UpravljajLigom view;

    public ControlLeague(UpravljajLigom view) {
        this.view = view;
        liga=view.getLiga();
    }

    @Override
    public void handle(ActionEvent event) {
        int selectedIndex=view.getSelectedIndex();
        switch (selectedIndex){
            case 0:
                if(liga.isFull()){
                    Alert fullLeagueAlert=new Alert(Alert.AlertType.INFORMATION);
                    fullLeagueAlert.setContentText("There is no more room for new teams");
                    fullLeagueAlert.show();
                    break;
                }
                NapraviNoviTimWindow window=new NapraviNoviTimWindow(liga);
                break;
            case 1:
                liga.napraviMeceve();
                break;
            case 2:
                view.showTabel();
                break;
            case 3:
                liga.odigrajMeceve();
                break;
            case 4:
                new AllMatchesWindow(liga.getMecevi());
                break;

        }
    }
}

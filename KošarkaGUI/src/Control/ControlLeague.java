package Control;

import Model.DataWork.RadSaFajlovima;
import Model.Objects.AbsLiga;
import Model.Test;
import View.AllMatchesWindow;
import View.CostumPanes.PlayOffView;
import View.FirstMenuView;
import View.NapraviNoviTimWindow;
import View.UpravljajLigom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControlLeague implements EventHandler<KeyEvent> {
    AbsLiga liga;
    UpravljajLigom view;

    public ControlLeague(UpravljajLigom view) {
        this.view = view;
        liga=view.getLiga();
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.SPACE)
            return;
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
            case 5:
                liga.napraviPlayOff();
                PlayOffView playOffView=new PlayOffView(liga);
                playOffView.populateListView(liga.getMecevi());
                Test.setSceneOntoStage(new Scene(playOffView));
                break;
            case 6:
                RadSaFajlovima.UpisivanjeLige(liga);
                Test.setSceneOntoStage(FirstMenuView.getInstance());
                break;
            default:
                System.out.println("Shouldn't happand");

        }
    }
}

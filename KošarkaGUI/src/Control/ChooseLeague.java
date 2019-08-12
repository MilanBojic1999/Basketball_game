package Control;

import Model.Objects.AbsLiga;
import Model.Test;
import View.FirstMenuView;
import View.UpravljajLigom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChooseLeague implements EventHandler<ActionEvent> {

    private FirstMenuView view;

    public ChooseLeague(FirstMenuView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        AbsLiga liga=view.getChosenLeague();
        Test.setSceneOntoStage(new UpravljajLigom(liga));
    }
}

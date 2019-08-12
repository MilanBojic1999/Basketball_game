package Control;


import Model.Test;
import View.FirstMenuView;
import View.NapraviLiguView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PocetniEvent implements EventHandler<ActionEvent> {

    private FirstMenuView view;

    public PocetniEvent(FirstMenuView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        int selectovano=view.getMeni().getSelectionModel().getSelectedIndex();
        System.out.println(selectovano);
        switch (selectovano){
            case 0:
                Test.setSceneOntoStage(new NapraviLiguView(view.getIzborLige().getSelectionModel().getSelectedItem()));
                break;
            case 1:
                view.populateListWithLeague();
                view.flipListView();
                break;
            case 2:
                System.exit(1);
                break;
            default:
                System.out.println("nis");

        }
    }
}

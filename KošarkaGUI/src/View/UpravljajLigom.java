package View;

import Control.ControlLeague;
import Model.Menus;
import Model.Objects.AbsLiga;
import Model.Objects.LigaGrupe;
import Model.Objects.LigaTabela;
import Model.Test;
import View.CostumPanes.GroupLigaView;
import View.CostumPanes.PlayOffView;
import View.CostumPanes.TabelChangeable;
import View.CostumPanes.TableLigaView;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UpravljajLigom extends Scene {
    private ListView<String> menuView;
    private AbsLiga liga;
    private VBox pane;
    private Node regularTabel;
    public UpravljajLigom(AbsLiga liga) {
        super(new Group());
        this.liga=liga;
        Test.getStage().setTitle(liga.getNaziv());
        pane=new VBox(15);
        pane.setPadding(new Insets(20,20,15,20));

        menuView=new ListView<>(FXCollections.observableArrayList(Menus.UpravljanjeLigeTwo()));
        pane.getChildren().add(menuView);



        if(liga instanceof LigaTabela){
            regularTabel=new TableLigaView((LigaTabela) liga);

        }else if(liga instanceof LigaGrupe){
            regularTabel=new GroupLigaView((LigaGrupe)liga);
        }

        regularTabel.setVisible(false);
        regularTabel.setManaged(false);

        pane.getChildren().add(regularTabel);

        menuView.setOnMouseClicked(event -> {
            showTabel();
        });

        menuView.setOnKeyPressed(new ControlLeague(this));

        pane.autosize();
        setRoot(pane);
    }

    public AbsLiga getLiga() {
        return liga;
    }

    public int getSelectedIndex(){
        return menuView.getSelectionModel().getSelectedIndex();
    }

    public void showTabel(){

        if(regularTabel instanceof TabelChangeable)
            ((TabelChangeable)regularTabel).refrash();
        if(menuView.getSelectionModel().getSelectedIndex()==2){
            regularTabel.setVisible(true);
            regularTabel.setManaged(true);
        }else{
            regularTabel.setManaged(false);
            regularTabel.setVisible(false);

        }
        pane.autosize();
        Test.getStage().sizeToScene();
    }

}

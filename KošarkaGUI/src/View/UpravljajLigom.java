package View;

import Control.ControlLeague;
import Model.Menus;
import Model.Objects.AbsLiga;
import Model.Objects.LigaGrupe;
import Model.Objects.LigaTabela;
import Model.Test;
import View.CostumPanes.GroupLigaView;
import View.CostumPanes.TabelChangeable;
import View.CostumPanes.TableLigaView;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class UpravljajLigom extends Scene {
    private ListView<String> menuView;
    private AbsLiga liga;
    private Button hiddenButton;
    private VBox pane;

    public UpravljajLigom(AbsLiga liga) {
        super(new Group());
        this.liga=liga;
        Test.getStage().setTitle(liga.getNaziv());
        pane=new VBox(15);
        pane.setPadding(new Insets(20,20,15,20));

        hiddenButton=new Button();
        hiddenButton.setVisible(false);

        menuView=new ListView<>(FXCollections.observableArrayList(Menus.UpravljanjeLigeTwo()));
        pane.getChildren().add(menuView);

        pane.getChildren().add(hiddenButton);

        if(liga instanceof LigaTabela){
            pane.getChildren().add(new TableLigaView((LigaTabela) liga));
            pane.getChildren().get(2).setManaged(false);
            pane.getChildren().get(2).setVisible(false);

        }else if(liga instanceof LigaGrupe){
            pane.getChildren().add(new GroupLigaView((LigaGrupe)liga));
            pane.getChildren().get(2).setManaged(false);
            pane.getChildren().get(2).setVisible(false);
        }
        menuView.setOnMouseClicked(event -> {
            showTabel();
        });

        hiddenButton.setOnAction(new ControlLeague(this));

        menuView.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE)
                hiddenButton.fire();
        });

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
        Node node=pane.getChildren().get(2);
        if(node instanceof TabelChangeable)
            ((TabelChangeable)node).refrash();
        if(menuView.getSelectionModel().getSelectedIndex()==2){
            node.setVisible(true);
            node.setManaged(true);
        }else{
            node.setManaged(false);
            node.setVisible(false);

        }
        pane.autosize();
        Test.getStage().sizeToScene();
    }
}

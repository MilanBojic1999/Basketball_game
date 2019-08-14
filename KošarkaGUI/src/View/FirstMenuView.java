package View;

import Control.ChooseLeague;
import Control.PocetniEvent;
import Model.DataWork.DataBase;
import Model.Menus;
import Model.Objects.AbsLiga;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class FirstMenuView extends Scene {

    private static FirstMenuView instance;
    private ListView<String> meni;
    private Button prijava;
    private ComboBox<String> izborLige;
    private ListView<AbsLiga> ponudjeneLige;

    private FirstMenuView() {
        super(new Group());
        VBox pane=new VBox();
        setRoot(pane);
        meni=new ListView<>(FXCollections.observableList(Menus.PocetnaStranaTwo()));
        prijava=new Button("Odradi");
        izborLige=new ComboBox<>();
        izborLige.getItems().addAll("Liga sa Grupama","Liga sa Tabelom");
        izborLige.setValue("Liga sa Grupama");
        izborLige.setVisible(false);
        ponudjeneLige=new ListView<>();

        ponudjeneLige.setVisible(false);
        meni.setVisible(true);

        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(meni,ponudjeneLige);

        pane.getChildren().addAll(izborLige,stackPane,prijava);
        pane.setPadding(new Insets(15));
        pane.setSpacing(7);
        pane.setAlignment(Pos.CENTER);


        meni.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER || event.getCode()== KeyCode.SPACE )
                prijava.fire();
        });

        ponudjeneLige.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER || event.getCode()== KeyCode.SPACE )
                prijava.fire();
        });

        meni.setOnMouseClicked(event -> {
            if(meni.getSelectionModel().getSelectedIndex()==0)
                izborLige.setVisible(true);
            else
                izborLige.setVisible(false);
        });

        prijava.setOnAction(new PocetniEvent(this));
    }

    public static FirstMenuView getInstance() {
        if(instance==null)
            instance=new FirstMenuView();
        if(instance.ponudjeneLige.isVisible())
            instance.flipListView();
        return instance;
    }

    public void populateListWithLeague(){
        List<AbsLiga> allLeague=DataBase.getInstance().getSveLige();
        ponudjeneLige.getItems().clear();
        for(AbsLiga liga:allLeague) {
            ponudjeneLige.getItems().add(liga);
        }
    }

    public void flipListView(){
        if(meni.isVisible()){
            meni.setVisible(false);
            ponudjeneLige.setVisible(true);
            prijava.setOnAction(new ChooseLeague(this));
        }else{
            meni.setVisible(true);
            ponudjeneLige.setVisible(false);
            prijava.setOnAction(new PocetniEvent(this));
        }
    }

    public ListView<String> getMeni() {
        return meni;
    }

    public Button getPrijava() {
        return prijava;
    }

    public ComboBox<String> getIzborLige() {
        return izborLige;
    }

    public AbsLiga getChosenLeague(){
        return ponudjeneLige.getSelectionModel().getSelectedItem();
    }
}

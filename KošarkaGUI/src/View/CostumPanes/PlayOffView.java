package View.CostumPanes;

import Model.Objects.Mec;
import Model.Objects.AbsLiga;
import Model.Objects.Tim;
import Model.Test;
import View.UpravljajLigom;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayOffView extends VBox {
    private ListView<Mec> meceviView;
    private Label brRunde;
    private Button odigrajRundu;
    private AbsLiga liga;
    private int runda;
    private Button restart;

    public PlayOffView(AbsLiga liga) {
        this.liga = liga;
        meceviView=new ListView<>();
        if(liga.getMecevi()==null)
            System.err.println("NEMA MEČEVA");
        runda=1;
        brRunde=new Label(runda+". runda");
        odigrajRundu=new Button("Odigraj playoff mec");

        restart=new Button("Restart");

        HBox hBox=new HBox(7);
        hBox.getChildren().addAll(restart,odigrajRundu);

        setSpacing(10);
        setPadding(new Insets(12.5));
        setAlignment(Pos.CENTER);

        getChildren().addAll(brRunde,meceviView,hBox);

        odigrajRundu.setOnMouseClicked(event -> {
            Tim pobednik=liga.odigrajPlayoff();
            if(pobednik!=null){
                Alert winner=new Alert(Alert.AlertType.CONFIRMATION);
                winner.setTitle("We have a new champ");
                winner.setContentText(pobednik.getKlubName()+" is champion!!!!");
                winner.show();

            }else{
                liga.napraviPlayOff();
                this.populateListView(liga.getMecevi());
                this.meceviView.refresh();
            }
        });

        restart.setOnMouseClicked(event -> {
            liga.restartujLigu();
            Test.setSceneOntoStage(new UpravljajLigom(liga));
        });
    }

    public void populateListView(List<Mec> mecevi){
        meceviView.getItems().clear();
        meceviView.getItems().addAll(mecevi);
    }
}

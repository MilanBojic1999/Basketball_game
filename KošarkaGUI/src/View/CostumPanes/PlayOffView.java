package View.CostumPanes;

import Model.Objects.Mec;
import Model.Objects.AbsLiga;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayOffView extends VBox {
    private ListView<Mec> meceviView;
    private Label brRunde;
    private Button odigrajRundu;
    private AbsLiga liga;
    private int runda;

    public PlayOffView(AbsLiga liga) {
        this.liga = liga;
        meceviView=new ListView<>();
        populateListView(liga.getMecevi());
        runda=1;
        brRunde=new Label(runda+". runda");
        odigrajRundu=new Button("Odigraj playoff mec");

        setSpacing(10);
        setPadding(new Insets(12.5));
        setAlignment(Pos.CENTER);

        getChildren().addAll(brRunde,meceviView,odigrajRundu);
    }

    public void populateListView(List<Mec> mecevi){
        meceviView.getItems().clear();
        meceviView.getItems().addAll(mecevi);
    }
}

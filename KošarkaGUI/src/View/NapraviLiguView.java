package View;

import Model.Objects.AbsLiga;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class NapraviLiguView extends Scene {
        private Label imeLige;
        private Label opcijaOne;
        private Label opcijaTwo;
        private TextField imeLigeField;
        private TextField brojOneField;
        private TextField brojTwoField;
        private Button dodajNovTimButton;
        private Button dodajPostojeciTimButton;
        private Button napraviLiguButton;
        private AbsLiga liga;

    public NapraviLiguView(String string) {

        super(new Group());
        GridPane pane=new GridPane();
        setRoot(pane);

        imeLige=new Label("Ime lige:");
        if(string.equals("Liga sa Grupama")){
            opcijaOne=new Label("Poželjan broj grupa");
            opcijaTwo=new Label("Poželjan broj timova po grupi");
        }else {
            opcijaOne=new Label("Poželjan broj timova");
            opcijaTwo=new Label("Poželjan broj playoff timova");
        }

        imeLigeField=new TextField();
        brojOneField=new TextField();
        brojTwoField=new TextField();

        dodajNovTimButton=new Button("Novi tim");
        dodajPostojeciTimButton=new Button("Postojeci tim");
        napraviLiguButton=new Button("Napravite ligu");

        HBox hBox=new HBox();
        hBox.getChildren().addAll(dodajNovTimButton,dodajPostojeciTimButton,napraviLiguButton);
        hBox.setSpacing(8);

        pane.add(imeLige,0,1);
        pane.add(opcijaOne,0,2);
        pane.add(opcijaTwo,0,3);
        pane.add(imeLigeField,1,1);
        pane.add(brojOneField,1,2);
        pane.add(brojTwoField,1,3);
        pane.add(hBox,1,4);

        pane.setHgap(7);
        pane.setVgap(14);
        pane.setPadding(new Insets(15));

        dodajNovTimButton.setOnAction(event -> {
            imeLigeField.setDisable(true);
            brojTwoField.setDisable(true);
            brojOneField.setDisable(true);
        });

        dodajPostojeciTimButton.setOnAction(event -> {
            imeLigeField.setDisable(true);
            brojTwoField.setDisable(true);
            brojOneField.setDisable(true);
        });



    }

    public TextField getImeLigeField() {
        return imeLigeField;
    }

    public TextField getBrojOneField() {
        return brojOneField;
    }

    public TextField getBrojTwoField() {
        return brojTwoField;
    }

    public Button getDodajNovTimButton() {
        return dodajNovTimButton;
    }

    public Button getDodajPostojeciTimButton() {
        return dodajPostojeciTimButton;
    }

    public Button getNapraviLiguButton() {
        return napraviLiguButton;
    }

    public AbsLiga getLiga() {
        return liga;
    }

    public void setLiga(AbsLiga liga) {
        this.liga = liga;
    }
}

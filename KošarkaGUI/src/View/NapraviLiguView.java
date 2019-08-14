package View;

import Control.NapraviNovuLiguEvent;
import Model.DataWork.DataBase;
import Model.Objects.AbsLiga;
import Model.Objects.LigaGrupe;
import Model.Objects.LigaTabela;
import Model.Test;
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
            makeLeague(string);
            imeLigeField.setDisable(true);
            brojTwoField.setDisable(true);
            brojOneField.setDisable(true);
            new NapraviNoviTimWindow(liga);
        });

        dodajPostojeciTimButton.setOnAction(event -> {
            makeLeague(string);
            imeLigeField.setDisable(true);
            brojTwoField.setDisable(true);
            brojOneField.setDisable(true);
            AllExistingTeamsWindow.getInstance(liga);
        });

        napraviLiguButton.setOnMouseClicked(event -> {
            Test.setSceneOntoStage(new UpravljajLigom(liga));
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

    public void makeLeague(String string){
        if(liga!=null)
            return;
        try {
            String info1 = imeLigeField.getText();
            int info2,info3;
            if(brojOneField.getText().equals("") || brojTwoField.getText().equals("")){
                info2=info3=1;
            }else {
                info2 = Integer.parseInt(brojOneField.getText());
                info3 = Integer.parseInt(brojTwoField.getText());
            }
            if(string.equals("Liga sa Grupama")){
                liga=new LigaGrupe(info1,info2,info3);

            }else{
                liga=new LigaTabela(info1,info2,info3);
            }
            DataBase.getInstance().addLiga(liga);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}

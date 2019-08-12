package View;

import Model.DataWork.DataBase;
import Model.Kreator;
import Model.Objects.AbsLiga;
import Model.Objects.Klub;
import Model.Objects.Tim;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class NapraviNoviTimWindow extends Stage {
    private TextField imeTimaField;
    private Spinner<Integer> napadField;
    private Spinner<Integer> odbranaField;
    private Button dodajTim;

    public NapraviNoviTimWindow(AbsLiga liga) {
        imeTimaField=new TextField();
        SpinnerValueFactory<Integer> napad=new SpinnerValueFactory.IntegerSpinnerValueFactory(50,99,75);
        napadField=new Spinner<>();
        napadField.setValueFactory(napad);
        SpinnerValueFactory<Integer> odbrana=new SpinnerValueFactory.IntegerSpinnerValueFactory(50,99,75);
        odbranaField=new Spinner<>();
        odbranaField.setValueFactory(odbrana);
        dodajTim=new Button("Dodaj tim");



        GridPane pane=new GridPane();

        pane.add(new Label("Ime tima"),0,1);
        pane.add(new Label("Napad tima"),0,2);
        pane.add(new Label("Odbrana tima"),0,3);
        pane.add(imeTimaField,1,1);
        pane.add(napadField,1,2);
        pane.add(odbranaField,1,3);
        pane.add(dodajTim,1,4);


        pane.setPadding(new Insets(20));
        pane.setVgap(14);
        pane.setHgap(7);

        setTitle("Napravi novi Tim");
        Scene scene=new Scene(pane);
        setScene(scene);
        show();

        dodajTim.setOnMouseClicked(event -> {
            DataBase base=DataBase.getInstance();
            String name=imeTimaField.getText();
            int nap=napadField.getValue();
            int odb=odbranaField.getValue();
            //System.out.println(klub);
            Klub klub=new Klub(name,nap,odb);
            if(DataBase.getInstance().getSviKlubovi().contains(klub)){
                System.out.println("Klub je već u sistemu");
                liga.dodajTim(new Tim(base.getSviKlubovi().get(base.getSviKlubovi().indexOf(klub))));
            }else{
                base.addKlub(klub);
                liga.dodajTim(new Tim(klub));
            }
            this.close();
        });

    }


}

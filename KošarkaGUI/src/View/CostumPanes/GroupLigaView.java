package View.CostumPanes;

import Model.Objects.LigaGrupe;
import Model.Objects.Tim;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GroupLigaView extends VBox implements TabelChangeable {
    private TableView<Tim> grupaView;
    private Label nameOfGroup;
    private Button leftButton;
    private Button rightButton;
    private LigaGrupe liga;
    private ListIterator<String> keys;

    public GroupLigaView(LigaGrupe liga) {
        this.liga=liga;

        List<String> lista=new ArrayList<>(liga.getGrupe().keySet());
        keys=lista.listIterator();

        grupaView=new TableView<>();

        TableColumn<Tim,String> nameTableColumn=new TableColumn<>("Name");
        TableColumn<Tim,Integer> datiKoseviTableColumn=new TableColumn<>("Given Points");
        TableColumn<Tim,Integer> primKoseviTableColumn=new TableColumn<>("Taken Points");
        TableColumn<Tim,Integer> pointsTableColumn=new TableColumn<>("Bodovi");

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("klubName"));
        datiKoseviTableColumn.setCellValueFactory(new PropertyValueFactory<>("datiKosevi"));
        primKoseviTableColumn.setCellValueFactory(new PropertyValueFactory<>("primljeniKosevi"));
        pointsTableColumn.setCellValueFactory(new PropertyValueFactory<>("bodovi"));

        grupaView.getColumns().addAll(nameTableColumn,datiKoseviTableColumn,primKoseviTableColumn,pointsTableColumn);

        leftButton=new Button(" < ");
        rightButton=new Button(" > ");
        nameOfGroup=new Label(keys.next());

        HBox hBox=new HBox(10);
        hBox.getChildren().addAll(leftButton,nameOfGroup,rightButton);
        hBox.setAlignment(Pos.CENTER);

        getChildren().addAll(hBox,grupaView);
        fillTable();

        leftButton.setOnMouseClicked(event -> {
            if(keys.hasPrevious()){
                nameOfGroup.setText(keys.previous());
            }else{
                String helpString="";
                while (keys.hasNext()){
                    helpString=keys.next();
                }
                nameOfGroup.setText(helpString);
            }
            fillTable();
        });

        rightButton.setOnMouseClicked(event -> {
            if(keys.hasNext()){
                nameOfGroup.setText(keys.next());
            }else{
                String helpString="";
                while (keys.hasPrevious()){
                    helpString=keys.previous();
                }
                nameOfGroup.setText(helpString);
            }
            fillTable();
        });

    }

    private void fillTable(){
        grupaView.getItems().clear();
        String line=nameOfGroup.getText();
        List<Tim> list=liga.getGrupe().get(line);
        grupaView.getItems().addAll(list);
    }

    @Override
    public void refrash() {
        fillTable();
    }
}

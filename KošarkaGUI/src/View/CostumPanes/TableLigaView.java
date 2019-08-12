package View.CostumPanes;

import Model.Objects.Klub;
import Model.Objects.LigaTabela;
import Model.Objects.Tim;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableLigaView extends VBox implements TabelChangeable{

    private TableView<Tim> tabelaView;
    private LigaTabela liga;
    public TableLigaView(LigaTabela liga) {
        this.liga=liga;
        tabelaView=new TableView<>(FXCollections.observableArrayList(liga.getTimovi()));

        TableColumn<Tim,String> nameTableColumn=new TableColumn<>("Name");
        TableColumn<Tim,Integer> datiKoseviTableColumn=new TableColumn<>("Given Points");
        TableColumn<Tim,Integer> primKoseviTableColumn=new TableColumn<>("Taken Points");
        TableColumn<Tim,Integer> pointsTableColumn=new TableColumn<>("Bodovi");

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("klubName"));
        datiKoseviTableColumn.setCellValueFactory(new PropertyValueFactory<>("datiKosevi"));
        primKoseviTableColumn.setCellValueFactory(new PropertyValueFactory<>("primljeniKosevi"));
        pointsTableColumn.setCellValueFactory(new PropertyValueFactory<>("bodovi"));

        tabelaView.getColumns().addAll(nameTableColumn,datiKoseviTableColumn,primKoseviTableColumn,pointsTableColumn);

        getChildren().add(tabelaView);
    }

    @Override
    public void refrash() {
        tabelaView.getItems().clear();
        tabelaView.getItems().addAll(liga.getTimovi());
    }
}

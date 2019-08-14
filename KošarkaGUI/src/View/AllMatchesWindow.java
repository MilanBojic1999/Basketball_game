package View;

import Model.Objects.Mec;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class AllMatchesWindow extends Stage {
    private ListView<Mec> allMatches;

    public AllMatchesWindow(List<Mec> list) {
        allMatches=new ListView<>();
        allMatches.getItems().addAll(list);

        StackPane group=new StackPane();
        group.getChildren().addAll(allMatches);

        setScene(new Scene(group));
        show();

        allMatches.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE){
                Mec mec=allMatches.getSelectionModel().getSelectedItem();
                mec.odigrajMec();

                Alert info=new Alert(Alert.AlertType.INFORMATION);
                info.setContentText(mec.finallResult());
                info.show();
                list.remove(mec);
                info.setOnCloseRequest(event1 -> {
                    this.close();
                });
            }
        });
    }
}

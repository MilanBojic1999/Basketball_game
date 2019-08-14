package Model;

import Model.DataWork.RadSaFajlovima;
import View.FirstMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Test extends Application {

    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        bootUpSystem();

        stage=primaryStage;
        stage.setScene(FirstMenuView.getInstance());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setSceneOntoStage(Scene scene){
        stage.setScene(scene);
    }

    private static void bootUpSystem(){
        RadSaFajlovima.ucitajSveTimove();
        RadSaFajlovima.ucitajLige();
    }
}

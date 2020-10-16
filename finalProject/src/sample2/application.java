package sample2;

import javafx.application.Application;
import javafx.stage.Stage;

public class application extends Application {
    curController controller;
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new curController();
        controller.buildView(primaryStage);
    }
}

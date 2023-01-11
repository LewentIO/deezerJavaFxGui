package lsalih.deezerjavafxgui.deezer;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {

        new Controller(primaryStage);

    }


    public static void main(String[] args) {

       launch(args);


    }

}


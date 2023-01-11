package lsalih.deezerjavafxgui.deezer.view;

import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadingCircle extends Pane {



private Stage stage;
    public void getLoader(){
        String loaderImage = getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/loader_02.gif").toExternalForm();
        ImageView loaderImageView = new ImageView(loaderImage);
        loaderImageView.setPreserveRatio(true);
        loaderImageView.setFitWidth(50);
//        loaderImageView.setLayoutX(220);
//        loaderImageView.setLayoutY(400);
         Pane pane= new Pane(loaderImageView);

        Scene scene = new Scene(pane,50,50);
        scene.setFill(Color.RED);

        stage = new Stage();
        stage.setScene(scene);
        stage.setX(300);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.show();

    }

    public void closeStage(){
        stage.close();
    }

}

package lsalih.deezerjavafxgui.deezer.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NoResultView extends AnchorPane {

    public NoResultView(){

    }

    public void noResultsInfo(){
        ImageView noResultImage = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/no_results_found.png").toExternalForm());
        Button closeButton = new Button();
        closeButton.setLayoutX(430);
        closeButton.setLayoutY(15);
        closeButton.setPrefSize(20,20);
        closeButton.setId("closeWindowButton");
        ImageView closeIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666595_x_icon.png").toExternalForm());
        closeIcon.setPreserveRatio(true);
        closeIcon.setFitWidth(20);
        closeButton.setGraphic(closeIcon);
        Pane noResultPane = new Pane();
        noResultPane.getChildren().addAll(noResultImage,closeButton);

        Scene noResultScene = new Scene(noResultPane,480,111);
        String pfad = getClass().getResource("NoResultViewCSS.css").toExternalForm();
        noResultScene.getStylesheets().add(pfad);
        Stage noResultStage = new Stage();
        closeButton.setOnAction(e -> noResultStage.close());
        noResultStage.setScene(noResultScene);
        noResultStage.initStyle(StageStyle.UNDECORATED);
        noResultStage.setAlwaysOnTop(true);

        // Move window on mouse is pressed
        noResultScene.setOnMousePressed(pressEvent -> {
            noResultScene.setOnMouseDragged(dragEvent -> {
                noResultStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                noResultStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        noResultStage.show();
    }
}

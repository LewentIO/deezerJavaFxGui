package lsalih.deezerjavafxgui.deezer.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lsalih.deezerjavafxgui.deezer.Controller;

public class SearchView extends AnchorPane {

    private String searchbarIcon;
    private TextField searchField;
    private Button searchButton;
    private Button favoriteButton;
    private Button closeWindow;
    private Button switchToResultView;
    private ImageView imageViewLogo;
    private ImageView searchIconSearchbar;
    private ImageView closeWindowIcon;
    private ImageView background;
    private Button resultViewButton;
    private GridPane gridPane;
    private Pane pane;
    private Pane loaderPane;
    private Scene scene;


    public SearchView() {
    }

    public Scene createSceneSearchView(Button backToResultViewButton, Button closeWindowButton) {

        deezerLogo();
        searchbar(backToResultViewButton);
        favoriteButton();
        closeWindowButton(closeWindowButton);
        background();


        pane.getChildren().addAll(imageViewLogo,searchIconSearchbar, searchField, searchButton, favoriteButton,closeWindow);
        scene = new Scene(pane, 520, 800);
        String pfad = getClass().getResource("SearchViewCSS.css").toExternalForm();
        scene.getStylesheets().add(pfad);

        return scene;
    }

    public void searchbar(Button backToResultViewButton) {
        searchbarIcon = getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/search_icon_for_searchbar.png").toExternalForm();
        searchIconSearchbar = new ImageView(searchbarIcon);
        searchIconSearchbar.setLayoutX(41);
        searchIconSearchbar.setLayoutY(300);

        searchField = new TextField();
        searchField.setOnMouseClicked(e -> searchField.clear());
        searchField.positionCaret(5);
        searchField.setPromptText("Song, Artist, Album");
        searchField.setPrefSize(320, 40);
        searchField.setLayoutX(80);
        searchField.setLayoutY(300);

        searchButton = backToResultViewButton;
        searchButton.setPrefSize(105, 40);
        searchButton.setLayoutX(360);
        searchButton.setLayoutY(300);
        searchButton.setId("searchButton");
    }

    public void favoriteButton() {
        favoriteButton = new Button("FAVORITE LIST");
        favoriteButton.setId("favoriteButton");
        favoriteButton.setPrefSize(425, 40);
        favoriteButton.setLayoutX(41);
        favoriteButton.setLayoutY(380);
    }

    public void background() {
        pane = new Pane();
        background = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/background_animation_without_logo.gif").toExternalForm());
        background.setPreserveRatio(true);
        background.setFitHeight(800);
        pane.getChildren().add(background);
        pane.setPrefSize(520, 800);
        pane.setStyle("-fx-background-color: #2b2b42");
    }

    public void closeWindowButton(Button closeWindowButton){
        closeWindow = closeWindowButton;
        closeWindowButton.setId("closeWindowButton");
        closeWindowButton.setPrefSize(40, 40);
        closeWindowIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666595_x_icon.png").toExternalForm());
        closeWindowIcon.setPreserveRatio(true);
        closeWindowIcon.setFitWidth(30);
        closeWindowButton.setGraphic(closeWindowIcon);
        closeWindowButton.setLayoutX(450);
        closeWindowButton.setLayoutY(20);
    }
    public void deezerLogo(){
        imageViewLogo = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/dezzer_search_engine.png").toExternalForm());
        imageViewLogo.setPreserveRatio(true);
        imageViewLogo.setFitWidth(300);
        imageViewLogo.setLayoutX(100);
        imageViewLogo.setLayoutY(100);
    }


    public TextField getSearchField() {
        return searchField;
    }

}

package lsalih.deezerjavafxgui.deezer;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lsalih.deezerjavafxgui.deezer.dao.WebserviceDeezerDao;
import lsalih.deezerjavafxgui.deezer.model.SearchArtistTrackAlbum;
import lsalih.deezerjavafxgui.deezer.view.*;

import java.io.IOException;
import java.util.List;

public class Controller {

    private LoadingCircle loadingCircle;
    private Scene loaderScene;

    private Stage stage;
    private String loaderImage;
    private ImageView loaderImageView;

    // SceneSearchView
    private Scene sceneSearchView;
    private VBox searchViewVBox;
    private Button searchViewButton;

    // SceneResultView
    private Scene sceneResultView;
    private VBox resultViewVBox;
    private Button resultViewButton;


    // SceneDetailView
    private Scene sceneDetailView;
    private Button detailViewButton;
    private ResultList resultList;

    private SearchView searchView;
    private ResultView resultView;
    private DetailViewAlbum detailViewAlbum;
    private Controller controller;
    private Stage loaderStage;
    private Pane loaderPane;
    private Button closeWindowButton;
    private ImageView closeWindowIcon;
    private NoResultView noResultView;

    public Controller() {
    }


    public Scene createDetailView() {
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> switchScene(sceneResultView));
        return sceneDetailView;
    }


    public Scene createSearchView() {
        closeWindowButton = new Button();
        closeWindowButton.setOnAction(e -> closeWindow());
        searchViewButton = new Button("SEARCH");
        searchViewButton.setOnAction(e -> getSearchResults());
        sceneSearchView = searchView.createSceneSearchView(searchViewButton,closeWindowButton);
        switchScene(sceneResultView);
        return sceneSearchView;
    }

    public Scene createResultView() {
        closeWindowButton = new Button();
        closeWindowButton.setOnAction(e -> closeWindow());
        resultViewButton = new Button("BACK");
        resultViewButton.setOnAction(e -> switchScene(sceneSearchView));
        sceneResultView = resultView.createSceneResultView(resultViewButton,closeWindowButton);
        switchScene(sceneResultView);
        return sceneResultView;
    }

    public void switchScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }


    public void getSearchResults() {
        String search = searchView.getSearchField().getText();
        List<SearchArtistTrackAlbum> result = null;
        System.out.println("*** CHECKPOINT TextField EingabeString *** | " + search);
        WebserviceDeezerDao dao = new WebserviceDeezerDao();
        try {
            if (!search.isEmpty()) {
                result = dao.searchArtistTrackAlbum(search);
            }
        } catch (IOException e) {
            System.out.println("Daten konnten nicht gefunden werden!");
        }

        System.out.println("*** CHECKPOINT SearchResult response *** | " + result);
        if (result != null && !result.isEmpty()) {
            resultList.setResultList(result);
            resultView.createArtistAndSongList(result);
            resultView.createAlbumList(result);
            switchScene(sceneResultView);
        } else {
            noResultView = new NoResultView();
           noResultView.noResultsInfo();
        }
    }



    public Controller(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stageLayout();

        searchView = new SearchView();
        resultView = new ResultView();
        resultList = new ResultList();
        detailViewAlbum = new DetailViewAlbum();

        sceneSearchView = createSearchView();
        sceneResultView = createResultView();
        sceneDetailView = createDetailView();

        // Move window on mouse is pressed
        sceneSearchView.setOnMousePressed(pressEvent -> {
            sceneSearchView.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        // Move window on mouse is pressed
        sceneResultView.setOnMousePressed(pressEvent -> {
            sceneResultView.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        stage.setScene(sceneSearchView);
//        stage.setAlwaysOnTop(true);

        stage.show();

    }



    public void stageLayout(){
        Image windowIcon = new Image("/deezer_icon_color_32x32.png");
        stage.getIcons().add(windowIcon);
        stage.setTitle("Deezer Search Engine");
        stage.setWidth(520);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

    }


    public void closeWindow(){
        stage.close();
    }


    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


}

package lsalih.deezerjavafxgui.deezer.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lsalih.deezerjavafxgui.deezer.Controller;
import lsalih.deezerjavafxgui.deezer.dao.WebserviceDeezerDao;
import lsalih.deezerjavafxgui.deezer.model.AlbumSongList;

import java.io.IOException;
import java.util.List;

public class DetailViewAlbum extends AnchorPane {

    Stage stage;

    private int idAlbum;
    private String albumTitle, coverAlbum, label, releaseDate, tracklist, albumArtistName;

    private Label artistLabelSongName;

    private VBox vBoxSongName;
    private ImageView imageViewLogo, albumCoverImageView, albumCoverListView;
    private Scene scene;

    private VBox vBoxAlbumList, vBoxScrollPaneAlbum;
    private GridPane gridPaneAlbumList;
    private ScrollPane scrollPaneAlbumList;
    private Pane pane;


    public DetailViewAlbum() {
    }

    public void getAlbumDetailsFromSelection(int albumid, String albumTitel, String albumCoverDetail, String albumRecordLabel, String albumReleaseDate, String albumTrackList, String albumArtist) {
        System.out.println("*** CHECKPOINT getAlbumDetailsFromSelection *** | " + albumid + " | | " + albumTitel);

        this.idAlbum = albumid;
        this.albumTitle = albumTitel;
        this.coverAlbum = albumCoverDetail;
        this.label = albumRecordLabel;
        this.releaseDate = albumReleaseDate;
        this.tracklist = albumTrackList;
        this.albumArtistName = albumArtist;
        createSceneDetailViewAlbum();
    }

    public void createSceneDetailViewAlbum() {
        // Get Tracklist from Album via Album-ID request
        getAlbumSongList();

        // Deezer Logo
        String imageLogo = getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/dezzer_search_engine.png").toExternalForm();
        imageViewLogo = new ImageView(imageLogo);
        imageViewLogo.setLayoutX(300);
        imageViewLogo.setLayoutY(20);
        imageViewLogo.setFitWidth(150);
        imageViewLogo.setPreserveRatio(true);

        // Info Section
        Label infoLabel = new Label("I N F O");
        infoLabel.setId("infoLabel");
        infoLabel.setLayoutX(50);
        infoLabel.setLayoutY(55);

        // Line1
        Line line1 = new Line();
        line1.setStartX(50);
        line1.setEndX(450);
        line1.setStartY(85);
        line1.setEndY(85);
        line1.setStrokeWidth(2);
        line1.setStroke(Color.rgb(239, 239, 255));

        // Frame for Album Cover
        ImageView imageFrame = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/album_cover_frame.png").toExternalForm());
        imageFrame.setLayoutX(100);
        imageFrame.setLayoutY(110);

        // Album Cover
        albumCoverImageView = new ImageView(String.valueOf(coverAlbum));
        albumCoverImageView.setPreserveRatio(true);
        albumCoverImageView.setFitWidth(300);
        albumCoverImageView.setLayoutX(100);
        albumCoverImageView.setLayoutY(110);

        // Boxes for Album Labels
        VBox vBoxArtistSongInfo = new VBox();
        HBox hBoxAlbum = new HBox();
        HBox hBoxArtist = new HBox();
        HBox hBoxReleaseDate = new HBox();
        HBox hBoxRecordLabel = new HBox();

        // Album Name Labels
        Label albumLabel = new Label("");
        Label albumTitleLabel = new Label(albumTitle);
        albumTitleLabel.setId("albumTitleLabel");

        // Album Artist Name Label
        Label artistLabel = new Label("by ");
        Label artistLabelInfo = new Label(albumArtistName);
        artistLabelInfo.setId("artistLabelInfo");

        // ReleaseDate Label
        Label releaseDateLabel = new Label("Release Date: ");
        Label releaseDateInfo = new Label(releaseDate);
        releaseDateInfo.setId("releaseDateInfo");

        // Recordlabel Label
        Label recordLabelLabel = new Label("Record Label: ");
        Label recordLabelInfo = new Label(label);
        recordLabelInfo.setId("recordLabelInfo");

        // Set all Labels in Boxes for positioning
        hBoxAlbum.getChildren().addAll(albumLabel, albumTitleLabel);
        hBoxAlbum.setAlignment(Pos.CENTER_LEFT);
        hBoxArtist.getChildren().addAll(artistLabel, artistLabelInfo);
        hBoxArtist.setAlignment(Pos.CENTER_LEFT);
        hBoxReleaseDate.getChildren().addAll(releaseDateLabel, releaseDateInfo);
        hBoxReleaseDate.setAlignment(Pos.CENTER_LEFT);
        hBoxRecordLabel.getChildren().addAll(recordLabelLabel, recordLabelInfo);
        hBoxRecordLabel.setAlignment(Pos.CENTER_LEFT);

        vBoxArtistSongInfo.getChildren().addAll(hBoxAlbum, hBoxArtist, hBoxReleaseDate, hBoxRecordLabel);

        vBoxArtistSongInfo.setPrefSize(300, 100);
        vBoxArtistSongInfo.setLayoutX(100);
        vBoxArtistSongInfo.setLayoutY(420);

        // Tracklist Section
        Label songListeLabel = new Label("T R A C K L I S T");
        songListeLabel.setId("songListeLabel");
        songListeLabel.setLayoutX(50);
        songListeLabel.setLayoutY(570);

        // Line2
        Line line2 = new Line();
        line2.setStartX(50);
        line2.setEndX(450);
        line2.setStartY(600);
        line2.setEndY(600);
        line2.setStrokeWidth(2);
        line2.setStroke(Color.rgb(239, 239, 255));


        // Button for CloseWindow
        Button closeWindowButton = new Button();
        closeWindowButton.setId("closeWindowButton");
        closeWindowButton.setPrefSize(40, 40);
        ImageView closeWindowIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666595_x_icon.png").toExternalForm());
        closeWindowIcon.setPreserveRatio(true);
        closeWindowIcon.setFitWidth(30);
        closeWindowButton.setGraphic(closeWindowIcon);
        closeWindowButton.setOnAction(e -> closeWindow());
        closeWindowButton.setLayoutX(420);
        closeWindowButton.setLayoutY(110);

        // Button for adding to FavoriteListe
        Button favoriteButton = new Button();
        favoriteButton.setId("favoriteButton");
        favoriteButton.setPrefSize(40, 40);
        ImageView favoriteIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon.png").toExternalForm());
        favoriteIcon.setPreserveRatio(true);
        favoriteIcon.setFitWidth(30);
        favoriteButton.setGraphic(favoriteIcon);
        favoriteButton.setLayoutX(420);
        favoriteButton.setLayoutY(160);

        // Set everything in Scene and get the CSS-Stylesheet
        pane = new Pane();
        pane.getChildren().addAll(imageViewLogo, infoLabel, albumLabel, line1, albumCoverImageView, imageFrame,
                vBoxArtistSongInfo, songListeLabel, line2, vBoxScrollPaneAlbum, closeWindowButton, favoriteButton);
        scene = new Scene(pane, 520, 800);
        String pfad = getClass().getResource("DetailViewAlbumCSS.css").toExternalForm();
        scene.getStylesheets().add(pfad);
        scene.setFill(Color.rgb(43, 43, 66));

        // Open the Stage
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        // Move window on mouse is pressed
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
        stage.show();

    }

    public void getAlbumSongList() {

        int search = idAlbum;
        System.out.println("*** CHECKPOINT getAlbumSongList via Album-ID *** | " + search);

        WebserviceDeezerDao dao = new WebserviceDeezerDao();
        List<AlbumSongList> result = null;
        try {
            result = dao.albumSongListList(String.valueOf(search));
        } catch (IOException e) {
            System.out.println("Daten konnten nicht gefunden werden!");
        }
        System.out.println("*** CHECKPOINT get back results from Tracklist request *** | " + result);
        createAlbumSongList(result);

    }

    public void createAlbumSongList(List<AlbumSongList> resultList) {

        vBoxAlbumList = new VBox();
        vBoxAlbumList.setPrefSize(450, 150);
        for (int i = 0; i < resultList.size(); i++) {
            AlbumSongList albumSongList = resultList.get(i);
            // GridPane
            gridPaneAlbumList = new GridPane();
            gridPaneAlbumList.setPrefSize(370, 25);
            if (i%2 == 0){
                gridPaneAlbumList.setStyle("-fx-background-color: #36364c");
            }else {
                gridPaneAlbumList.setStyle("-fx-background-color: #404055");
            }
            GridPane.setMargin(gridPaneAlbumList, new Insets(10, 10, 10, 10));

            // Set Venyl-Icon in Column 0
            albumCoverListView = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/album_list_image.png").toExternalForm());
            albumCoverListView.setId("albumCoverListView");
            albumCoverListView.setPreserveRatio(true);
            albumCoverListView.setFitWidth(25);
            gridPaneAlbumList.addColumn(0, albumCoverListView);
            GridPane.setMargin(albumCoverListView, new Insets(5, 5, 5, 20));

            // Set Song-Name with Vbox in Column 1
            artistLabelSongName = new Label(String.valueOf(albumSongList.getTitleTrack()));
            artistLabelSongName.setId("artistLabelSongName");
            vBoxSongName = new VBox(artistLabelSongName);
            vBoxSongName.setAlignment(Pos.CENTER_LEFT);
            vBoxSongName.setMinWidth(245);
            vBoxSongName.setMaxWidth(245);
            vBoxSongName.setPadding(new Insets(0, 0, 0, 5));
            gridPaneAlbumList.addColumn(1, vBoxSongName);
            VBox.setMargin(gridPaneAlbumList, new Insets(2, 5, 2, 5));

            vBoxAlbumList.getChildren().add(gridPaneAlbumList);
        }

        // ScrollPane
        scrollPaneAlbumList = new ScrollPane(vBoxAlbumList);
        scrollPaneAlbumList.setFitToWidth(true);

        // Set everything in Vbox and get ready for the Scene in createSceneDetailViewAlbum()
        vBoxScrollPaneAlbum = new VBox(scrollPaneAlbumList);
        vBoxScrollPaneAlbum.setPrefSize(450, 150);
        vBoxScrollPaneAlbum.setLayoutX(35);
        vBoxScrollPaneAlbum.setLayoutY(610);
    }

    public void closeWindow() {
        stage.close();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

package lsalih.deezerjavafxgui.deezer.view;

import com.opencsv.exceptions.CsvException;
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
import lsalih.deezerjavafxgui.deezer.Controller;
import lsalih.deezerjavafxgui.deezer.dao.CsvWriterDao;
import lsalih.deezerjavafxgui.deezer.model.SearchArtistTrackAlbum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class ResultView extends Pane {
    private Boolean favButtonIsSelected = false;

    private Button backButton, infobutton, favoriteButton, closeWindow;
    private Label songLabel, albumLabel, artistLabelArtistName, artistLabelSongName, albumLabelAlbumName, albumLabelArtistName;

    private Line line1, line2;
    private ImageView artistImageView, albumImageView, imageViewLogo, closeWindowIcon;
    private ScrollPane scrollPaneArtist, scrollPaneAlbum;
    private GridPane gridPaneArtist, gridPaneAlbum;
    private VBox vBoxArtist, vBoxArtistName, vBoxScrollPaneArtist, vBoxAlbum, vBoxAlbumName, vBoxScrollPaneAlbum;
    private HBox hBoxInfoFavoriteIcons;
    private Scene scene;

    private DetailViewAlbum detailViewAlbum;
    private DetailViewSong detailViewSong;
    private Controller controller;
    private CsvWriterDao csvWriter;


    public ResultView() {
    }

    public Scene createSceneResultView(Button backButtonToSearchButton, Button closeWindowButton) {

        // Deezer Logo
        String imageLogo = getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/dezzer_search_engine.png").toExternalForm();
        imageViewLogo = new ImageView(imageLogo);
        imageViewLogo.setLayoutX(300);
        imageViewLogo.setLayoutY(50);
        imageViewLogo.setFitWidth(150);
        imageViewLogo.setPreserveRatio(true);

        // Artist / Song Section
        songLabel = new Label("S O N G ");
        songLabel.setLayoutX(50);
        songLabel.setLayoutY(85);

        // Line1
        line1 = new Line();
        line1.setStartX(50);
        line1.setEndX(450);
        line1.setStartY(115);
        line1.setEndY(115);
        line1.setStrokeWidth(2);
        line1.setStroke(Color.rgb(239, 239, 255));

        // Album Section
        albumLabel = new Label("A L B U M");
        albumLabel.setLayoutX(50);
        albumLabel.setLayoutY(430);

        // Line2
        line2 = new Line();
        line2.setStartX(50);
        line2.setEndX(450);
        line2.setStartY(460);
        line2.setEndY(460);
        line2.setStrokeWidth(2);
        line2.setStroke(Color.rgb(239, 239, 255));

        // Button back to SearchView
        backButton = backButtonToSearchButton;
        backButton.setId("backButton");
        backButton.setPrefSize(40, 40);
        backButton.setLayoutX(230);
        backButton.setLayoutY(750);
        ImageView backButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666693_search_icon.png").toExternalForm());
        backButtonIcon.setPreserveRatio(true);
        backButtonIcon.setFitWidth(30);
        backButton.setGraphic(backButtonIcon);

        closeWindowButton(closeWindowButton);

        // Set everything in Scene and get the CSS-Stylesheet
        Pane pane = new Pane();
        pane.setPrefSize(520, 800);
        pane.getChildren().addAll(imageViewLogo, songLabel, line1, line2, albumLabel, backButton, closeWindow);
        this.getChildren().add(pane);
        scene = new Scene(this, 520, 800);
        String pfad = getClass().getResource("ResultViewCSS.css").toExternalForm();
        scene.getStylesheets().add(pfad);
        scene.setFill(Color.rgb(43, 43, 66));
        pane.setStyle("-fx-background-color: #2b2b42");

        return scene;
    }

    public void createArtistAndSongList(List<SearchArtistTrackAlbum> resultlist) {

        vBoxArtist = new VBox();
        vBoxArtist.setPrefSize(450, 250);
        for (int i = 0; i < resultlist.size(); i++) {
            SearchArtistTrackAlbum searchArtistTrackAlbum = resultlist.get(i);

            // GridPane
            gridPaneArtist = new GridPane();
            gridPaneArtist.setPrefSize(370, 50);
            if (i % 2 == 0) {
                gridPaneArtist.setStyle("-fx-background-color: #36364c");
            } else {
                gridPaneArtist.setStyle("-fx-background-color: #404055");
            }
            GridPane.setMargin(gridPaneArtist, new Insets(10, 10, 10, 10));
//            gridPaneArtist.setPadding(new Insets(10,10,10,10));

            // Set Artist Picture in Column 0
            String artistImage = searchArtistTrackAlbum.getArtist().getPictureArtist();
            artistImageView = new ImageView(artistImage);
            artistImageView.setId("artistImageView");
            artistImageView.setFitWidth(50);
            artistImageView.setPreserveRatio(true);
            gridPaneArtist.addColumn(0, artistImageView);
            GridPane.setMargin(artistImageView, new Insets(5, 5, 5, 20));

            // Set Song Name and Artist Name in Column 1
            artistLabelSongName = new Label(String.valueOf(searchArtistTrackAlbum.getTrack().getTitleTrack()));
            artistLabelSongName.setId("artistLabelSongName");
            artistLabelArtistName = new Label(String.valueOf(searchArtistTrackAlbum.getArtist().getNameArtist()));
            artistLabelArtistName.setId("artistLabelArtistName");

            // Set Vbox with Song Name and Artist Name in Column 2
            vBoxArtistName = new VBox(artistLabelSongName, artistLabelArtistName);
            vBoxArtistName.setAlignment(Pos.CENTER_LEFT);
            vBoxArtistName.setMinWidth(245);
            vBoxArtistName.setMaxWidth(245);
            vBoxArtistName.setPadding(new Insets(0, 0, 0, 5));
            gridPaneArtist.addColumn(1, vBoxArtistName);

            // Get Information for each row about Song and Artist for pass on to DetailView
            int trackId = searchArtistTrackAlbum.getTrack().getIdTrack();
            String songTitel = String.valueOf(searchArtistTrackAlbum.getTrack().getTitleTrack());
            String pictureArtist = String.valueOf(searchArtistTrackAlbum.getArtist().getPictureArtist());
            String artistName = String.valueOf(searchArtistTrackAlbum.getArtist().getNameArtist());
            String songPreview = String.valueOf(searchArtistTrackAlbum.getTrack().getPreviewTrack());

            getInfoAndFavoriteIconsSong(trackId, songTitel, pictureArtist, artistName, songPreview);

            gridPaneArtist.addColumn(2, hBoxInfoFavoriteIcons);
            GridPane.setMargin(hBoxInfoFavoriteIcons, new Insets(0, 5, 0, 5));

            VBox.setMargin(gridPaneArtist, new Insets(2, 5, 2, 5));

            vBoxArtist.getChildren().add(gridPaneArtist);

        }

        // ScrollPane
        scrollPaneArtist = new ScrollPane(vBoxArtist);
        scrollPaneArtist.setFitToWidth(true);

        // VBox
        vBoxScrollPaneArtist = new VBox(scrollPaneArtist);
        vBoxScrollPaneArtist.setPrefSize(450, 270);
        vBoxScrollPaneArtist.setLayoutX(35);
        vBoxScrollPaneArtist.setLayoutY(125);
        this.getChildren().add(vBoxScrollPaneArtist);
    }

    public void createAlbumList(List<SearchArtistTrackAlbum> resultlist) {

        vBoxAlbum = new VBox();
        vBoxAlbum.setPrefSize(450, 250);
        for (int i = 0; i < resultlist.size(); i++) {
            SearchArtistTrackAlbum searchArtistTrackAlbum = resultlist.get(i);

            // GridPane
            gridPaneAlbum = new GridPane();
            gridPaneAlbum.setPrefSize(370, 50);
            if (i % 2 == 0) {
                gridPaneAlbum.setStyle("-fx-background-color: #36364c");
            } else {
                gridPaneAlbum.setStyle("-fx-background-color: #404055");
            }

            GridPane.setMargin(gridPaneAlbum, new Insets(10, 10, 10, 10));

            // Set Album Cover in Column 0
            String albumCover = searchArtistTrackAlbum.getAlbum().getCoverAlbum();
            albumImageView = new ImageView(albumCover);
            albumImageView.setFitWidth(50);
            albumImageView.setPreserveRatio(true);
            gridPaneAlbum.addColumn(0, albumImageView);
            GridPane.setMargin(albumImageView, new Insets(5, 5, 5, 20));

            // Set Album Name and Artist Name in Column 1
            albumLabelAlbumName = new Label(String.valueOf(searchArtistTrackAlbum.getAlbum().getTitle()));
            albumLabelAlbumName.setId("albumLabelAlbumName");
            albumLabelArtistName = new Label(String.valueOf(searchArtistTrackAlbum.getArtist().getNameArtist()));
            albumLabelArtistName.setId("albumLabelArtistName");
            vBoxAlbumName = new VBox(albumLabelAlbumName, albumLabelArtistName);
            vBoxAlbumName.setAlignment(Pos.CENTER_LEFT);
            vBoxAlbumName.setMinWidth(245);
            vBoxAlbumName.setMaxWidth(245);
            vBoxAlbumName.setPadding(new Insets(0, 0, 0, 5));
            gridPaneAlbum.addColumn(1, vBoxAlbumName);

            // Get Information for each row about Album and Tracklist for pass on to DetailView.
            int albumId = searchArtistTrackAlbum.getAlbum().getIdAlbum();
            String albumTitel = String.valueOf(searchArtistTrackAlbum.getAlbum().getTitle());
            String albumCoverDetail = String.valueOf(searchArtistTrackAlbum.getAlbum().getCoverAlbum());
            String albumRecordLabel = String.valueOf(searchArtistTrackAlbum.getAlbum().getLabel());
            String albumReleaseDate = String.valueOf(searchArtistTrackAlbum.getAlbum().getReleaseDate());
            String albumTrackList = String.valueOf(searchArtistTrackAlbum.getAlbum().getTracklist());
            String albumArtist = String.valueOf(searchArtistTrackAlbum.getArtist().getNameArtist());

            getInfoAndFavoriteIconsAlbum(albumId, albumTitel, albumCoverDetail, albumRecordLabel, albumReleaseDate, albumTrackList, albumArtist);

            gridPaneAlbum.addColumn(2, hBoxInfoFavoriteIcons);
            GridPane.setMargin(hBoxInfoFavoriteIcons, new Insets(0, 5, 0, 5));

            VBox.setMargin(gridPaneAlbum, new Insets(2, 5, 2, 5));

            vBoxAlbum.getChildren().add(gridPaneAlbum);
        }

        // ScrollPane
        scrollPaneAlbum = new ScrollPane(vBoxAlbum);
        scrollPaneAlbum.setFitToWidth(true);

        // VBox with ScrollPane
        vBoxScrollPaneAlbum = new VBox(scrollPaneAlbum);
        vBoxScrollPaneAlbum.setPrefSize(450, 270);
        vBoxScrollPaneAlbum.setLayoutX(35);
        vBoxScrollPaneAlbum.setLayoutY(470);
        this.getChildren().add(vBoxScrollPaneAlbum);
    }

    public void createInfoButton() {
        infobutton = new Button();
        infobutton.setId("infobutton");
        infobutton.setPrefSize(30, 30);
        ImageView infoButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666788_info_information_icon.png").toExternalForm());
        infoButtonIcon.setPreserveRatio(true);
        infoButtonIcon.setFitWidth(20);
        infobutton.setGraphic(infoButtonIcon);
    }

    public void createfavoriteButtonSong(int trackid) throws CsvException {
        favoriteButton = new Button();
        favButtonIsSelected.booleanValue();
        favoriteButton.setId("favoriteButton");
        favoriteButton.setPrefSize(30, 30);
        ImageView favoriteButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon.png").toExternalForm());
        favoriteButtonIcon.setPreserveRatio(true);
        favoriteButtonIcon.setFitWidth(20);
        favoriteButton.setGraphic(favoriteButtonIcon);

        // Check if FavListSong contains the track id or not
        try {
            BufferedReader readerCsvSong =new BufferedReader(new FileReader("C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\favlistsong.csv"));
            String[] values = null;
            String line;

            while ((line = readerCsvSong.readLine()) != null) {
                values = line.split(",");

                if (Arrays.asList(values).contains(String.valueOf(trackid))) {
                    favoriteButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon_selected.png").toExternalForm());
                    favoriteButton.setId("favoriteButtonSelected");
                    favoriteButtonIcon.setPreserveRatio(true);
                    favoriteButtonIcon.setFitWidth(20);
                    favoriteButton.setGraphic(favoriteButtonIcon);
                    favButtonIsSelected = true;
                }}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createfavoriteButtonAlbum(int albumid) {
        favoriteButton = new Button();
        favoriteButton.setId("favoriteButton");
        favoriteButton.setPrefSize(30, 30);
        ImageView favoriteButtonIcon = null;
        favoriteButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon.png").toExternalForm());
        favoriteButtonIcon.setPreserveRatio(true);
        favoriteButtonIcon.setFitWidth(20);
        favoriteButton.setGraphic(favoriteButtonIcon);

        // Check if FavListSong contains the track id or not
        try {
            BufferedReader readerCsvAlbum = null;
            readerCsvAlbum = new BufferedReader(new FileReader("C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\favlistalbum.csv"));
            String[] values = null;
            String line;

            while ((line = readerCsvAlbum.readLine()) != null) {
                values = line.split(",");

                if (Arrays.asList(values).contains(String.valueOf(albumid))) {
                    favoriteButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon_selected.png").toExternalForm());
                    favoriteButton.setId("favoriteButtonSelected");
                    favoriteButtonIcon.setPreserveRatio(true);
                    favoriteButtonIcon.setFitWidth(20);
                    favoriteButton.setGraphic(favoriteButtonIcon);
                    favButtonIsSelected = true;
                }
             }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void checkFavLists() throws IOException {

    }

    public void checkFavListSong() throws IOException {

    }

    public void checkFavListArtist() throws IOException {

    }

    public void checkFavListAlbum() throws IOException {

    }

    // Info + Favorite Icons for GridPane Col 2
    public void getInfoAndFavoriteIconsAlbum(int albumId, String albumTitel, String albumCoverDetail, String albumRecordLabel, String albumReleaseDate, String albumTrackList, String albumArtist) {
        detailViewAlbum = new DetailViewAlbum();
        csvWriter = new CsvWriterDao();

        // Create Info and Favorite Buttons for each row in Album List
        createInfoButton();
        createfavoriteButtonAlbum(albumId);

        // OnButtonClick the Album information goes to DetailView and create a new Stage for the Information
        infobutton.setOnAction(e -> detailViewAlbum.getAlbumDetailsFromSelection(albumId, albumTitel, albumCoverDetail, albumRecordLabel, albumReleaseDate, albumTrackList, albumArtist));
        favoriteButton.setOnAction(e -> csvWriter.getAlbumDetailsFromSelection(albumId, albumTitel, albumCoverDetail, albumRecordLabel, albumReleaseDate, albumTrackList, albumArtist));

        // Set the Buttons in AlbumHBox for the ResultView Scene
        hBoxInfoFavoriteIcons = new HBox(infobutton, favoriteButton);
        hBoxInfoFavoriteIcons.setAlignment(Pos.CENTER_RIGHT);

    }

    public void getInfoAndFavoriteIconsSong(int trackId, String songTitel, String pictureArtist, String artistName, String songPreview) {
        detailViewSong = new DetailViewSong();

        // Create Info and Favorite Buttons for each row in Song and Artist List
        createInfoButton();
        try {
            createfavoriteButtonSong(trackId);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        // OnButtonClick the Song and Artist information goes to DetailView and create a new Stage for the Information
        infobutton.setOnAction(e -> detailViewSong.getSongDetailsFromSelection(trackId, songTitel, pictureArtist, artistName, songPreview));
        favoriteButton.setOnAction(e -> {
            csvWriter.getSongDetailsFromSelection(trackId, songTitel, pictureArtist, artistName, songPreview);
//            setFavButtonOnSelected();
        });


        // Set the Buttons in SongArtistHBox for the ResultView Scene
        hBoxInfoFavoriteIcons = new HBox(infobutton, favoriteButton);
        hBoxInfoFavoriteIcons.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setFavButtonOnSelected() {
        ImageView favoriteButtonIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon_selected.png").toExternalForm());
        favoriteButton.setId("favoriteButtonSelected");
        favoriteButtonIcon.setPreserveRatio(true);
        favoriteButtonIcon.setFitWidth(20);
        favoriteButton.setGraphic(favoriteButtonIcon);
    }

    public void closeWindowButton(Button closeWindowButton) {
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

}

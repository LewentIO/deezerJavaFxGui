package lsalih.deezerjavafxgui.deezer.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DetailViewSong extends AnchorPane {

    private int idTrack;
    private String songTitle, artistPicture, nameArtist, songPreview;
    private ImageView imageViewLogo;
    private Button playButton, stopButton, closeWindowButton, favoriteButton;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Stage stage;

    private Label infoLabel,songTitleLabel, songLabel, artistLabelInfo, artistLabel;
    private HBox hBoxArtist, hBoxSong;
    private VBox vBoxArtistSongInfo;
    private ImageView imageFrame, imageViewArtist, equalizerAnimation, volume1, volume2, playIcon, stopIcon, closeWindowIcon, favoriteIcon;
    private Slider volumeSlider;
    private Line line1;


    public void getSongDetailsFromSelection(int trackId, String titleTrack, String pictureArtist, String artistName, String trackPreview) {
        System.out.println("*** CHECKPOINT getAlbumDetailsFromSelection *** | " + trackId + " | | " + artistName);

        this.idTrack = trackId;
        this.songTitle = titleTrack;
        this.artistPicture = pictureArtist;
        this.nameArtist = artistName;
        this.songPreview = trackPreview;
        createSceneDetailViewSong();
    }


    public void createSceneDetailViewSong() {

        // Deezer Logo
        String imageLogo = getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/dezzer_search_engine.png").toExternalForm();
        imageViewLogo = new ImageView(imageLogo);
        imageViewLogo.setLayoutX(300);
        imageViewLogo.setLayoutY(20);
        imageViewLogo.setFitWidth(150);
        imageViewLogo.setPreserveRatio(true);

        infoSection();
        artistPicture();
        detailInfoBox();
        equalizerIcon();
        mp3FileDownloader();

        // Media Player
        media = new Media(songPreview);
        mediaPlayer = new MediaPlayer(media);

        volumeSlider();
        playButton();
        stopButton();
        closeWindowButton();
        addToFavoriteButton();


        // Set everything in Scene and get the CSS-Stylesheet
        Pane pane = new Pane();
        pane.getChildren().addAll(imageViewLogo, infoLabel, line1, imageViewArtist, imageFrame, vBoxArtistSongInfo, equalizerAnimation,
                playButton, stopButton, volumeSlider, volume1, volume2, closeWindowButton, favoriteButton);
        Scene scene = new Scene(pane, 520, 800);
        String pfad = getClass().getResource("DetailViewSongCSS.css").toExternalForm();
        scene.getStylesheets().add(pfad);
        scene.setFill(Color.rgb(43, 43, 66));

        // Open the Stage
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        // Move window on mouse is pressed
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
        stage.show();
    }

    public void artistPicture() {
        // Frame for Artist Picture
        imageFrame = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/images/album_cover_frame.png").toExternalForm());
        imageFrame.setLayoutX(100);
        imageFrame.setLayoutY(110);

        // Artist Picture
        imageViewArtist = new ImageView(String.valueOf(artistPicture));
        imageViewArtist.setId("imageViewArtist");
        imageViewArtist.setPreserveRatio(true);
        imageViewArtist.setFitWidth(300);
        imageViewArtist.setLayoutX(100);
        imageViewArtist.setLayoutY(110);
    }

    public void detailInfoBox() {
        // Boxes for Artist Labels
        vBoxArtistSongInfo = new VBox();
        hBoxSong = new HBox();
        hBoxArtist = new HBox();

        // Artist Name Label
        artistLabel = new Label("by ");
        artistLabelInfo = new Label(nameArtist);
        artistLabelInfo.setId("artistLabelInfo");

        // Song Name Label
        songLabel = new Label("");
        songTitleLabel = new Label(songTitle);
        songTitleLabel.setId("songTitleLabel");

        // Set all Labels in Boxes for positioning
        hBoxSong.getChildren().addAll(songLabel, songTitleLabel);
        hBoxArtist.getChildren().addAll(artistLabel, artistLabelInfo);
        vBoxArtistSongInfo.getChildren().addAll(hBoxSong, hBoxArtist);
        vBoxArtistSongInfo.setPrefSize(300, 50);
        vBoxArtistSongInfo.setLayoutX(100);
        vBoxArtistSongInfo.setLayoutY(440);
    }

    public void equalizerIcon() {
        // Icon Equalizer Animation
        equalizerAnimation = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/equalizer_animation_transparent_bg.gif").toExternalForm());
        equalizerAnimation.setId("equalizerAnimation");
        equalizerAnimation.setPreserveRatio(true);
        equalizerAnimation.setFitWidth(60);
        equalizerAnimation.setLayoutX(230);
        equalizerAnimation.setLayoutY(530);
    }

    public void playButton() {
        // PlayButton
        playButton = new Button();
        playButton.setId("playButton");
        playButton.setPrefSize(80, 80);
        playIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666551_play_circle_icon.png").toExternalForm());
        playIcon.setPreserveRatio(true);
        playIcon.setFitWidth(70);
        playButton.setGraphic(playIcon);
        playButton.setOnAction(e -> playMedia());
        playButton.setLayoutX(170);
        playButton.setLayoutY(600);
    }

    public void stopButton() {
        // StopButton
        stopButton = new Button();
        stopButton.setId("stopButton");
        stopButton.setPrefSize(80, 80);
        stopIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666552_stop_circle_icon.png").toExternalForm());
        stopIcon.setPreserveRatio(true);
        stopIcon.setFitWidth(70);
        stopButton.setGraphic(stopIcon);
        stopButton.setOnAction(e -> pauseMedia());
        stopButton.setLayoutX(260);
        stopButton.setLayoutY(600);
    }

    public void volumeSlider() {


        // Slider for setting the Volume
        volumeSlider = new Slider();
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
        volumeSlider.setValue(50);
        volumeSlider.setLayoutX(185);
        volumeSlider.setLayoutY(710);

        // Icons for Volume (quiet and loud)
        volume1 = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666709_volume_1_icon.png").toExternalForm());
        volume1.setPreserveRatio(true);
        volume1.setFitWidth(20);
        volume1.setLayoutX(160);
        volume1.setLayoutY(707);

        volume2 = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666734_volume_2_icon.png").toExternalForm());
        volume2.setPreserveRatio(true);
        volume2.setFitWidth(20);
        volume2.setLayoutX(335);
        volume2.setLayoutY(707);
    }

    public void closeWindowButton() {
        closeWindowButton = new Button();
        closeWindowButton.setId("closeWindowButton");
        closeWindowButton.setPrefSize(40, 40);
        closeWindowIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666595_x_icon.png").toExternalForm());
        closeWindowIcon.setPreserveRatio(true);
        closeWindowIcon.setFitWidth(30);
        closeWindowButton.setGraphic(closeWindowIcon);
        closeWindowButton.setOnAction(e -> closeWindow());
        closeWindowButton.setLayoutX(420);
        closeWindowButton.setLayoutY(110);
    }

    public void addToFavoriteButton() {
        favoriteButton = new Button();
        favoriteButton.setId("favoriteButton");
        favoriteButton.setPrefSize(40, 40);
        favoriteIcon = new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666647_heart_icon.png").toExternalForm());
        favoriteIcon.setPreserveRatio(true);
        favoriteIcon.setFitWidth(30);
        favoriteButton.setGraphic(favoriteIcon);
        favoriteButton.setLayoutX(420);
        favoriteButton.setLayoutY(160);
    }

    public void mp3FileDownloader() {
        /*
            Get the MP3-Preview-File from an URL.
            Download it and save it as local media.mp3 file-
        */
        System.out.println("*** CHECKPOINT URL Connection File Download (mp3) *** | " + songPreview);
        try {
            URLConnection urlConnection = new URL(songPreview).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            OutputStream outstream = new FileOutputStream(new File("C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\music\\media.mp3"));
            System.out.println("*** CHECKPOINT FileOutputStream *** | " + outstream.toString());
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playMedia() {
//        playButton.setGraphic(new ImageView(getClass().getResource("/lsalih/deezerjavafxgui/deezer/icons/8666552_stop_circle_icon.png").toExternalForm()));
        mediaPlayer.play();
    }

    public void pauseMedia() {
        mediaPlayer.stop();
    }

    public void closeWindow() {
        mediaPlayer.stop();
        stage.close();
    }


    public void infoSection() {

         infoLabel = new Label("I N F O");
        infoLabel.setId("infoLabel");
        infoLabel.setLayoutX(50);
        infoLabel.setLayoutY(55);
//        songLabel.setStyle("-fx-font-weight: 800");

         line1 = new Line();
        line1.setStartX(50);
        line1.setEndX(450);
        line1.setStartY(85);
        line1.setEndY(85);
        line1.setStrokeWidth(3);
        line1.setStroke(Color.rgb(239, 239, 255));
    }

}

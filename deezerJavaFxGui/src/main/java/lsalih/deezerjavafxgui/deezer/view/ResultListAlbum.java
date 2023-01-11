package lsalih.deezerjavafxgui.deezer.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import lsalih.deezerjavafxgui.deezer.model.AlbumSongList;
import lsalih.deezerjavafxgui.deezer.model.SearchArtistTrackAlbum;

import java.util.List;

public class ResultListAlbum extends ListView<AlbumSongList> {

    public ResultListAlbum(){
    }
        public ResultListAlbum(List< AlbumSongList > searchAlbumSongList){
            setResultListAlbum(searchAlbumSongList);
        }


        public void setResultListAlbum(List<AlbumSongList> searchAlbumSongList){
            ObservableList<AlbumSongList> searchArtistTrackAlbumObservableList = FXCollections.observableList(searchAlbumSongList);

            this.setItems(searchArtistTrackAlbumObservableList);
            this.getSelectionModel().selectFirst();
        }






}

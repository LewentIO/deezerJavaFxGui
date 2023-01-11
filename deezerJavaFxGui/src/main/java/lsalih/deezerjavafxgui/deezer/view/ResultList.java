package lsalih.deezerjavafxgui.deezer.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import lsalih.deezerjavafxgui.deezer.model.SearchArtistTrackAlbum;

import java.util.List;

public class ResultList extends ListView<SearchArtistTrackAlbum> {
    public ResultList(){
    }

    public ResultList(List<SearchArtistTrackAlbum> searchArtistTrackAlbumList){
        setResultList(searchArtistTrackAlbumList);
    }

    public void setResultList(List<SearchArtistTrackAlbum> searchArtistTrackAlbumList){
        ObservableList<SearchArtistTrackAlbum> searchArtistTrackAlbumObservableList = FXCollections.observableList(searchArtistTrackAlbumList);

        this.setItems(searchArtistTrackAlbumObservableList);
        this.getSelectionModel().selectFirst();
    }
}

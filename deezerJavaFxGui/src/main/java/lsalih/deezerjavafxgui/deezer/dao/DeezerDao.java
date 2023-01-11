package lsalih.deezerjavafxgui.deezer.dao;

import lsalih.deezerjavafxgui.deezer.model.AlbumSongList;
import lsalih.deezerjavafxgui.deezer.model.SearchArtistTrackAlbum;

import java.io.IOException;
import java.util.List;

public interface DeezerDao {
    List<SearchArtistTrackAlbum> searchArtistTrackAlbum(String searchString) throws IOException;


    List<AlbumSongList> albumSongListList(String searchString) throws IOException;
}

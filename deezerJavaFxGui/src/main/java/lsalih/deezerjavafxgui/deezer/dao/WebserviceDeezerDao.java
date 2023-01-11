package lsalih.deezerjavafxgui.deezer.dao;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lsalih.deezerjavafxgui.deezer.Controller;
import lsalih.deezerjavafxgui.deezer.model.*;
import lsalih.deezerjavafxgui.deezer.view.SearchView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class WebserviceDeezerDao implements DeezerDao {

   private SearchView searchView;
   private Controller controller;
   private Button searchViewButton;
    private final static String BASE_URL = "https://api.deezer.com/";

    private List<SearchArtistTrackAlbum> jsonArrayToListASA(JSONArray jsonArray) {
        List<SearchArtistTrackAlbum> searchArtistTrackAlbums = new ArrayList<>();
        try {
        for (int i = 0; i < 15; i++) {

            JSONObject searchASAJSONObject = jsonArray.getJSONObject(i);
            SearchArtistTrackAlbum artistTrackAlbum = new SearchArtistTrackAlbum();

            Artist artist = new Artist();
            Track track = new Track();
            Album album = new Album();
            Genre genre = new Genre();

                // Artist
                JSONObject artistObject = searchASAJSONObject.getJSONObject("artist");
                artist.setIdArtist(artistObject.getInt("id"));
                artist = getArtistData(artist.getIdArtist());


                // Track
                track.setIdTrack(searchASAJSONObject.getInt("id"));
                track = getTrackData(track.getIdTrack());


                // Album
                JSONObject albumObject = searchASAJSONObject.getJSONObject("album");
                album.setIdAlbum(albumObject.getInt("id"));
                album = getAlbumData(album.getIdAlbum());

                // Genre
//                JSONObject genreObject = searchASAJSONObject.getJSONObject("data");
//                genre.setIdGenre(genreObject.getInt("id"));
//                genre = getGenreData(genre.getIdGenre());

            //Genre

            artistTrackAlbum.setArtist(artist);
            artistTrackAlbum.setTrack(track);
            artistTrackAlbum.setAlbum(album);

            searchArtistTrackAlbums.add(artistTrackAlbum);

        }
        } catch (Exception e) {
            System.out.println("keine Ergebnisse gefunden");


        }
        return searchArtistTrackAlbums;
    }

    private List<AlbumSongList> jsonArrayToAlbumList(JSONArray jsonArray) {
        List<AlbumSongList> albumSongListList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject searchAlbumSongListJSONObject = jsonArray.getJSONObject(i);
            AlbumSongList albumSongList = new AlbumSongList();

            albumSongList.setTitleTrack(searchAlbumSongListJSONObject.getString("title"));

            JSONObject artistObject = searchAlbumSongListJSONObject.getJSONObject("artist");
            albumSongList.setNameArtist(artistObject.getString("name"));

            albumSongList.setPreviewTrack(searchAlbumSongListJSONObject.getString("preview"));

            albumSongListList.add(albumSongList);
        }
        return albumSongListList;
    }

    private Artist jsonObjectToListArtist(JSONObject jsonObjectArtist) {
        Artist artist = new Artist();
        artist.setIdArtist(jsonObjectArtist.getInt("id"));
        artist.setNameArtist(jsonObjectArtist.getString("name"));
        artist.setPictureArtist(jsonObjectArtist.getString("picture_big"));
        artist.setTracklist(jsonObjectArtist.getString("tracklist"));
        return artist;
    }

    private Track jsonObjectToListTrack(JSONObject jsonObjectTrack) {
        Track track = new Track();
        track.setIdTrack(jsonObjectTrack.getInt("id"));
        track.setTitleTrack(jsonObjectTrack.getString("title"));
        track.setDurationTrack(jsonObjectTrack.getInt("duration"));
        track.setPreviewTrack(jsonObjectTrack.getString("preview"));
        track.setBpmTrack(jsonObjectTrack.getDouble("bpm"));

        return track;
    }

    private Album jsonObjectToListAlbum(JSONObject jsonObjectAlbum) {
        Album album = new Album();
        album.setIdAlbum(jsonObjectAlbum.getInt("id"));
        album.setTitle(jsonObjectAlbum.getString("title"));
        album.setCoverAlbum(jsonObjectAlbum.getString("cover_big"));
        album.setLabel(jsonObjectAlbum.getString("label"));
        album.setReleaseDate(jsonObjectAlbum.getString("release_date"));
        album.setTracklist(jsonObjectAlbum.getString("tracklist"));
//        album.setArtist();
        return album;
    }

    private Genre jsonObjectToListGenre(JSONObject jsonObjectGenre) {
        Genre genre = new Genre();
        genre.setIdGenre(jsonObjectGenre.getInt("id"));
        genre.setNameGenre(jsonObjectGenre.getString("name"));
        genre.setPictureGenre(jsonObjectGenre.getString("picture_big"));
        return genre;
    }


    @Override
    public List<SearchArtistTrackAlbum> searchArtistTrackAlbum(String searchString) throws IOException {
        String endpoint = "";

        String urlEncode = URLEncoder.encode(searchString, StandardCharsets.UTF_8);
        System.out.println("*** CHECKPOINT WebserviceDAO EingabeString *** | " + urlEncode);
        endpoint = BASE_URL + "search?q=" + urlEncode;
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(endpoint);
//        alert.showAndWait();
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("data");
        return jsonArrayToListASA(results);
    }


    @Override
    public List<AlbumSongList> albumSongListList(String searchString) throws IOException {
        String endpoint = BASE_URL + "album/" + searchString + "/tracks";
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(endpoint);
//        alert.showAndWait();
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("data");
        return jsonArrayToAlbumList(results);
    }


    public Artist getArtistData(int idArtist) throws IOException {
        String endpoint = BASE_URL + "artist/" + idArtist;
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObjectArtist = new JSONObject(response);
        return jsonObjectToListArtist(jsonObjectArtist);
    }

    public Track getTrackData(int idTrack) throws IOException {
        String endpoint = BASE_URL + "track/" + idTrack;
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObjectTrack = new JSONObject(response);
        return jsonObjectToListTrack(jsonObjectTrack);
    }

    public Album getAlbumData(int idAlbum) throws IOException {
        String endpoint = BASE_URL + "album/" + idAlbum;
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObjectAlbum = new JSONObject(response);
        return jsonObjectToListAlbum(jsonObjectAlbum);
    }

    public Genre getGenreData(int idGenre) throws IOException {
        String endpoint = BASE_URL + "genre/" + idGenre;
        String response = WebserviceTools.getResponse(endpoint);
        JSONObject jsonObjectGenre = new JSONObject(response);
        return jsonObjectToListGenre(jsonObjectGenre);
    }

}


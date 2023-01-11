package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class SearchArtistTrackAlbum implements Serializable {

    private Artist artist;
    private Album album;
    private Track track;


    @Override
    public String toString() {
        return "Search{" + "\n" +
                "artist=" + artist + "\n" +
                ", album=" + album + "\n" +
                ", track=" + track + "\n" +
                '}'+ "\n";
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}

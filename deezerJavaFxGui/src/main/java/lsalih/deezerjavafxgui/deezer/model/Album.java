package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class Album implements Serializable {

    private int idAlbum;
    private String title;
    private String coverAlbum;
    private String label;
    private String releaseDate;
    private String tracklist;




    @Override
    public String toString() {
        return "Album{" +
                "idAlbum='" + idAlbum + '\'' +
                ", title='" + title + '\'' +
                ", coverAlbum='" + coverAlbum + '\'' +
                ", label='" + label + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", tracklist='" + tracklist + '\'' +
                '}' + "\n";
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCoverAlbum() {
        return coverAlbum;
    }

    public void setCoverAlbum(String coverAlbum) {
        this.coverAlbum = coverAlbum;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}

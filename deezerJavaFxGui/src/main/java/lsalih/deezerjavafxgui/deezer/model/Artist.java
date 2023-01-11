package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class Artist implements Serializable {

    private int idArtist;
    private String nameArtist;
    private String pictureArtist;
    private String tracklist;




    @Override
    public String toString() {
        return "Artist{" +
                "idArtist=" + idArtist +
                ", nameArtist='" + nameArtist + '\'' +
                ", pictureArtist='" + pictureArtist + '\'' +
                '}' + "\n";
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getPictureArtist() {
        return pictureArtist;
    }

    public void setPictureArtist(String pictureArtist) {
        this.pictureArtist = pictureArtist;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}

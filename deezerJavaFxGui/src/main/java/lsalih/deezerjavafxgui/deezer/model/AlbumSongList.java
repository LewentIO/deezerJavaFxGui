package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class AlbumSongList implements Serializable {


    private String titleTrack;
    private String nameArtist;
    private String previewTrack;


    @Override
    public String toString() {
        return "AlbumSongList{" +
                "titleTrack='" + titleTrack + '\'' +
                ", nameArtist='" + nameArtist + '\'' +
                ", previewTrack='" + previewTrack + '\'' +
                '}';
    }

    public String getTitleTrack() {
        return titleTrack;
    }

    public void setTitleTrack(String titleTrack) {
        this.titleTrack = titleTrack;
    }


    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }


    public void setPreviewTrack(String previewTrack) {
        this.previewTrack = previewTrack;
    }
}

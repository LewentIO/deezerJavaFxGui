package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class Genre implements Serializable {

    private int idGenre;
    private String nameGenre;
    private String pictureGenre;


    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getPictureGenre() {
        return pictureGenre;
    }

    public void setPictureGenre(String pictureGenre) {
        this.pictureGenre = pictureGenre;
    }
}

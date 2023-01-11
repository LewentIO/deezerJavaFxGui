package lsalih.deezerjavafxgui.deezer.model;

import java.io.Serializable;

public class Track implements Serializable {

    private int idTrack;
    private String titleTrack;
    private int durationTrack;
    private String previewTrack;

    private double bpmTrack;



    @Override
    public String toString() {
        return "Track{" +
                "idTrack=" + idTrack +
                ", titleTrack='" + titleTrack + '\'' +
                ", durationTrack=" + durationTrack +
                ", previewTrack='" + previewTrack + '\'' +
                ", bpmTrack=" + bpmTrack +
                '}' + "\n";
    }

    public int getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(int idTrack) {
        this.idTrack = idTrack;
    }

    public String getTitleTrack() {
        return titleTrack;
    }

    public void setTitleTrack(String titleTrack) {
        this.titleTrack = titleTrack;
    }

    public int getDurationTrack() {
        return durationTrack;
    }

    public void setDurationTrack(int durationTrack) {
        this.durationTrack = durationTrack;
    }

    public String getPreviewTrack() {
        return previewTrack;
    }

    public void setPreviewTrack(String previewTrack) {
        this.previewTrack = previewTrack;
    }

    public double getBpmTrack() {
        return bpmTrack;
    }

    public void setBpmTrack(double bpmTrack) {
        this.bpmTrack = bpmTrack;
    }


}

package lsalih.deezerjavafxgui.deezer.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvWriterDao {

    // Artist
    private int idArtist;
    private String nameArtistSong;
    private String pictureArtist;

    // Song
    private int idTrack;
    private String titleTrack;
    private int durationTrack;
    private String previewTrack;
    private double bpmTrack;

    // Album
    private int idAlbum;
    private String albumTitle;
    private String albumArtistName;
    private String coverAlbum;
    private String label;
    private String releaseDate;
    private String tracklist;

    private String csvFile = "C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\favlistsong.csv";
    private String csvFileTemp = "C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\temp.csv";
    private ArrayList<String> list = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    public void getSongDetailsFromSelection(int trackId, String titleTrack, String pictureArtist, String artistName, String trackPreview) {
        System.out.println("*** CHECKPOINT getAlbumDetailsFromSelection *** | " + trackId + " | | " + artistName);

        this.idTrack = trackId;
        this.titleTrack = titleTrack;
        this.pictureArtist = pictureArtist;
        this.nameArtistSong = artistName;
        this.previewTrack = trackPreview;
        // Check if FavListSong contains the track id or not
        checkFavSongList();
    }


    public void checkFavSongList() {
        String searchValue = String.valueOf(idTrack);
        String line = "";
        String csvSplitBy = ",";

        // Einlesen der CSV-Datei
        BufferedReader br = null;
        List<String> lines = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));

            // ArrayList für die Zeilen der CSV-Datei
            lines = new ArrayList<String>();
            // Einlesen jeder Zeile
            while ((line = br.readLine()) != null) {
                // Fügen Sie jede Zeile der Liste hinzu
                lines.add(line);
            }
        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        // Zeile löschen
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(searchValue)) {
                // Entfernen der Zeile aus der Liste
                lines.remove(i);
                break;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            for (String s : lines) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Zeilen einfügen
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).contains(searchValue)) {
                // Entfernen der Zeile aus der Liste
                lines.add(String.valueOf(idTrack) + "," + titleTrack + "," +nameArtistSong + "," + previewTrack);

            }
        }
//
//        reader.close();
//        writer.close();



    // Löschen der ursprünglichen CSV-Datei
    File originalFile = new File(csvFile);
        originalFile.delete();

    // Umbenennen der temporären Datei in den Namen der ursprünglichen CSV-Datei
    File tempFile = new File(csvFileTemp);
        tempFile.renameTo(originalFile);

}

    public void writeCsvFile(List<String> lines) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            for (String s : lines) {
                bw.write(s);
                bw.newLine();
//            bw.flush();
//            bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAlbumDetailsFromSelection(int albumid, String albumTitel, String albumCoverDetail, String albumRecordLabel, String albumReleaseDate, String albumTrackList, String albumArtist) {
        System.out.println("*** CHECKPOINT getAlbumDetailsFromSelection *** | " + albumid + " | | " + albumTitel);

        this.idAlbum = albumid;
        this.albumTitle = albumTitel;
        this.coverAlbum = albumCoverDetail;
        this.label = albumRecordLabel;
        this.releaseDate = albumReleaseDate;
        this.tracklist = albumTrackList;
        this.albumArtistName = albumArtist;

        writeFavAlbum();
    }


    public void writeFavSong() {
        try {
            // create CSV-File
            FileWriter fileWriter = new FileWriter("C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\favlistsong.csv", true);

            // write CSV-Values
            fileWriter.append(String.valueOf(idTrack));
            fileWriter.append(",");
            fileWriter.append(titleTrack);
            fileWriter.append(",");
            fileWriter.append(nameArtistSong);
            fileWriter.append(",");
            fileWriter.append(previewTrack);
            fileWriter.append("\n");

            // close file
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavSong7() {
        String line = "";
        String csvSplitBy = ",";

        // Einlesen der CSV-Datei
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // ArrayList für die Zeilen der CSV-Datei
            List<String> lines = new ArrayList<String>();
            // Einlesen jeder Zeile
            while ((line = br.readLine()) != null) {
                // Fügen Sie jede Zeile der Liste hinzu
                lines.add(line);
            }

            // Entfernen der Zeile mit dem gewünschten String
            String searchString = String.valueOf(idTrack);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(searchString)) {
                    // Entfernen der Zeile aus der Liste
                    lines.remove(i);
                    break;
                }
            }

            // Schreiben der modifizierten CSV-Datei
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
                for (String s : lines) {
                    bw.write(s);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFavAlbum() {
        try {
            // create CSV-File
            FileWriter writer = new FileWriter("C:\\java_projects\\deezerJavaFxGui\\deezerJavaFxGui\\src\\main\\resources\\lsalih\\deezerjavafxgui\\deezer\\fav\\favlistalbum.csv");

            // write CSV-Values
            writer.append(String.valueOf(idAlbum));
            writer.append(",");
            writer.append(albumTitle);
            writer.append(",");
            writer.append(albumArtistName);
            writer.append(",");
            writer.append(pictureArtist);
            writer.append(",");
            writer.append(label);
            writer.append(",");
            writer.append(releaseDate);
            writer.append(",");
            writer.append(tracklist);
            writer.append("\n");

            // close file
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

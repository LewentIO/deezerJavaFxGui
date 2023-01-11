package lsalih.deezerjavafxgui.deezer.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebserviceTools {


    private static String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String row;
            while ((row = reader.readLine()) != null){
                stringBuilder.append(row);
            }
        }
        return stringBuilder.toString();
    }


    public static String getResponse(String request) throws IOException {

        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept",  "application/json");
        connection.setRequestMethod("GET"); // Abfragemethode wird auf GET gesetzt (HTTP-GET)

       return  readResponse(connection);
    }
}

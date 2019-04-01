package itsovy.sk.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Internet {

    public static Double getUSDrate() throws IOException {
        URL urlForGetRequest = new URL("https://api.exchangeratesapi.io/latest?symbols=USD");

        double results;

        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject jsonObj = root.getAsJsonObject();
        jsonObj = jsonObj.getAsJsonObject("rates");
        results = jsonObj.get("USD").getAsDouble();

        return results;

    }

}

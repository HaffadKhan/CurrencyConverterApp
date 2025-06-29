package project.currencyconverterapp;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyApiClient {
    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public CurrencyData fetchRates(String baseCurrency) throws Exception {
        String urlStr = BASE_URL + baseCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            throw new RuntimeException("Failed to fetch data: HTTP code " + con.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(jsonBuilder.toString());
        return new CurrencyData(json.getJSONObject("rates"));
    }
}
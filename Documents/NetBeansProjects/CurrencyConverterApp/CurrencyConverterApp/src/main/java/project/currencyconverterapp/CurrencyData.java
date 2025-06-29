package project.currencyconverterapp;
import org.json.JSONObject;

public class CurrencyData {
    private final JSONObject rates;
    public CurrencyData(JSONObject rates) {
        this.rates = rates;
    }
    public double getRate(String currencyCode) {
        return rates.getDouble(currencyCode);
    }
}
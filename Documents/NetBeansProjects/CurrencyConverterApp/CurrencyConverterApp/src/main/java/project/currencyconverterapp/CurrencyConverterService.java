package project.currencyconverterapp;
public class CurrencyConverterService {
    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    public double convertCurrency(String from, String to, double amount) throws Exception {
        CurrencyData data = apiClient.fetchRates(from);
        double rate = data.getRate(to);
        return amount * rate;
    }
}
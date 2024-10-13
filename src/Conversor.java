import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Conversor {
    private String monedaOrigen;
    private String monedaDestino;
    private double valorConversion;

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public double getValorConversion() {
        return valorConversion;
    }

    public void setValorConversion(double valorConversion) {
        this.valorConversion = valorConversion;
    }

    public Conversor(String monedaOrigen, String monedaDestino) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
    }

    public Conversor() {
        this.monedaOrigen = "";
        this.monedaDestino = "";
    }

    public  double obtieneValor(int opcion) throws IOException {
        double valor;
        switch (opcion) {
            case 1:
                monedaOrigen = "USD";
                monedaDestino =  "ARS";
                break;
            case 2:
                monedaOrigen = "ARS";
                monedaDestino =  "USD";
                // valor = obtieneConversion(obtieneRates("ARG"), "USD");
                break;
            case 3:
                monedaOrigen = "USD";
                monedaDestino =  "BRL";
            //    valor = obtieneConversion(obtieneRates("USD"), "BRL");
                break;
            case 4:
                monedaOrigen = "BRL";
                monedaDestino =  "USD";
             //   valor = obtieneConversion(obtieneRates("BRL"), "USD");
                break;
            case 5:
                monedaOrigen = "USD";
                monedaDestino =  "COP";
            //    valor = obtieneConversion(obtieneRates("USD"), "COP");
                break;
            case 6:
                monedaOrigen = "COP";
                monedaDestino =  "USD";
            //    valor = obtieneConversion(obtieneRates("COP"), "USD");
                break;
            default:
                valor = 1.0;
        }

        Conversor conversor = new Conversor(monedaOrigen, monedaDestino);
        conversor.setValorConversion(obtieneConversion(obtieneRates(this.getMonedaOrigen()), this.getMonedaDestino()));

        return conversor.getValorConversion();
    }

    public Rate obtieneRates(String monedaOrigen) throws IOException {

        //String url_str = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD";

          URI direccion = URI.create("https://v6.exchangerate-api.com/v6/51bbe2e43f7dfc64c538e186/latest/" + monedaOrigen);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            try {
                HttpResponse<String> response = null;
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), Rate.class);

            } catch (IOException e) {
                throw new RuntimeException("No encontré la conversión (IO), " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("No encontré la conversión (Ex). " + e.getMessage());
            }
    }

    public double obtieneConversion(Rate registroRate, String monedaDestino) {
        String json;
        String monedaOrigen = registroRate.base_code();
        json = registroRate.conversion_rates().toString().replace("=", "\":\"");
        json = json.replace(", ", "\", \"");
        json = json.replace("{", "{\"");
        json = json.replace("}", "\"}");

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Double>>(){}.getType();
        Map<String, Double> conversionRates = gson.fromJson(json, mapType);
        return conversionRates.getOrDefault(monedaDestino, 0.0);
    }
}

package com.conversordemoneda;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ExchangeRateService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c8cf2f6f3d297d1c91e8fffa/latest/USD";

    public static String[] obtenerTasasDeCambio(Moneda moneda1, Moneda moneda2) throws Exception {
        String[] tasas = new String[2];

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        // Enviar solicitud y obtener respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar estado de la respuesta
        if (response.statusCode() == 200) {
            // Parsear respuesta JSON
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            // Información de tasas de cambio específicas
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            tasas[0] = rates.get(moneda1.name()).getAsString();
            tasas[1] = rates.get(moneda2.name()).getAsString();
        } else {
            throw new RuntimeException("Error al conectar con el servicio: Código de respuesta " + response.statusCode());
        }

        return tasas;
    }
}
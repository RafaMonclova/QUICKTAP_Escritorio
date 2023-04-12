/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodingAPI {

    public static void geocodeBar(String barName) {
        try {
            // Codificar el nombre del bar para usarlo en la URL
            String encodedBarName = URLEncoder.encode(barName, "UTF-8");

            // Crear la URL de la petición a la API de OSM
            String urlString = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedBarName;
            URL url = new URL(urlString);

            // Abrir la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            ArrayList<StringBuilder> lista = new ArrayList<StringBuilder>();
            while ((inputLine = reader.readLine()) != null) {
                StringBuilder response = new StringBuilder();
                response.append(inputLine);
                lista.add(response);
            }
            reader.close();
            System.out.println(lista.size());
            for (int i = 0; i < lista.size(); i++) {
                // Procesar la respuesta JSON
                JSONArray results = new JSONArray(lista.get(i).toString());
                if (results.length() > 0) {
                    JSONObject result = results.getJSONObject(0);
                    String address = result.getString("display_name");
                    String lat = result.getString("lat");
                    String lon = result.getString("lon");

                    // Usar los datos obtenidos en tu aplicación JavaFX como desees
                    System.out.println("Dirección: " + address);
                    System.out.println("Latitud: " + lat);
                    System.out.println("Longitud: " + lon);
                } else {
                    System.out.println("No se encontraron resultados para el bar: " + barName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String barName = "kfc"; // Reemplazar con el nombre del bar que quieres geocodificar
        geocodeBar(barName);
    }
}

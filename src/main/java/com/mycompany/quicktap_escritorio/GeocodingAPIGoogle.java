/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;

/**
 *
 * @author rafam
 */
public class GeocodingAPIGoogle {
    public static void geocodeBar(String barName) {
        try {
            // Configurar el contexto de la API de Google Maps con tu clave de API
            GeoApiContext  context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyAaP6rsOVudwTFDitm7dKUbSJpTBabVyFU")
                    .build();

            // Realizar la solicitud de geocodificación con el nombre del bar
            GeocodingResult[] results = GeocodingApi.geocode(context, barName).await();

            if (results.length > 0) {
                // Obtener la dirección y coordenadas del primer resultado
                String address = results[0].formattedAddress;
                double lat = results[0].geometry.location.lat;
                double lon = results[0].geometry.location.lng;

                // Usar los datos obtenidos en tu aplicación JavaFX como desees
                System.out.println("Dirección: " + address);
                System.out.println("Latitud: " + lat);
                System.out.println("Longitud: " + lon);
            } else {
                System.out.println("No se encontraron resultados para el bar: " + barName);
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String barName = "la bella italia"; // Reemplazar con el nombre del bar que quieres geocodificar
        geocodeBar(barName);
    }
}

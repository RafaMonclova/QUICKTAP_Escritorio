/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import java.util.List;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

/**
 *
 * @author rafam
 */
public class Prueba {

    public static void main(String[] args) {

        GooglePlaces client = new GooglePlaces("AIzaSyAaP6rsOVudwTFDitm7dKUbSJpTBabVyFU");

        List<Place> places = client.getPlacesByQuery("KFC", GooglePlaces.MAXIMUM_RESULTS);

        for (Place place : places) {
            
            
            System.out.println(place.getAddress());
            
        }
    }
}

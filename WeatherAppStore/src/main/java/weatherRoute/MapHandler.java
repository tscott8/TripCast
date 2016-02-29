/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

import java.util.ArrayList;
import java.net.URL;

import java.net.HttpURLConnection;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.InputStreamReader;
import java.io.InputStream;

import java.net.MalformedURLException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
/**
 *
 * @author andrew
 */
public class MapHandler {
    
    //TODO actually hit GOOGLE API and parse for a list of cities
    public ArrayList<Location> getCities(String origin, String destination) {
        ArrayList<Location> cities = new ArrayList<Location>();     
       
       origin = origin.replace(" ", "");
       destination = destination.replace(" ", "");

        
        String apiQuery = "https://maps.googleapis.com/maps/api/directions/json?origin=" + 
                origin + "&destination=" + destination
                + "&key=AIzaSyDbWBUUbN6UHlG9auqFVGmN6WJY9HSe8YI";
        
        try {            
            URL url = new URL(apiQuery);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            
            //You have to step through the ugly, JSON within array within JSON stuff.
            JsonArray stepsArray = root.getAsJsonObject().getAsJsonArray("routes").get(0)
                    .getAsJsonObject().getAsJsonArray("legs").get(0)
                    .getAsJsonObject().getAsJsonArray("steps");          
            
            //loop through steps to get city locations
            for (JsonElement element : stepsArray) {
                //this gets a string value of the distance in miles
                String distance = element.getAsJsonObject().get("distance")
                        .getAsJsonObject().get("text").toString();
                
                if (distance.contains("mi")){
                    //parse return value and cast to a Double to be able to compare
                    distance = distance.replace(" mi", "");
                    distance = distance.replace("\"", "");
                    Double test = Double.parseDouble(distance);

                    //this limits out all the short .5 mile steps
                    //should mostly get different cities now
                    if (Double.compare(test, 10.0) > 0) {  
                        System.out.println("TEST DISTANCE: " + distance + ", " + test);

                        JsonObject location = element.getAsJsonObject().get("end_location").getAsJsonObject();               
                        String latitude = location.get("lat").toString();
                        String longitude = location.get("lng").toString();

                        Location tempLocation = new Location(Double.parseDouble(latitude),Double.parseDouble(longitude));

                        cities.add(tempLocation);
                        //now I need to hit a different API and get CITY names from lat long
                        //http://stackoverflow.com/questions/6548504/how-can-i-get-city-name-from-a-latitude-and-longitude-point
                        //fortunately it shows me how here ^

                        //System.out.println("TEST LOCATION LAT: " + latitude + " LONG: " + longitude);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
 
        //testing();
        
        return cities;
    }
    
    public void testing(){
        //replace these with input values
        String origin = "Boise";
        String destination = "Rexburg";
        
        String apiQuery = "https://maps.googleapis.com/maps/api/directions/json?origin=" + 
                origin + "&destination=" + destination
                + "&key=AIzaSyDbWBUUbN6UHlG9auqFVGmN6WJY9HSe8YI";
        
        try {            
            URL url = new URL(apiQuery);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            
            //You have to step through the ugly, JSON within array within JSON stuff.
            JsonArray stepsArray = root.getAsJsonObject().getAsJsonArray("routes").get(0)
                    .getAsJsonObject().getAsJsonArray("legs").get(0)
                    .getAsJsonObject().getAsJsonArray("steps");          
            
            //loop through steps to get city locations
            for (JsonElement element : stepsArray) {
                //this gets a string value of the distance in miles
                String distance = element.getAsJsonObject().get("distance")
                        .getAsJsonObject().get("text").toString();
                
                //parse return value and cast to a Double to be able to compare
                distance = distance.replace(" mi", "");
                distance = distance.replace("\"", "");
                Double test = Double.parseDouble(distance);
                
                //this limits out all the short .5 mile steps
                //should mostly get different cities now
                if (Double.compare(test, 10.0) > 0) {  
                    System.out.println("TEST DISTANCE: " + distance + ", " + test);
                   
                    JsonObject location = element.getAsJsonObject().get("end_location").getAsJsonObject();               
                    String latitude = location.get("lat").toString();
                    String longitude = location.get("lng").toString();
                
                    //now I need to hit a different API and get CITY names from lat long
                    //http://stackoverflow.com/questions/6548504/how-can-i-get-city-name-from-a-latitude-and-longitude-point
                    //fortunately it shows me how here ^
                    
                    System.out.println("TEST LOCATION LAT: " + latitude + " LONG: " + longitude);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

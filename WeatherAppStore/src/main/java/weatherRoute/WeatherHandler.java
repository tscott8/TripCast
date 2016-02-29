/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author andrew
 */
public class WeatherHandler {
    
    public Tile getWeather(Location locObj) {
        Tile tile = new Tile();
        String apiQuery = "http://api.openweathermap.org/data/2.5/weather?lat=" + locObj.getLatitude()
                + "&lon=" + locObj.getLongitude()
                + "&units=imperial&appid=2de143494c0b295cca9337e1e96b00e0";
        
        System.out.println("TESTING Weather Query: " + apiQuery);
        try {            
            URL url = new URL(apiQuery);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            
            JsonParser jp = new JsonParser();
            JsonObject root = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
            
//System.out.println("TESTING WEATHER API CALL: " + root);
            
            //get the city name          
            String cityName = root.get("name").toString();
            tile.setLocationName(cityName);
//System.out.println("TESING city name: " + cityName);     
            
            //parse through the weather object to grab stuff
            JsonObject tempObj = root.getAsJsonArray("weather").get(0).getAsJsonObject();           
          
            //get the description
            String description = tempObj.get("description").toString();
            tile.setWeatherDescription(description);
            
//System.out.println("TESTING DESCRIPTION: " + description);
            
            //get the icon code for openweather icons
            String icon = tempObj.get("icon").toString();
            icon = icon.replace("\"", "");
            tile.setIcon(icon);
           
            //get the icon id code to get prettier looking icons.
            String iconID = tempObj.get("id").toString();
            double iconIDDouble = Double.parseDouble(iconID);
            tile.setIconID((int) Math.floor(iconIDDouble));
            
            //could also get min and max temps here
            //grab the temp and convert from string to double to int
            tempObj = root.getAsJsonObject("main");
            String temperature = tempObj.get("temp").toString();
            double tempDouble = Double.parseDouble(temperature); 
            tile.setTemperature((int) Math.floor(tempDouble));
            
            //grab the humidity and convert from string to double to int
            String humidity = tempObj.get("humidity").toString();          
            double humidDouble = Double.parseDouble(humidity);            
            tile.setHumidity((int) Math.floor(humidDouble));
     
//System.out.println("TESTING TEMP: " + temperature);
//System.out.println("TESING HUMID: " + humidity);
            
            //parse through the wind object and grab stuff
            tempObj = root.getAsJsonObject("wind");
            String windSpeed = tempObj.get("speed").toString();
            double windDouble = Double.parseDouble(windSpeed);            
            tile.setWindSpeed((int) Math.floor(windDouble));
            
//System.out.println("TESING windspeed: " + windSpeed);           
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return tile;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

import org.apache.commons.lang.WordUtils;
/**
 *
 * @author andrew
 */
public class Tile {
    
    String locationName;
    String weatherDescription;
    String icon;
    int iconID;
    int temperature;
    int humidity;
    int windSpeed;
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public String getLocationName() {
        return locationName;
    }
    
    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
    
    public int getIconID() {
        return iconID;
    }
    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getHumidity() {
        return humidity;
    }
    
    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
    public int getWindSpeed() {
        return windSpeed;
    }
    
    @Override
    public String toString() {
        //TODO change to output however jsp is going to expect it
        //return "this is a tile: " + city + " + " + weather;
        return "nothing yet...";
    }
    
    //TODO set up getters so JSP can handle different outputs, maybe stretch goal...
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

import java.util.ArrayList;
/**
 *
 * @author andrew
 */
public class TileHandler {
    
    //THIS IS DONE!
    public ArrayList<Tile> getTiles(String origin, String destination) {
        //Set up map object
        MapHandler mh = new MapHandler();
        WeatherHandler wh = new WeatherHandler();
        
        //set up arrays
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        ArrayList<Location> cities = mh.getCities(origin, destination);
        
        for (Location city : cities) {
            Tile tile = wh.getWeather(city);
            tiles.add(tile);
        }   
        
        return tiles;
    }
    
}

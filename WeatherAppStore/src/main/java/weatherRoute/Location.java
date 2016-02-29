/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

/**
 *
 * @author andrew
 */
public class Location {
    
    double latitude;
    double longitude;   
    
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}

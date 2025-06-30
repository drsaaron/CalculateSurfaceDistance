/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.calculateangulardistance;

import java.io.Serializable;

/**
 *
 * @author AAR1069
 */
public class Coordinate implements Serializable {
    
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
    
    
}

package com.lester.demo.data.model;

import com.lester.demo.presentation.util.AppUtil;

import java.io.Serializable;

public class UserLocationE implements Serializable {
    String street;
    String city;
    String state;
    String postcode;
    CoordinateE coordinates;
    TimeZoneE timezone;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public CoordinateE getCoordinates() {
        return coordinates;
    }

    public TimeZoneE getTimezone() {
        return timezone;
    }

    public String getAddress() {
        return AppUtil.capitalize(street) + "\n" + AppUtil.capitalize(city) + " - " + AppUtil.capitalize(state);
    }

    public class CoordinateE implements Serializable{
        String latitude;
        String longitude;

        public CoordinateE(double latitude, double longitude) {
            this.latitude = String.valueOf(latitude);
            this.longitude = String.valueOf(longitude);
        }

        public double getLatitude() {
            if(latitude == null || latitude.isEmpty()) latitude = "0.0";
            return Double.parseDouble(latitude);
        }

        public double getLongitude() {
            if(longitude == null || longitude.isEmpty()) longitude = "0.0";
            return Double.parseDouble(longitude);
        }
    }

    public class TimeZoneE implements Serializable{
        String offset;
        String description;

        public String getOffset() {
            return offset;
        }

        public String getDescription() {
            return description;
        }

        public String getTz() {
            return description + " GMT(" + offset + ")";
        }
    }
}
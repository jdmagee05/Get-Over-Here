package com.magee.j.comechill.Services;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.magee.j.comechill.ui.Activity.MessageContactActivity;

/**
 * Created by J on 5/30/2015.
 */
public class GoogleMapsServiceImpl implements GoogleMapsService {

    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    Location currentLocation;


    public GoogleMapsServiceImpl(GoogleApiClient googleApiClient){ this.googleApiClient = googleApiClient; }

    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public Location getLocation(){
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        return currentLocation;
    }

//    @Override
//    public void startLocationUpdates(GoogleApiClient googleApiClient, MessageContactActivity activity) {
//        createLocationRequest();
//        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest);
//    }
}

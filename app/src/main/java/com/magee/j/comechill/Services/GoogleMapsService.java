package com.magee.j.comechill.Services;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by J on 5/30/2015.
 */
public interface GoogleMapsService {

    public Location getLocation();

    //public void startLocationUpdates(GoogleApiClient googleApiClient);
}

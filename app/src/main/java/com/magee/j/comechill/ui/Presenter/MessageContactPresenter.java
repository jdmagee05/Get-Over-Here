package com.magee.j.comechill.ui.Presenter;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by J on 5/30/2015.
 */
public interface MessageContactPresenter {

    public void sendMessage(String name, String phoneNumber, GoogleApiClient googleApiClient);

    //public void startLocationUpdates(GoogleApiClient googleApiClient);
}

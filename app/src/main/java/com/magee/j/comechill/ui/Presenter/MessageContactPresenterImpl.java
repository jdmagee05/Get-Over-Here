package com.magee.j.comechill.ui.Presenter;

import android.telephony.SmsManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.magee.j.comechill.Services.GoogleMapsService;
import com.magee.j.comechill.Services.GoogleMapsServiceImpl;
import com.magee.j.comechill.ui.View.MessageContactView;

/**
 * Created by J on 5/30/2015.
 */
public class MessageContactPresenterImpl implements MessageContactPresenter {

    private GoogleMapsService googleMapsService;
    private MessageContactView messageContactView;

    public MessageContactPresenterImpl(MessageContactView messageContactView){ this.messageContactView = messageContactView; }

    @Override
    public void sendMessage(String name, String phoneNumber, GoogleApiClient googleApiClient) {
        googleMapsService = new GoogleMapsServiceImpl(googleApiClient);
        googleMapsService.getLocation();

        String message = "Test Message for " + name;
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);

        messageContactView.navigateToMainActivity();
    }

//    @Override
//    public void startLocationUpdates(GoogleApiClient googleApiClient) {
//
//    }
}

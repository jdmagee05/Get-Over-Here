package com.magee.j.comechill.ui.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.magee.j.comechill.R;
import com.magee.j.comechill.ui.Presenter.MessageContactPresenter;
import com.magee.j.comechill.ui.Presenter.MessageContactPresenterImpl;
import com.magee.j.comechill.ui.View.MessageContactView;

public class MessageContactActivity extends ActionBarActivity implements MessageContactView, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient googleApiClient;
    private MessageContactPresenter presenter;
    Button sendMessageButton;
    TextView phoneNumberTextView;
    String name = null;
    String phoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_contact);
        buildGoogleApiClient();
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        phoneNumber = extras.getString("phoneNumber");
        setButtonAndTextViewText(name, phoneNumber);
        sendMessageButton.setOnClickListener(this);
        presenter = new MessageContactPresenterImpl(this);
    }

    protected synchronized void buildGoogleApiClient(){
         googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setButtonAndTextViewText(String name, String phoneNumber) {
        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        sendMessageButton.setText("Send invite to " + name);
        phoneNumberTextView.setText(phoneNumber);
    }

    @Override
    public void sendMessage(){
        presenter.sendMessage(name, phoneNumber, googleApiClient);
    }

    @Override
    public void onClick(View v) {
        sendMessage();
    }

    @Override
    public void navigateToMainActivity(){
        Intent intent = new Intent(MessageContactActivity.this, MainActivity.class);
        MessageContactActivity.this.startActivity(intent);
    }

    @Override
    public void onConnected(Bundle bundle) {
        //presenter.startLocationUpdates(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

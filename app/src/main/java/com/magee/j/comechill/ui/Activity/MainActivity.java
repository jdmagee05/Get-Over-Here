package com.magee.j.comechill.ui.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.net.Uri;

import com.magee.j.comechill.R;
import com.magee.j.comechill.ui.Contact;
import com.magee.j.comechill.ui.ContactsArrayAdapter;
import com.magee.j.comechill.ui.Presenter.MainPresenter;
import com.magee.j.comechill.ui.Presenter.MainPresenterImpl;
import com.magee.j.comechill.ui.View.MainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity implements MainView, AdapterView.OnItemClickListener {

    private ListView contactsListView;
    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsListView = (ListView) findViewById(R.id.listview_contacts);
        contactsListView.setOnItemClickListener(this);
        setContactsList();
        presenter = new MainPresenterImpl(this);
    }

    @Override protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<Contact>();
        String name = null;
        String phoneNumber = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(CONTENT_URI, null, null, null, null);

        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = cr.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        int phoneType = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        if(phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        }
                    }
                    phoneCursor.close();
                }

                if(phoneNumber != null) {
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            }
        }

        Collections.sort(contacts);
        return contacts;
    }

    @Override
    public void navigateToMessageContactActivity(String name, String phoneNumber) {
        Intent intent = new Intent(MainActivity.this, MessageContactActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("phoneNumber", phoneNumber);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void setContactsList(){
        List<Contact> contacts = getContacts();
        ContactsArrayAdapter adapter = new ContactsArrayAdapter(this, R.layout.contacts_list_item, contacts);
        contactsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Object contactObject = contactsListView.getItemAtPosition(position);
        presenter.onContactClicked(contactObject);
    }
}

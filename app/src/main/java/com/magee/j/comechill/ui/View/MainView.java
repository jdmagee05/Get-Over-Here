package com.magee.j.comechill.ui.View;

import com.magee.j.comechill.ui.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by J on 5/22/2015.
 */
public interface MainView {

    public void setContactsList();

    public List<Contact> getContacts();

    public void navigateToMessageContactActivity(String name, String phoneNumber);

}

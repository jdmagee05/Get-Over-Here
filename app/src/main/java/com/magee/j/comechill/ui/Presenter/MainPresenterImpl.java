package com.magee.j.comechill.ui.Presenter;

import com.magee.j.comechill.ui.Contact;
import com.magee.j.comechill.ui.View.MainView;

/**
 * Created by J on 5/23/2015.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void onResume() {
        //TODO
    }

    @Override
    public void onContactClicked(Object contactObject) {
        Contact contact = (Contact) contactObject;
        mainView.navigateToMessageContactActivity(contact.getName(), contact.getPhoneNumber());

    }
}

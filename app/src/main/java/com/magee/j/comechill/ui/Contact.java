package com.magee.j.comechill.ui;

import java.util.List;

/**
 * Created by J on 5/26/2015.
 */
public class Contact implements Comparable<Contact> {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Contact other) {
       return name.compareTo(other.name);
    }
}

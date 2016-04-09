package com.magee.j.comechill.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.magee.j.comechill.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 5/26/2015.
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    Context mContext;
    int layoutResourceId;
    private List<Contact> data = null;

    public ContactsArrayAdapter(Context mContext, int layoutResourceId, List<Contact> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        // object item based on the position
        Contact contact = data.get(position);
        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.name);
        textViewItem.setText(contact.getName());
        textViewItem.setTag(contact.getPhoneNumber());

        return convertView;
    }


}

package com.example.cuoiki;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

class NotificationAdapter extends BaseAdapter {
    private ArrayList<Notification> arrNoti;
    private LayoutInflater inflater;
    NotificationActivity conText ;
    public NotificationAdapter(ArrayList<Notification> notifications, NotificationActivity context) {
        this.arrNoti = notifications;
        inflater = LayoutInflater.from(context);
        NotificationActivity conText = context;
    }

    @Override
    public int getCount() {
        return arrNoti.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNoti.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_noti, parent, false);
        }
        TextView txtUsername = convertView.findViewById(R.id.nameNoti);
        Notification tb = arrNoti.get(position);
        txtUsername.setText(tb.getName());
        return convertView;
    }
    }

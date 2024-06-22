package com.example.cuoiki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class notication_apdapter extends BaseAdapter {
    private ArrayList<notication> arrNoti;
    private LayoutInflater inflater;
    String name ,id, date, img ;
    private NotificationActivity conText ;
    public  notication_apdapter(ArrayList<notication> notications, NotificationActivity context) {
        this.arrNoti = notications;
        inflater = LayoutInflater.from(context);
        conText = context; // Assign the MainActivity object to conText
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_noti, parent, false);
        }

        TextView txtUsername = convertView.findViewById(R.id.nameNoti);
        TextView txtUserdate = convertView.findViewById(R.id.dateNoti);
        notication notication = arrNoti.get(position);
        name =notication.name;
        date = notication.date ;
        txtUsername.setText(notication.getName());
        txtUserdate.setText(notication.getDate());
        return convertView;
    }
}

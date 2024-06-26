package com.example.cuoiki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class NotificationAdapter extends BaseAdapter {
    private ArrayList<Notification> arrNoti;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public NotificationAdapter(ArrayList<Notification> notifications, LayoutInflater inflater, OnItemClickListener onItemClickListener) {
        this.arrNoti = notifications;
        this.inflater = inflater;
        this.onItemClickListener = onItemClickListener;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_noti, parent, false);
            holder = new ViewHolder();
            holder.txtUsername = convertView.findViewById(R.id.nameNoti);
            holder.txtUserdate = convertView.findViewById(R.id.dateNoti);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Notification notification = arrNoti.get(position);
        holder.txtUsername.setText(notification.getName());
        holder.txtUserdate.setText(notification.getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(notification);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtUsername;
        TextView txtUserdate;
    }

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }
}

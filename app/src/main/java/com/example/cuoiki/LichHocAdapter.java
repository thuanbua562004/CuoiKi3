package com.example.cuoiki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LichHocAdapter  extends BaseAdapter {
    public ArrayList<MonHoc> arrayList = new ArrayList<>();
    private LayoutInflater inflater;
    LichHocActivity conText ;

    public LichHocAdapter(ArrayList<MonHoc> arrayList, LichHocActivity conText) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(conText);
        this.conText = conText;
    }

    @Override
    public int getCount() {
        return  arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.lichhoc,parent,false);
        }
        MonHoc lichHocActivity = arrayList.get(position);
        TextView txtTenMon = convertView.findViewById(R.id.monhoc);
        TextView txtThoiGian = convertView.findViewById(R.id.thoigian);
        TextView txtNgay = convertView.findViewById(R.id.ngay);
        TextView txtGiaoVien = convertView.findViewById(R.id.giaovien);
        TextView txtPhong = convertView.findViewById(R.id.phong);

        txtTenMon.setText(lichHocActivity.getTenmon());
        txtThoiGian.setText(lichHocActivity.getThoigian());
        txtNgay.setText(lichHocActivity.getNgay());
        txtGiaoVien.setText(lichHocActivity.getGiaovien());
        txtPhong.setText(lichHocActivity.getPhong());
        return  convertView;
    }
}

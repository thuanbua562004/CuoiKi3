package com.example.cuoiki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserActivity extends MainActivity {
    public TextView txtHoTen ,txtMssv,txtNamsinh, txtQuequan,txtNganhhoc,txtEmail;
    ArrayList<SinhVien> arrayList =new ArrayList<>();
    String url ="http://192.168.40.104/QLSV/user.php";
    public  String mssv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsinhvien);
        anhxa();
        mssv = getMssv();
        Log.i("TAG1", "onCreate: "+mssv);
        getThongTinSv(url, mssv);
    }

    public void anhxa() {
        txtEmail = findViewById(R.id.txtemail);
        txtMssv = findViewById(R.id.txtmssv);
        txtHoTen = findViewById(R.id.txthoten);
        txtNamsinh = findViewById(R.id.txtnamsinh);
        txtNganhhoc = findViewById(R.id.txtnganhhoc);
        txtQuequan = findViewById(R.id.txtquequan);
        txtEmail = findViewById(R.id.txtemail);
    }

    public void getThongTinSv(String url, String mssv) {
        arrayList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        SinhVien sv = new SinhVien(
                                obj.getString("username"),
                                obj.getString("password"),
                                obj.getString("mssv"),
                                obj.getString("quequan"),
                                obj.getString("ngaysinh"),
                                obj.getString("nganhhoc"),
                                obj.getString("hoten"),
                                obj.getString("email")
                        );
                        arrayList.add(sv);
                    }
                    SinhVien sv = arrayList.get(0);
                    setThongTin(sv);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1", error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mssv", mssv);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private String getMssv() {
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String mssv = sharedPref.getString("key", "");
        return mssv ;
    }

    private void setThongTin(SinhVien sv) {
        txtMssv.setText( "MSSV: "+sv.getMssv());
        txtHoTen.setText("Ho Ten: "+sv.getHoten());
        txtNamsinh.setText("Ngay Sinh: "+sv.getNgaysinh());
        txtQuequan.setText("Que Quan: "+sv.getQuequan());
        txtNganhhoc.setText("Nganh Hoc: "+sv.getNganhhoc());
        txtEmail.setText("Email :"+ sv.getEmail());
    }
}
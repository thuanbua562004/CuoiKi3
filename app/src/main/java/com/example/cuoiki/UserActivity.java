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

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends MainActivity {
    public TextView txtHoTen ;
    public  TextView txtMssv ;
    public TextView txtNamsinh ;
    public TextView  txtQuequan;
    public  TextView txtNganhhoc ;
    public TextView txtEmail;
    String url = "http://192.168.1.19/QLSV/user.php"; // Thay đổi địa chỉ URL phù hợp với server của bạn
    public  String mssv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsinhvien);
        anhxa();
        mssv = getMssv();
        getThongTinSv(url, mssv);
    }

    private void anhxa() {
        txtEmail = findViewById(R.id.txtemail);
        txtMssv = findViewById(R.id.txtmssv);
        txtHoTen = findViewById(R.id.txthoten);
        txtNamsinh = findViewById(R.id.txtnamsinh);
        txtNganhhoc = findViewById(R.id.txtnganhhoc);
        txtQuequan = findViewById(R.id.txtquequan);
    }

    private void getThongTinSv(String url, String mssv) {
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
                                obj.getString("hoten")
                        );
                        arrayList.add(sv);
                    }

                    if (!arrayList.isEmpty()) {
                        SinhVien sv = arrayList.get(0);
                        setThongTin(sv);
                        Log.i("TAG1", sv.getHoten());
                    }

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
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString("mssvkey", "");
    }

    private void setThongTin(SinhVien sv) {
        txtMssv.setText( "MSSV: "+sv.getMssv());
        txtHoTen.setText("Ho Ten: "+sv.getHoten());
        txtNamsinh.setText("Ngay Sinh: "+sv.getNgaysinh());
        txtQuequan.setText("Que Quan: "+sv.getQuequan());
        txtNganhhoc.setText("Nganh Hoc: "+sv.getNganhhoc());
    }
}
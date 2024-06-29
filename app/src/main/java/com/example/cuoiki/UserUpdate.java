package com.example.cuoiki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;
import java.util.Map;

public class UserUpdate extends AppCompatActivity {
    String url ="http://192.168.1.41/QLSV/updateuser.php" ;
    EditText edtname ,edtemail ,edtquequan, edtnamsinh,edtnganhhoc,edtimg ;
    String txtname ,txtemail ,txtquequan, txtnamsinh,txtnganhhoc,txtimg ;
    Button btnupdate , btnchoseImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        anhxa();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(url);
            }
        });
    }

    private void updateUser(String url ) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG1", "onResponse:" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("TAG1", "volleyError: upda" + volleyError.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                String mssv = sharedPref.getString("key", "");
                txtquequan = sharedPref.getString("quequan","");
                txtnamsinh = sharedPref.getString("ngaysinh","");
                txtemail = sharedPref.getString("email","");
                txtname = sharedPref.getString("hoten","");
                txtnganhhoc = sharedPref.getString("nganhhoc","");
                /////////////////////////////////THUC HIEN KIEM TRA TRUOC KHI TRUYEN DI DU LIEU


                Map<String ,String> params = new HashMap<>();
                params.put("mssv",mssv);
                if(edtquequan.getText().toString().trim().isEmpty()){
                    params.put("quequan",txtquequan);
                }else {
                    params.put("quequan",edtquequan.getText().toString());
                }
                params.put("img","null");
                if(edtnamsinh.getText().toString().trim().isEmpty()){
                    params.put("ngaysinh",txtnamsinh);
                }else {
                    params.put("ngaysinh",edtnamsinh.getText().toString());
                }
                if(edtemail.getText().toString().trim().isEmpty()){
                    params.put("email",txtemail);
                }else {
                    params.put("email",edtemail.getText().toString());
                }
                if(edtnganhhoc.getText().toString().trim().isEmpty()){
                    params.put("nganhhoc",txtnganhhoc);
                }else {
                    params.put("nganhhoc",edtnganhhoc.getText().toString());
                }
                if(edtname.getText().toString().trim().isEmpty()){
                    params.put("hoten",txtname);
                }else {
                    params.put("hoten",edtname.getText().toString());
                }
                Log.i("TAG1", "getParams: "+ params.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void anhxa() {
        edtname =findViewById(R.id.txtname);
        edtemail =findViewById(R.id.txtemail);
        edtquequan =findViewById(R.id.txtquequan);
        edtnamsinh =findViewById(R.id.txtnamsinh);
        edtnganhhoc =findViewById(R.id.txtnganhoc);
        btnupdate = findViewById(R.id.update);
    }

}
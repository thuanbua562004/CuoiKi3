package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button btnConfirm ;
    TextView txtUsername , txtPassword ,txtRepassword;
    String url ="http://192.168.40.104/QLSV/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().toString().equals("") || txtPassword.getText().toString().equals("") || txtRepassword.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"Vui Long Nhap Day Du",Toast.LENGTH_LONG).show();
                }else{
                    if(txtPassword.getText().toString().equals(txtRepassword.getText().toString())){
                        register(url);
                    }else{
                        Toast.makeText(RegisterActivity.this,"Mat Khau Khong Trung khop",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
    public void register (String url){
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        Log.i("TAG", username);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(s.equals("thanhcong")){
                    startActivity(new Intent(RegisterActivity.this , MainActivity.class));
                    Toast.makeText(RegisterActivity.this,"Dang Ki Thanh Cong",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("TAG1", volleyError.toString());
                Toast.makeText(RegisterActivity.this,volleyError.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String> params = new HashMap<>();
                params.put("password",password);
                params.put("username",username);
                   return params ;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void anhxa(){
        btnConfirm = findViewById(R.id.btnConfirm);
        txtUsername =findViewById(R.id.username);
        txtPassword =findViewById(R.id.password);
        txtRepassword =findViewById(R.id.repassword);
    };
}
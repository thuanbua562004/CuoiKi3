package com.example.cuoiki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin, btnRegister;
    private static final String url = "http://192.168.1.41/QLSV/login.php";
    public ArrayList<SinhVien> arrayList;
    public static final String MyPREFERENCES = "mystore" ;
    public static final String mssvkey = "mssvkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        setContentView(R.layout.login);
        anhxa();
        xulisk();
    }

    private void anhxa() {
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnlogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void xulisk() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login( url);
            }
        });
    }

    public void login(String url) {
        arrayList.clear();
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            showErrorDialog("Vui Long Nhap Day Du Thong Tin");
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("thatbai")){
                        Log.i("TAG1", response.toString());
                        showErrorDialog("Khong Dung Tai Khoan Mat Khau");
                        return;
                    }else {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                SinhVien sv = new SinhVien(
                                  obj.getString("mssv")
                                );
                                arrayList.add(sv);
                            }
                            SinhVien sv = arrayList.get(0);
                            Log.i("TAG1", sv.getMssv().toString());
                            Log.i("TAG1", response.toString());
                            session(sv.getMssv().toString(), sv);
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("TAG1", error.toString());
                    showErrorDialog("Server error");
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
    public void session (String mssv, SinhVien sv){
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", mssv);
        editor.apply();
    }


    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}

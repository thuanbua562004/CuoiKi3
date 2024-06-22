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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
    private static final String url = "http://192.168.1.20/QLSV/login.php";
    private ArrayList<SinhVien> arrayList;
    public static final String MyPREFERENCES = "mystore" ;
    public static final String mssvkey = "mssvkey";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login( url);
            }
        });
    }

    private void login(String url) {
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public Context context;
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
                    SinhVien sv = arrayList.get(0);
                    Log.i("TAG1", sv.getMssv().toString());
                    session(sv.getMssv().toString());
                    startActivity(new Intent(MainActivity.this , home.class));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
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
    public void session (String mssv ){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.putString(mssvkey, mssv);
        editor.commit();
    }


    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}

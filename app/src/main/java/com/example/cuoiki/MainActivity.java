package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin, btnRegister;
    private static final String url = "http://192.168.1.19/QLSV/login.php";
    private ArrayList<SinhVien> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//        anhxa();
//        xulisk();
        arrayList = new ArrayList<>(); // Initialize the ArrayList
    }
//
//    private void anhxa() {
//        txtUsername = findViewById(R.id.username);
//        txtPassword = findViewById(R.id.password);
//        btnLogin = findViewById(R.id.btnlogin);
//        btnRegister = findViewById(R.id.btnRegister);
//    }
//
//    private void xulisk() {
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, register.class));
//            }
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login(url);
//            }
//        });
//    }
//
//    private void login(String url) {
//        String username = txtUsername.getText().toString().trim();
//        String password = txtPassword.getText().toString().trim();
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray jsonArray) {
//                arrayList.clear(); // Clear the list before adding new data
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    try {
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        arrayList.add(new SinhVien(
//                                object.getString("username"),
//                                object.getString("password"),
//                                object.getString("mssv"),
//                                object.getString("quequan"),
//                                object.getString("ngaysinh"),
//                                object.getString("nganhhoc"),
//                                object.getString("hoten")
//                        ));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.d("TAG1", "Array list size: " + arrayList.size());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("TAG1", "Error: " + volleyError.getMessage());
//                showErrorDialog("Lỗi kết nối hoặc server không phản hồi.");
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    private void showErrorDialog(String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Thông báo")
//                .setMessage(message)
//                .setPositiveButton("OK", null)
//                .show();
//    }
}

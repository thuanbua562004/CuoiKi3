package com.example.cuoiki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class user extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView mssvTextView;
    private TextView quequanTextView;
    private TextView ngaysinhTextView;
    private TextView nganhhocTextView;
    private TextView hotenTextView;
    String url = "http://192.168.40.107/QLSV/user.php"; // Thay đổi địa chỉ URL phù hợp với server của bạn
    public  String mssv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        anhxa();
        mssv =  getMssv();
        getThongTinSv(url ,mssv);
    }

    private void getThongTinSv(String url,String mssv) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG1", response.toString() +"user");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1", error.toString() );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Log.i("TAG1", "getMssv12: " +mssv);
                params.put("mssv",mssv);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public String getMssv (){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String savedData = sharedpreferences.getString("mssvkey", "");
        return savedData;
    }

    public void anhxa(){
        // Ánh xạ các TextView từ layout
        mssvTextView = findViewById(R.id.textView31);
        quequanTextView = findViewById(R.id.textView30);
        ngaysinhTextView = findViewById(R.id.textView29);
        nganhhocTextView = findViewById(R.id.textView28);
        hotenTextView = findViewById(R.id.textView27);
    }
}

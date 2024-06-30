package com.example.cuoiki;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class LichHocActivity extends AppCompatActivity {
    String url ="http://192.168.1.41/QLSV/lichhoc.php";
    public ListView listView ;
    public LichHocAdapter lichHocAdapter ;
    ArrayList<MonHoc> listHoc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_hoc);
        listView = findViewById(R.id.listlichhoc);
        lichHocAdapter = new LichHocAdapter(listHoc,LichHocActivity.this);
        listView.setAdapter(lichHocAdapter);
        getLichHoc(url);
    }

    private void getLichHoc(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                listHoc.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        MonHoc monHoc = new MonHoc(
                                object.getString("thoigian"),
                                object.getString("phong"),
                                object.getString("giaovien"),
                                object.getString("ngay"),
                                object.getString("name")
                        );
                        listHoc.add(monHoc);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    lichHocAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("TAG1", "onResponse: " + volleyError.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
}
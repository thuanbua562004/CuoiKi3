package com.example.cuoiki;

import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ArrayList<notication> arrayListNoti;
    private ListView listView;
    private notication_apdapter adapter;
    private String url = "http://192.168.1.25/QLSV/getnotification.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintruong);
        listView = findViewById(R.id.listview);
        arrayListNoti = new ArrayList<>();
        adapter = new notication_apdapter(arrayListNoti, this);
        listView.setAdapter(adapter);
        getNotification();
    }

    private void getNotification() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        arrayListNoti.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                arrayListNoti.add(new notication(
                                        object.getString("name"),
                                        object.getString("notification_info"),
                                        object.getString("notifi_ing"),
                                        object.getString("date_create")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                showErrorDialog("Error parsing JSON data: " + e.getMessage());
                            }
                        }
                        adapter.notifyDataSetChanged();
                        Log.i("TAG1", jsonArray.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG1", volleyError.toString());
                showErrorDialog("Network error: " + volleyError.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
        builder.setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}

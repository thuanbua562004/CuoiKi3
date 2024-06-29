package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ArrayList<Notification> arrayListNoti;
    private ListView listView;
    private NotificationAdapter adapter;
    private String url = "http://192.168.1.41/QLSV/getnotification.php";
    ImageButton news,user,home ,menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintruong);
        listView = findViewById(R.id.listview);
        arrayListNoti = new ArrayList<>();
        adapter = new NotificationAdapter(arrayListNoti, NotificationActivity.this);
        listView.setAdapter(adapter);
        getNotification();
        anhxa();
        menubar();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NotificationActivity.this, NotifidetailActivity.class);
                Notification notification = arrayListNoti.get(position);
                intent.putExtra("name",notification.getName().toString());
                intent.putExtra("info",notification.getNotication_info().toString());
                intent.putExtra("date",notification.getDate().toString());
                intent.putExtra("img",notification.getImg().toString());
                startActivity(intent);
            }
        });
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
                                arrayListNoti.add(new Notification(
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
    public void menubar(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotificationActivity.this , UserActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotificationActivity.this , MenuUser.class));
            }
        });

    }
    public void anhxa(){
        user = findViewById(R.id.user);
        home = findViewById(R.id.home);
        news  =findViewById(R.id.news);
        menu = findViewById(R.id.menu);

    }
}

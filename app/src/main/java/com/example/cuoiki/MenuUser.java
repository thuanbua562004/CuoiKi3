package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuUser extends AppCompatActivity {
    Button btnCapNhat , btnChiTietSv , btnDangXuat ;
    ImageButton news,user,home ,menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        anhxa();
        slsk();
        menubar();
    }

    private void slsk() {
        btnChiTietSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUser.this , UserActivity.class));
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUser.this , UserUpdate.class));
            }
        });
    }

    private void anhxa() {
        btnCapNhat =findViewById(R.id.capnhatsv);
        btnChiTietSv = findViewById(R.id.chitietsv);
        btnDangXuat = findViewById(R.id.dangxuat);
        user = findViewById(R.id.user);
        home = findViewById(R.id.home);
        news  =findViewById(R.id.news);
        menu = findViewById(R.id.menu);
    }
    public void menubar(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUser.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUser.this , UserActivity.class));
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUser.this , NotificationActivity.class));
            }
        });

    }
}
package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuUser extends AppCompatActivity {
    Button btnCapNhat , btnChiTietSv , btnDangXuat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        anhxa();
        slsk();
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
    }
}
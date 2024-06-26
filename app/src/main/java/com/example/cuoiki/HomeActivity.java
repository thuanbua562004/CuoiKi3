
package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    ImageButton imgthongbao  ,news,user,homme ,menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        anhxa();
        slsk();
    }
    public void slsk(){
        imgthongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , UserActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , MenuUser.class));
            }
        });

    }
    public void anhxa(){
        imgthongbao = findViewById(R.id.imgthongbao);
        user = findViewById(R.id.user);
        homme = findViewById(R.id.home);
        news  =findViewById(R.id.news);
        menu = findViewById(R.id.menu);

    }
}

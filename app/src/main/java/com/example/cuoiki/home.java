
package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity {
    ImageButton imgthongbao ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        anhxa();
        imgthongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


    }
    public void anhxa(){
        imgthongbao = findViewById(R.id.imgthongbao);
    }
}

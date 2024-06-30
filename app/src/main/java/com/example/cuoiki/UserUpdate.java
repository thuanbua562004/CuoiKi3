package com.example.cuoiki;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUpdate extends AppCompatActivity {
    String url ="http://192.168.1.41/QLSV/updateuser.php" ;
    EditText edtname ,edtemail ,edtquequan, edtnamsinh,edtnganhhoc ;
    String txtname ,txtemail ,txtquequan, txtnamsinh,txtnganhhoc,txtimg ;
    Button btnupdate ;
    ImageButton btnchoseImg;
    Bitmap bitmap ;
    private static final int REQUEST_CODE_PICK_IMAGE = 2;
    private static final int REQUEST_CODE_PERMISSION = 1;
    public String mssv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        mssv = sharedPref.getString("key", "");
        anhxa();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(url);
            }
        });
        btnchoseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Mở gallery để chọn ảnh
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Hiển thị ảnh đã chọn lên ImageView
            ImageView imageView = findViewById(R.id.imgacount);
            imageView.setImageURI(uri);

            /////////////////
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if(bitmap!=null){
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                final  String base64Imgae = Base64.encodeToString(bytes , Base64.DEFAULT);
                String url = "http://192.168.1.41/QLSV/uploadfile.php" ;
                RequestQueue requestQueue = Volley.newRequestQueue(UserUpdate.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("TAG1", "onErrorResponse: " + s.toString() );

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG1", "onErrorResponse: " + volleyError.toString() );
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>() ;
                        params.put("image",base64Imgae);
                        params.put("mssv",mssv);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }

        }
    }

    private void updateUser(String url ) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG1", "onResponse:" + response.toString());
                Toast.makeText(UserUpdate.this ,"Cap Nhat Thanh Cong",Toast.LENGTH_LONG).show();
                startActivity(new Intent(UserUpdate.this,HomeActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("TAG1", "volleyError: upda" + volleyError.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                 mssv = sharedPref.getString("key", "");
                txtquequan = sharedPref.getString("quequan","");
                txtnamsinh = sharedPref.getString("ngaysinh","");
                txtemail = sharedPref.getString("email","");
                txtname = sharedPref.getString("hoten","");
                txtnganhhoc = sharedPref.getString("nganhhoc","");
                txtimg = sharedPref.getString("img","");
                /////////////////////////////////THUC HIEN KIEM TRA TRUOC KHI TRUYEN DI DU LIEU


                Map<String ,String> params = new HashMap<>();
                params.put("mssv",mssv);
                if(edtquequan.getText().toString().trim().isEmpty()){
                    params.put("quequan",txtquequan);
                }else {
                    params.put("quequan",edtquequan.getText().toString());
                }
                params.put("img",txtimg);
                if(edtnamsinh.getText().toString().trim().isEmpty()){
                    params.put("ngaysinh",txtnamsinh);
                }else {
                    params.put("ngaysinh",edtnamsinh.getText().toString());
                }
                if(edtemail.getText().toString().trim().isEmpty()){
                    params.put("email",txtemail);
                }else {
                    params.put("email",edtemail.getText().toString());
                }
                if(edtnganhhoc.getText().toString().trim().isEmpty()){
                    params.put("nganhhoc",txtnganhhoc);
                }else {
                    params.put("nganhhoc",edtnganhhoc.getText().toString());
                }
                if(edtname.getText().toString().trim().isEmpty()){
                    params.put("hoten",txtname);
                }else {
                    params.put("hoten",edtname.getText().toString());
                }
                Log.i("TAG1", "getParams: "+ params.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void anhxa() {
        edtname =findViewById(R.id.txtname);
        edtemail =findViewById(R.id.txtemail);
        edtquequan =findViewById(R.id.txtquequan);
        edtnamsinh =findViewById(R.id.txtnamsinh);
        edtnganhhoc =findViewById(R.id.txtnganhoc);
        btnupdate = findViewById(R.id.update);
        btnchoseImg = findViewById(R.id.openpicture);
    }

}
package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FirstScreen extends AppCompatActivity {
    private Button manager, user;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        manager = findViewById(R.id.manager);
        user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        manager.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstScreen.this, Login1Activity.class);
                startActivity(intent);
            }
        });
    }
}

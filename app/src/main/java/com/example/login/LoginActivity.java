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

public class LoginActivity extends AppCompatActivity{
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private static final String TAG = "RentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어 있는 값을 get해 온다.
                final String userID = et_id.getText().toString();
                final String userPass = et_pass.getText().toString();
                //
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //Log.d(TAG, "야뭐야");
                            //php의 success를 보고 성공했는지 확인
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //로그인등록에 성공한 경우
                                String userName = jsonObject.getString("userName");
                                String userNumber = jsonObject.getString("userNumber");

                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, RentActivity.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userNumber", userNumber);
                                startActivity(intent);
                            } else { // 로그인등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(loginRequest);
                        //Log.d(TAG, "야뭐야");
                    }
                }).start();
            }
        });
    }
}
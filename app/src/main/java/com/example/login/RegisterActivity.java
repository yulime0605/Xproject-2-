package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_name, et_number, et_email;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티 시작시 처음으로 설정되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디값찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        et_email = findViewById(R.id.et_email);

        //회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edittext에 현재입력되어잇는값 가져옴
                final String userID= et_id.getText().toString();
                final String userPass= et_pass.getText().toString();
                final String userName= et_name.getText().toString();
                final String userNumber= et_number.getText().toString();
                final String userEmail= et_email.getText().toString();

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //json object 이용
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //php의 success를 보고 성공했는지 확인
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                        RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userNumber, userEmail, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registerRequest);
                    }
                }).start();

            }
        });
    }
}
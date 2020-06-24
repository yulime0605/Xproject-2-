package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TimerActivity extends AppCompatActivity {
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 1800000;
    private boolean timerRunning;
    private Button usingStop;
    private String userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        countdownText=(TextView)findViewById(R.id.countdown_text);
        startStop();
        usingStop = (Button)findViewById(R.id.usingStop);
        Intent intent = getIntent();
        userNumber = intent.getStringExtra("userNumber");
        System.out.println("test1"+userNumber);

        usingStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //반납 버튼 눌렀을 때
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("test" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            //Log.d(TAG, "야뭐야");
                            //php의 success를 보고 성공했는지 확인
                            boolean return_bike = jsonObject.getBoolean("return_bike");
//                            if (return_bike) {
//                                timerRunning = false;
//                                //showLogout();
//                                //Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
//                            } else {
//                                //return;
//                            }
                            Toast.makeText(TimerActivity.this, "로그아웃 되었습니다. 다시 대여하세요.", Toast.LENGTH_LONG).show();
                            TimerActivity.this.finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimerRequest timerRequest = new TimerRequest(userNumber, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(TimerActivity.this);
                        queue.add(timerRequest);
                        //Log.d(TAG, "야뭐야");
                    }
                }).start();
            }
        });
    }

    void showLogout()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("로그아웃 ");
        builder.setPositiveButton("로그아웃",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(TimerActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    }
                });
        builder.setNegativeButton("앱 나가기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"또 이용해주세요.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
        builder.show();
    }
    public void startStop(){
        if (timerRunning) {
            System.out.println("바로 stop으로 넘어가짐");
            stopTimer();
        } else {
            System.out.println("start 확인");
            startTimer();
        }
    }
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long timeLeftInMilliseconds) {
//                timeLeftInMilliseconds = 1;
//                updateTimer();
                int seconds = (int) (timeLeftInMilliseconds / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                countdownText.setText("TIME : " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                System.out.println("onFinsih");
            }

        }.start();
        System.out.println("start()함수이후");
        timerRunning = true;

    }
    public  void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }
    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        String timeLeftText = "" + minutes + ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);

    }
}
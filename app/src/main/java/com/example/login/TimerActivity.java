package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 1800000;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        countdownText=findViewById(R.id.countdownText);
        startStop();
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
                System.out.println("onTick실행중");
//                timeLeftInMilliseconds = 1;
//                updateTimer();
                int seconds = (int) (timeLeftInMilliseconds / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                countdownText.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
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

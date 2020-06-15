package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class RentActivity extends AppCompatActivity implements View.OnClickListener{
    Button scanBtn;
    private static final String TAG = "RentActivity";
    String userName, userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userNumber = intent.getStringExtra("userNumber");

    }

    @Override
    public void onClick(View view) {
        scanCode();
    }
    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                final String bikeNumber = result.getContents();
                Log.d(TAG,"자전거" + bikeNumber);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d(TAG,"야뭔데");

                            JSONObject jsonObject = new JSONObject(response);
                            //php의 success를 보고 성공했는지 확인
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //자전거등록에 성공한 경우
                                Log.d(TAG,"야뭐야");
                                Toast.makeText(getApplicationContext(), "자전거" + bikeNumber +"잠금이 헤제되었습니다.", Toast.LENGTH_SHORT).show();
                            } else { // 자전거등록에 실패한 경우
                                Log.d(TAG,"야어디야");
                                Toast.makeText(getApplicationContext(), "다른 사용자가 사용중인 자전거입니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RentRequest rentRequest = new RentRequest(userName, userNumber, bikeNumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RentActivity.this);
                queue.add(rentRequest);


            } else {
                Log.d(TAG,"!!!!!!!!!!!");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("no result");
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

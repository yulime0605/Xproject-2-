package com.example.login;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.*;

public class TimerRequest extends StringRequest {
    //서버 url 설정(php 파일 연동)
    final static private String URL = "http://xproject2.dothome.co.kr/unlock.php";
    private Map<String, String> map;

    public TimerRequest(String userNumber, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userNumber", userNumber);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

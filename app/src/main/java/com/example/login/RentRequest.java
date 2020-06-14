package com.example.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import java.util.HashMap;


public class RentRequest extends StringRequest {
    //서버 url 설정(php 파일 연동)
    final static private String URL = "http://xproject2.dothome.co.kr/BikeResult.php";
    private Map<String, String> map;

    public RegisterRequest(String bikeNumber, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("bikeNumber",);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}


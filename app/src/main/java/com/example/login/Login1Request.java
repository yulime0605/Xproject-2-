package com.example.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login1Request extends StringRequest {
    //서버 url 설정(php 파일 연동)
    final static private String URL = "http://xproject2.dothome.co.kr/Login1.php";
    private Map<String, String> map;

    public Login1Request(String managerID, String managerPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("managerID", managerID);
        map.put("managerPassword", managerPassword);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
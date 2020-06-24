package com.example.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import java.util.HashMap;

public class ManagerRgRequest extends StringRequest {
    //서버 url 설정(php 파일 연동)
    final static private String URL = "http://xproject2.dothome.co.kr/managerRegister.php";
    private Map<String, String> map;

    public ManagerRgRequest(String managerID, String managerPassword, String managerName, String managerNumber, String managerEmail, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("managerID", managerID);
        map.put("managerPassword", managerPassword);
        map.put("managerName", managerName);
        map.put("managerNumber", managerNumber);
        map.put("managerEmail", managerEmail);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
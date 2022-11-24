package com.example.realnona2;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ptCreateRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://nonaphp.dothome.co.kr/ptCreate.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public ptCreateRequest(String ptShop, String ptTitle, String ptCategory, String ptDelivery, String ptPeople, String ptDate, String ptTime, String ptChat, String ptPlace, String ptPay, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("ptShop", ptShop);
        map.put("ptTitle", ptTitle);
        map.put("ptCategory", ptCategory);
        map.put("ptDelivery", ptDelivery);
        map.put("ptPeople", ptPeople);
        map.put("ptDate", ptDate);
        map.put("ptTime", ptTime);
        map.put("ptChat", ptChat);
        map.put("ptPlace", ptPlace);
        map.put("ptPay", ptPay);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
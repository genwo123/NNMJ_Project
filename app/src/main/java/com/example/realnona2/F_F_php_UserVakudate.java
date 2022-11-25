package com.example.realnona2;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class F_F_php_UserVakudate extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://nonaphp.dothome.co.kr/UserValidate1.php";
    private final Map<String, String> map;

    public F_F_php_UserVakudate(String UserNum, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("UserNum", UserNum);
    }

    @Override
    protected Map<String, String> getParams()throws AuthFailureError {
        return map;
    }
}
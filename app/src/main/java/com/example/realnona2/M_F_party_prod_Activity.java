package com.example.realnona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;

import org.json.JSONException;
import org.json.JSONObject;


public class M_F_party_prod_Activity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private static NaverMap naverMap;

    EditText M_V_pp_store, M_V_pp_name, M_V_pp_openchat; // 가게명, 파티명, 채팅방 링크
    RadioButton M_V_pp_pf_radio_bm, M_V_pp_pf_radio_yo, M_V_pp_pf_radio_cu, M_V_pp_pay1_gather, M_V_pp_pay2_meet, M_V_pp_pay3_first; // 플랫폼, 결제수단
    Button M_V_pp_create_btn; // 생성버튼
    Button M_V_pp_pn_1, M_V_pp_pn_2, M_V_pp_pn_3, M_V_pp_pn_4, M_V_pp_pn_5, M_V_pp_pn_6; // 인원
    Button M_V_pp_date_today, M_V_pp_date_tomorrow; // 오늘, 내일
    TimePicker M_V_pp_date_TimePicker; // 시간
    TextView M_V_pp_order; // 선택내역 표시

    String M_V_pp_pt_store = "가게명";
    String options1 = "배달업체";
    String options2 = "인원";
    String options3 = "주문일";
    String options4 = "주문시간";
    String options5 = "위치";
    String options6 = "결제방법";
    String M_V_pp_pt_title = "파티명";
    String M_V_pp_pt_link = "링크";
    String M_V_pp_pt_category = "카테고리";
    String[] category = {"카테고리", "고기", "도시락", "양식", "일식", "중식", "치킨", "카페", "피자", "패스트푸드", "한식"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_f_activity_party_prod);

        // 상세정보
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        mapView = (MapView) findViewById(R.id.M_V_pp_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        M_V_pp_store = findViewById(R.id.M_V_pp_store);
        M_V_pp_name = findViewById(R.id.M_V_pp_name);
        M_V_pp_openchat = findViewById(R.id.M_V_pp_openchat);
        M_V_pp_pf_radio_bm = findViewById(R.id.M_V_pp_pf_radio_bm);
        M_V_pp_pf_radio_yo = findViewById(R.id.M_V_pp_pf_radio_yo);
        M_V_pp_pf_radio_cu = findViewById(R.id.M_V_pp_pf_radio_cu);
        M_V_pp_pay1_gather = findViewById(R.id.M_V_pp_pay1_gather);
        M_V_pp_pay2_meet = findViewById(R.id.M_V_pp_pay2_meet);
        M_V_pp_pay3_first = findViewById(R.id.M_V_pp_pay3_first);
        M_V_pp_create_btn = findViewById(R.id.M_V_pp_create_btn);
        M_V_pp_pn_1 = findViewById(R.id.M_V_pp_pn_1);
        M_V_pp_pn_2 = findViewById(R.id.M_V_pp_pn_2);
        M_V_pp_pn_3 = findViewById(R.id.M_V_pp_pn_3);
        M_V_pp_pn_4 = findViewById(R.id.M_V_pp_pn_4);
        M_V_pp_pn_5 = findViewById(R.id.M_V_pp_pn_5);
        M_V_pp_pn_6 = findViewById(R.id.M_V_pp_pn_6);
        M_V_pp_date_today = findViewById(R.id.M_V_pp_date_today);
        M_V_pp_date_tomorrow = findViewById(R.id.M_V_pp_date_tomorrow);
        M_V_pp_date_TimePicker = findViewById(R.id.M_V_pp_date_TimePicker);
        M_V_pp_order = findViewById(R.id.M_V_pp_order);
        Spinner category = findViewById(R.id.category);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, this.category
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 선택되면
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                M_V_pp_pt_category = (M_F_party_prod_Activity.this.category[position]);
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }

            // 아무것도 선택되지 않은 상태
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        M_V_pp_store.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                M_V_pp_pt_store = M_V_pp_store.getText().toString();
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                M_V_pp_pt_title = M_V_pp_name.getText().toString();
            }
        });

        M_V_pp_openchat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                M_V_pp_pt_link = M_V_pp_openchat.getText().toString();
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pf_radio_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options1 = "배달의민족";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pf_radio_yo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options1 = "요기요";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pf_radio_cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options1 = "쿠팡이츠";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "1명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "2명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "3명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "4명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "5명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options2 = "6명";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_date_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options3 = "오늘";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_date_tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options3 = "내일";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_date_TimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                options4 = hourOfDay + "시" + minute + "분";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pay1_gather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options6 = "모여서 후결제";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_pay2_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options6 = "파티장 선결제";
                M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            }
        });

        M_V_pp_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (M_V_pp_pt_store == "가게명" || M_V_pp_pt_store.length() == 0) {
                    Toast.makeText(getApplicationContext(), "가게명을 확인해주세요.", Toast.LENGTH_SHORT).show();
                } else if (M_V_pp_pt_title == "파티명" || M_V_pp_pt_title.length() == 0) {
                    Toast.makeText(getApplicationContext(), "파티명을 기입해주세요.", Toast.LENGTH_SHORT).show();
                } else if (M_V_pp_pt_category == "카테고리") {
                    Toast.makeText(getApplicationContext(), "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options1 == "배달업체") {
                    Toast.makeText(getApplicationContext(), "배달업체를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options2 == "인원") {
                    Toast.makeText(getApplicationContext(), "인원을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options3 == "주문일") {
                    Toast.makeText(getApplicationContext(), "오늘 또는 내일을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options4 == "주문시간") {
                    Toast.makeText(getApplicationContext(), "주문시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (M_V_pp_pt_link == "링크" || M_V_pp_pt_link.length() == 0) {
                    Toast.makeText(getApplicationContext(), "오픈 채팅방 링크를 기입해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options5 == "위치") {
                    Toast.makeText(getApplicationContext(), "만날 장소를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (options6 == "결제방법") {
                    Toast.makeText(getApplicationContext(), "결제방식을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    String ptShop = M_V_pp_pt_store;
                    String ptTitle = M_V_pp_pt_title;
                    String ptCategory = M_V_pp_pt_category;
                    String ptDelivery = options1;
                    String ptPeople = options2;
                    String ptDate = options3;
                    String ptTime = options4;
                    String ptChat = M_V_pp_pt_link;
                    String ptPlace = options5;
                    String ptPay = options6;

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");

                                //성공시
                                if (success) {

                                    Toast.makeText(getApplicationContext(), "파티가 생성되었습니다.", Toast.LENGTH_SHORT).show();
                                    finish();


                                    //실패시
                                } else {
                                    Toast.makeText(getApplicationContext(), "파티생성에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    ptCreateRequest ptCreateRequest = new ptCreateRequest(ptShop, ptTitle, ptCategory, ptDelivery, ptPeople, ptDate, ptTime, ptChat, ptPlace, ptPay, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(M_F_party_prod_Activity.this);
                    queue.add(ptCreateRequest);
                }
            }

        });



    }

    // 네이버맵
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(35.164841, 128.098965),
                15
        );
        naverMap.setCameraPosition(cameraPosition);

        naverMap.setMinZoom(15.0);
        naverMap.setMaxZoom(16.0);

        Marker marker1 = new Marker();
        Marker marker2 = new Marker();
        Marker marker3 = new Marker();
        Marker marker4 = new Marker();

        marker1.setPosition(new LatLng(35.1655, 128.0988));
        marker2.setPosition(new LatLng(35.165111, 128.100278));
        marker3.setPosition(new LatLng(35.163937, 128.098977));
        marker4.setPosition(new LatLng(35.165382, 128.096904));

        marker1.setCaptionText("산학협동관");
        marker2.setCaptionText("미래관");
        marker3.setCaptionText("본관건물");
        marker4.setCaptionText("식당");

        marker1.setMap(naverMap);
        marker2.setMap(naverMap);
        marker3.setMap(naverMap);
        marker4.setMap(naverMap);

        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "여기서 먹어요!";
            }
        });

        // 지도를 클릭하면 정보 창을 닫음
        naverMap.setOnMapClickListener((coord, point) -> {
            infoWindow.close();
        });

        // 마커를 클릭하면:
        Overlay.OnClickListener listener = overlay -> {
            Marker marker = (Marker)overlay;

            if (marker.getInfoWindow() == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow.open(marker);
            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow.close();
            }

            return true;
        };

        marker1.setOnClickListener(listener);
        marker2.setOnClickListener(listener);
        marker3.setOnClickListener(listener);
        marker4.setOnClickListener(listener);

        marker1.setOnClickListener(overlay -> {
            infoWindow.open(marker1);
            options5 = "산학협동관";
            M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            return true;
        });

        marker2.setOnClickListener(overlay -> {
            infoWindow.open(marker2);
            options5 = "미래관";
            M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            return true;
        });

        marker3.setOnClickListener(overlay -> {
            infoWindow.open(marker3);
            options5 = "본관건물";
            M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            return true;
        });

        marker4.setOnClickListener(overlay -> {
            infoWindow.open(marker4);
            options5 = "식당";
            M_V_pp_order.setText(M_V_pp_pt_store + "ㅣ"  + M_V_pp_pt_category + "ㅣ" + options1 + "ㅣ" + options2 + "\n" + options3 + "ㅣ" + options4 + "ㅣ" + options5 + "ㅣ" + options6);
            return true;
        });


    }

}

package com.example.realnona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;


import com.example.realnona2.fragment.FragmentShare;
import com.example.realnona2.fragment.FragmentWith;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class M_F_Borad_share_Activity extends AppCompatActivity {



    private ListView M_V_board_share_list;
    String mTitle[]={"브리또","상호명","상호명","상호명","상호명","상호명","상호명","상호명","상호명"};//listview에 title부분 설정
    String mDescription[]={"2시에 브리또 드실 분","글 내용","글 내용","글 내용","글 내용","글 내용","글 내용","글 내용","글 내용"};//listview에 설명부분
    int images[]={R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food,R.drawable.ic_food};
    //listview에 들어가는 사진

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_f_borad_share_activity);


        M_V_board_share_list =(ListView)findViewById(R.id.M_V_board_shear_list);

        MyAdapter adapter=new MyAdapter(this,mTitle,mDescription,images);
        M_V_board_share_list.setAdapter(adapter);//리스트에 어뎁터 설정


        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[],String description[],int imgs[]){
            super(c,R.layout.l_f_activity_board_sample,R.id.textView1,title);
            this.context=c;
            this.rTitle=title;
            this.rDescription=description;
            this.rImgs=imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //앞에서 만든 row xml파일을 view 객체로 만들기 위해서는 layoutInflater를 이용
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View board=layoutInflater.inflate(R.layout.l_f_activity_board_sample,parent,false);

            ImageView images=board.findViewById(R.id.image);
            TextView myTitle=board.findViewById(R.id.textView1);
            TextView myDescription=board.findViewById(R.id.textView2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return board;//앞에서 만든 xml 파일..
        }
    }





}
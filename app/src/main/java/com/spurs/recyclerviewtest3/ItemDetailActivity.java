package com.spurs.recyclerviewtest3;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemDetailActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //이 엑티비티를 실행시킨 Intent객체를 얻어오기..
        Intent intent=getIntent();

        //이 Intent객체에게 Extra데이터들을 얻어오기..
        String title = intent.getStringExtra("Title");
        int imgId = intent.getIntExtra("Img",0);

        //제목줄 글씨변경
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(title);

        //이미지뷰 설정
        img=(ImageView)findViewById(R.id.img);
        Glide.with(this).load(imgId).into(img);

        //img객체에게 Transition의 pair를 위한 이름 부여..
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img.setTransitionName("IMG");
        }

    }
    public void clickimg(View v){
        supportFinishAfterTransition();
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }else {
            finish();
        }*/
    }
}

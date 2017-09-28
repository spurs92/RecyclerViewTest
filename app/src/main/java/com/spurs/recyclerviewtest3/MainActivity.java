package com.spurs.recyclerviewtest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Item> items=new ArrayList<>();

    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);

        items.add(new Item(R.drawable.one_ace,R.drawable.gametitle_01,"ace"));
        items.add(new Item(R.drawable.one_chopa,R.drawable.gametitle_02,"chopa"));
        items.add(new Item(R.drawable.one_nicorobin,R.drawable.gametitle_03,"nicorobin"));
        items.add(new Item(R.drawable.one_sandi,R.drawable.gametitle_04,"sandi"));
        items.add(new Item(R.drawable.one_shanks,R.drawable.gametitle_05,"shanks"));
        items.add(new Item(R.drawable.one_usoup,R.drawable.gametitle_06,"usoup"));
        items.add(new Item(R.drawable.one_zoro,R.drawable.gametitle_07,"zoro"));
        items.add(new Item(R.drawable.one_ace,R.drawable.gametitle_01,"ace"));
        items.add(new Item(R.drawable.one_chopa,R.drawable.gametitle_02,"chopa"));
        items.add(new Item(R.drawable.one_nicorobin,R.drawable.gametitle_03,"nicorobin"));
        items.add(new Item(R.drawable.one_sandi,R.drawable.gametitle_04,"sandi"));
        items.add(new Item(R.drawable.one_shanks,R.drawable.gametitle_05,"shanks"));
        items.add(new Item(R.drawable.one_usoup,R.drawable.gametitle_06,"usoup"));
        items.add(new Item(R.drawable.one_zoro,R.drawable.gametitle_07,"zoro"));

        recyclerAdapter=new RecyclerAdapter(items,this);
        recyclerView.setAdapter(recyclerAdapter);

        //recyclerView에 ItemDecoration추가하기..
        DividerItemDecoration deco=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(deco);
        //deco.setDrawable(getResources().getDrawable(R.drawable.one_zoro)); //라인 꾸미기

        GridLayoutManager layoutManager=(GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position==0)return 2;
                else return 1;
            }
        });

        //recyclerView에 ItemAnimation 객체를 설저하기..
        //아이템이 추가,삭제,위치이동 같은 작업시에 애니메이션을 부여..
        MyItemAnimator animator=new MyItemAnimator();
        recyclerView.setItemAnimator(animator);
    }

    public void clickAdd(View v){
        items.add(0,new Item(R.drawable.one_ace,R.drawable.gametitle_02,"NEW"));
        recyclerAdapter.notifyItemInserted(0);
    }

    public void clickDel(View v){
        if(items.size()==0) return;
        items.remove(0);
        recyclerAdapter.notifyItemRemoved(0);
    }
}

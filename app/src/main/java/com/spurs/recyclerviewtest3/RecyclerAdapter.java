package com.spurs.recyclerviewtest3;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by alfo06-11 on 2017-06-13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    ArrayList<Item> items;
    Context context;

    public RecyclerAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(position==0) viewType=10;
        else viewType=0;
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //positon이 0번이면 recycler_header.xml 모양의 view 객체 생성
        if(viewType==10){
            View header=LayoutInflater.from(context).inflate(R.layout.recycler_header,parent,false);
            HeaderVH holder=new HeaderVH(header);

            return holder;

        }else {
            //나머지는 아래모양
            View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);

            ViewHolder holder=new ViewHolder(itemView);

            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position==0){//헤더뷰냐?
            return;
        }

//        holder.imgIcon.setImageResource(items.get(position).imgIcon);
//        holder.img.setImageResource(items.get(position).imgId);

        Glide.with(context).load(items.get(position-1).imgIcon).into(((ViewHolder)holder).imgIcon);
        Glide.with(context).load(items.get(position-1).imgId).into(((ViewHolder)holder).img);

        ((ViewHolder)holder).tvName.setText(items.get(position-1).name);

        //추가적으로 애니메이션
        Animation ani= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        holder.itemView.setAnimation(ani);

    }

    @Override
    public int getItemCount() {
        return items.size()+1;
    }

    class HeaderVH extends RecyclerView.ViewHolder{

        ImageView img;

        public HeaderVH(View itemView) {
            super(itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgIcon;
        TextView tvName;
        ImageView img;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;

            imgIcon=(CircleImageView) itemView.findViewById(R.id.img_icon);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            img=(ImageView)itemView.findViewById(R.id.img);

            //아이콘 이미지를 눌렀을때..
            imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "icon click : "+items.get(getLayoutPosition()).name, Toast.LENGTH_SHORT).show();
                }
            });

            //아이템을 눌렀을때..
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "itemview click : "+getLayoutPosition(), Toast.LENGTH_SHORT).show();

                    //아이템에 대한 상세정보를 보여주는 새로운 Activity를 실행..
                    //새로운 엑티비티에 전달하고자 하는 데이터
                    String title=items.get(getLayoutPosition()-1).name;
                    int imgId=items.get(getLayoutPosition()-1).imgId;

                    Intent intent=new Intent(context,ItemDetailActivity.class);

                    //activity에 보내기
                    intent.putExtra("Title",title);
                    intent.putExtra("Img",imgId);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation((Activity) context,new Pair<View, String>(img,"IMG"));
                        context.startActivity(intent,activityOptions.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

/*                    context.startActivity(intent);

                    //Activity 전환시에 애니메이션..
                    ((MainActivity)context).overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);*/


                }
            });

        }
    }
}

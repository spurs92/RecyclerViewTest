package com.spurs.recyclerviewtest3;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by alfo06-11 on 2017-06-14.
 */

public class MyItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {

        //오른쪽으로 슬라이드 아웃 되는 애니메이션 적용
        Animation ani= AnimationUtils.loadAnimation(holder.itemView.getContext(),android.R.anim.slide_out_right);
        holder.itemView.setAnimation(ani);

        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {

        //왼쪽에서 슬라이드 인 되는 애니메이션 적용
        Animation ani= AnimationUtils.loadAnimation(holder.itemView.getContext(),android.R.anim.slide_in_left);
        holder.itemView.setAnimation(ani);

        return super.animateAdd(holder);
    }
}

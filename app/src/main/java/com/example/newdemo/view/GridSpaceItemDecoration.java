package com.example.newdemo.view;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.view
 * 文件名： GridSpaceItemDecoration
 * 创建者：hanhehuann
 * 创建时间：2020-06-22 15:59
 * 描述：TODO
 */

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public GridSpaceItemDecoration(int space,RecyclerView parent){
        this.mSpace = space;
        getRecyclerViewOffsets(parent);
    }

    /**
     *
     * @param outRect item的矩形边界
     * @param view itemview
     * @param parent RecyclerView
     * @param state RecyclerView的状态
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mSpace;

        //判断item是不是每一行第一个item
        /*if (parent.getChildLayoutPosition(view) % 3 == 0){
            outRect.left = 0;
        }*/





    }

    private void getRecyclerViewOffsets(RecyclerView parent){
        //  view margin
        //  margin为正，则view 会距离边界产生一个距离
        //  margin为负，则view 会超出边界产生一个距离

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }


}

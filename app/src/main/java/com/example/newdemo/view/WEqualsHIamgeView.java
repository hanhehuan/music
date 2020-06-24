package com.example.newdemo.view;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.view
 * 文件名： WEqualsHIamgeView
 * 创建者：hanhehuann
 * 创建时间：2020-06-22 14:52
 * 描述：TODO
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class WEqualsHIamgeView extends AppCompatImageView {

    public WEqualsHIamgeView(Context context) {
        super(context);
    }

    public WEqualsHIamgeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHIamgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        //获取view宽度
        //int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取view模式
        //match_parent,wrap_content,具体dp
        //int mode = MeasureSpec.getMode(widthMeasureSpec);
        //int height = MeasureSpec.getSize(heightMeasureSpec);

    }
}

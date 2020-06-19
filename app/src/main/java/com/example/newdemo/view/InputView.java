package com.example.newdemo.view;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.view
 * 文件名： InputView
 * 创建者：hanhehuann
 * 创建时间：2020-06-16 15:00
 * 描述：TODO
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.newdemo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 1,input_icon:输入框前面的图标
 * 2,input_hint:输入框的提示内容
 * 3,is_password:输入框的内容是否需要以密文的形式展示
 */
public class InputView extends FrameLayout {

    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View mView;
    private ImageView mIcon;
    private EditText mEdInput;

    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        if (attrs == null)return;
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon,R.mipmap.logo);
        inputHint =  typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);
        typedArray.recycle();//回收资源
        //绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view,this,false);
        mIcon = mView.findViewById(R.id.iv_icon);
        mEdInput = mView.findViewById(R.id.et_input);
        //布局关联属性
        mIcon.setImageResource(inputIcon);
        mEdInput.setHint(inputHint);
        mEdInput.setInputType(isPassword? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_CLASS_PHONE);

        addView(mView);
    }

    /**
     *
     */
    public String getInputStr(){
        return mEdInput.getText().toString().trim();
    }
}

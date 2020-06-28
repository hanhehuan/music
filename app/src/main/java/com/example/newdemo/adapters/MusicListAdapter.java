package com.example.newdemo.adapters;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.adapters
 * 文件名： MusicListAdapter
 * 创建者：hanhehuann
 * 创建时间：2020-06-22 16:41
 * 描述：TODO
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.activity.PlayMusicActivity;
import com.example.newdemo.model.MusicModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContent;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcarHeight;
    private List<MusicModel> modelList;

    public MusicListAdapter(Context context,RecyclerView recyclerView,List<MusicModel> list){
        mContent = context;
        mRv = recyclerView;
        modelList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContent).inflate(R.layout.item_list_music,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();

        MusicModel musicModel = modelList.get(position);
        //"http://res.lgdsunday.club/poster-1.png"
        Glide.with(mContent)
                .load(musicModel.getPoster())
                .into(holder.ivIcon);

        holder.mName.setText(musicModel.getName());
        holder.mAuthor.setText(musicModel.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContent, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.MUSIC_ID,musicModel.getMusicId());
                mContent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    /**
     * 1、获取itemview的高度
     * 2、itemview的数量
     * 3、使用乘法求高度
     */
    private void setRecyclerViewHeight(){

        if (isCalcarHeight || mRv == null) return;

        isCalcarHeight = true;

        //获取itemview高度
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取itemview的数量
        int count = getItemCount();
        int recyclerHeight = layoutParams.height * count;
        //设置高度
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        layoutParams1.height = recyclerHeight;
        mRv.setLayoutParams(layoutParams1);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        View itemView;
        TextView mName,mAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            mAuthor = itemView.findViewById(R.id.tv_author);
            mName = itemView.findViewById(R.id.tv_name);
        }
    }
}

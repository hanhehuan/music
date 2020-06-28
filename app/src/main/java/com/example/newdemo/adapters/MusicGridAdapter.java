package com.example.newdemo.adapters;
/*
 * 项目名： newdemo
 * 包名： com.example.newdemo.adapters
 * 文件名： MusicGridAdapter
 * 创建者：hanhehuann
 * 创建时间：2020-06-22 13:53
 * 描述：TODO
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.activity.AlbumListActivity;
import com.example.newdemo.model.AlbumModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder>{

    private Context mcontext;
    private List<AlbumModel> mDataSource;

    public MusicGridAdapter(Context context,List<AlbumModel> mDataSource){
        this.mcontext = context;
        mDataSource = mDataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_grid_music,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AlbumModel albumModel = mDataSource.get(position);


        Glide.with(mcontext)
                .load("http://res.lgdsunday.club/poster-1.png")
                .into(holder.ivIcon);

        holder.mPlayNum.setText(albumModel.getPlayNum());
        holder.mTvName.setText(albumModel.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, AlbumListActivity.class);
                intent.putExtra(AlbumListActivity.ALBUM_ID,albumModel.getAlbumId());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        View itemView;
        TextView mPlayNum,mTvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            mPlayNum = itemView.findViewById(R.id.tv_play_num);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}

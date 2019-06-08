package com.example.haikou.Util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haikou.Datas.Scene;
import com.example.haikou.R;
import com.example.haikou.Interface.SceneDetail;

import java.util.List;

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder>{

    private Context mContext;
    private List<Scene> mSceneList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView sceneImage;
        TextView sceneName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            sceneImage = (ImageView)view.findViewById(R.id.scene_image);
            sceneName = (TextView) view.findViewById(R.id.scene_name);
        }
    }

    public SceneAdapter(List<Scene> sceneList){
        mSceneList = sceneList;
    }

    @Override
    public SceneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_scene,parent,false);

        final SceneAdapter.ViewHolder holder = new SceneAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Scene scene = mSceneList.get(position);
                Intent intent = new Intent(mContext, SceneDetail.class);
                intent.putExtra(SceneDetail.SCENE_NAME,scene.getName());
                intent.putExtra(SceneDetail.SCENE_IMAGE_ID,scene.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(SceneAdapter.ViewHolder holder, int position){
        Scene scene = mSceneList.get(position);
        holder.sceneName.setText(scene.getName());

        Glide.with(mContext).load(scene.getImageId()).into(holder.sceneImage);
    }

    @Override
    public int getItemCount(){
        return mSceneList.size();
    }
}

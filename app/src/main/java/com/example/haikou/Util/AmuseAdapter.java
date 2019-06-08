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
import com.example.haikou.Interface.AmuseDetail;
import com.example.haikou.Datas.Amuse;
import com.example.haikou.R;

import java.util.List;

public class AmuseAdapter extends RecyclerView.Adapter<AmuseAdapter.ViewHolder> {

    private Context mContext;
    private List<Amuse> mAmuseList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView amuseImage;
        TextView amuseName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            amuseImage = (ImageView)view.findViewById(R.id.amuse_image);
            amuseName = (TextView) view.findViewById(R.id.amuse_name);
        }
    }

    public AmuseAdapter(List<Amuse> amuseList){
        mAmuseList = amuseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_amuse,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Amuse amuse = mAmuseList.get(position);
                Intent intent = new Intent(mContext, AmuseDetail.class);
                intent.putExtra(AmuseDetail.AMUSE_NAME,amuse.getName());
                intent.putExtra(AmuseDetail.AMUSE_IMAGE_ID,amuse.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Amuse amuse = mAmuseList.get(position);
        holder.amuseName.setText(amuse.getName());

        Glide.with(mContext).load(amuse.getImageId()).into(holder.amuseImage);
    }

    @Override
    public int getItemCount(){
        return mAmuseList.size();
    }
}

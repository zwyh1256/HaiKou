package com.example.haikou.Util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.haikou.Interface.AmuseMain;
import com.example.haikou.Interface.ApartmentMain;
import com.example.haikou.Interface.FoodMain;
import com.example.haikou.Interface.HospitalMain;
import com.example.haikou.Interface.SceneMain;
import com.example.haikou.Interface.ShopMain;
import com.example.haikou.R;
import com.example.haikou.Datas.Sort;

import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;

    private List<Sort> mSortList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView sortImage;
        TextView sortName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            sortImage= (ImageView) view.findViewById(R.id.sort_image);
            sortName = (TextView) view.findViewById(R.id.sort_name);
        }
    }

    public MainAdapter(List<Sort> sortList) {
        mSortList = sortList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sort,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                Sort sort = mSortList.get(position);

                Intent intent;

                int nameId = sort.getSortId();
                switch (nameId){
                    case 1:
                        intent = new Intent(mContext, SceneMain.class);
                        mContext.startActivity(intent);
                        break;

                        case 2:
                        intent = new Intent(mContext, FoodMain.class);
                        mContext.startActivity(intent);
                        break;

                        case 3:
                        intent = new Intent(mContext, HospitalMain.class);
                        mContext.startActivity(intent);
                        break;

                        case 4:
                        intent = new Intent(mContext, ShopMain.class);
                        mContext.startActivity(intent);
                        break;

                        case 5:
                        intent = new Intent(mContext, ApartmentMain.class);
                        mContext.startActivity(intent);
                        break;

                        case 6:
                        intent = new Intent(mContext, AmuseMain.class);
                        mContext.startActivity(intent);
                        break;

                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sort sort = mSortList.get(position);
        holder.sortName.setText(sort.getName());
        Glide.with(mContext).load(sort.getImageId()).into(holder.sortImage);
    }

    @Override
    public int getItemCount() {
        return mSortList.size();
    }



}
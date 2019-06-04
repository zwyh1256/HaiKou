package com.example.haikou;

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
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

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
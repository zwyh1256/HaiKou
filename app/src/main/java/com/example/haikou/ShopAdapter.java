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

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    private Context mContext;
    private List<Shop> mshopList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView shopImage;
        TextView shopName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            shopImage = (ImageView)view.findViewById(R.id.shop_image);
            shopName = (TextView) view.findViewById(R.id.shop_name);
        }
    }

    public ShopAdapter(List<Shop> shopList){
        mshopList = shopList;
    }

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop,parent,false);

        final ShopAdapter.ViewHolder holder = new ShopAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Shop shop = mshopList.get(position);
                Intent intent = new Intent(mContext, ShopDetail.class);
                intent.putExtra(ShopDetail.SHOP_NAME,shop.getName());
                intent.putExtra(ShopDetail.SHOP_IMAGE_ID,shop.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position){
        Shop shop = mshopList.get(position);
        holder.shopName.setText(shop.getName());

        Glide.with(mContext).load(shop.getImageId()).into(holder.shopImage);
    }

    @Override
    public int getItemCount(){
        return mshopList.size();
    }
}

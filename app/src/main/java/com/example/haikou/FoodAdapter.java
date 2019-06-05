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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private List<Food> mFoodList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView foodImage;
        TextView foodName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            foodImage = (ImageView)view.findViewById(R.id.food_image);
            foodName = (TextView) view.findViewById(R.id.food_name);
        }
    }

    public FoodAdapter(List<Food> foodList){
        mFoodList = foodList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_food,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Food food = mFoodList.get(position);
                Intent intent = new Intent(mContext, FoodDetail.class);
                intent.putExtra(FoodDetail.FOOD_NAME,food.getName());
                intent.putExtra(FoodDetail.FOOD_IMAGE_ID,food.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Food food = mFoodList.get(position);
        holder.foodName.setText(food.getName());

        Glide.with(mContext).load(food.getImageId()).into(holder.foodImage);
    }

    @Override
    public int getItemCount(){
        return mFoodList.size();
    }


}

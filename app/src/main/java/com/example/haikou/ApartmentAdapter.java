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

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private Context mContext;
    private List<Apartment> mApartmentList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView apartmentImage;
        TextView apartmentName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            apartmentImage = (ImageView)view.findViewById(R.id.apartment_image);
            apartmentName = (TextView) view.findViewById(R.id.apartment_name);
        }
    }

    public ApartmentAdapter(List<Apartment> apartmentList){
        mApartmentList = apartmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_apartment,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Apartment apartment = mApartmentList.get(position);
                Intent intent = new Intent(mContext, ApartmentDetail.class);
                intent.putExtra(ApartmentDetail.APARTMENT_NAME,apartment.getName());
                intent.putExtra(ApartmentDetail.APARTMENT_IMAGE_ID,apartment.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Apartment apartment = mApartmentList.get(position);
        holder.apartmentName.setText(apartment.getName());

        Glide.with(mContext).load(apartment.getImageId()).into(holder.apartmentImage);
    }

    @Override
    public int getItemCount(){
        return mApartmentList.size();
    }
}

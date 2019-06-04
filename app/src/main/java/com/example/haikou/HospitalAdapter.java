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

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private Context mContext;
    private List<Hospital> mHospitalList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView hospitalImage;
        TextView hospitalName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            hospitalImage = (ImageView)view.findViewById(R.id.hospital_image);
            hospitalName = (TextView) view.findViewById(R.id.hospital_name);
        }
    }

    public HospitalAdapter(List<Hospital> hospitalList){
        mHospitalList = hospitalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hospital,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Hospital hospital = mHospitalList.get(position);
                Intent intent = new Intent(mContext, HospitalDetail.class);
                intent.putExtra(HospitalDetail.HOSPITAL_NAME,hospital.getName());
                intent.putExtra(HospitalDetail.HOSPITAL_IMAGE_ID,hospital.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Hospital hospital = mHospitalList.get(position);
        holder.hospitalName.setText(hospital.getName());

        Glide.with(mContext).load(hospital.getImageId()).into(holder.hospitalImage);
    }

    @Override
    public int getItemCount(){
        return mHospitalList.size();
    }
}

package com.example.brandshoesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.brandshoesapp.R;
import com.example.brandshoesapp.ViewAllActivity;
import com.example.brandshoesapp.model.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
     private Context context;
    private List<PopularModel> popularModels;

    public PopularAdapter(Context context, List<PopularModel> popularModels) {
        this.context = context;
        this.popularModels = popularModels;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(popularModels.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModels.get(position).getName());
        holder.desc.setText(popularModels.get(position).getDescription());
        holder.rating.setText(popularModels.get(position).getRating());
        holder.discount.setText(popularModels.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popularModels.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, desc, rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.popular_item_image);
           name=itemView.findViewById(R.id.popular_title);
            desc=itemView.findViewById(R.id.popular_description);
           rating=itemView.findViewById(R.id.popular_rating);
           discount=itemView.findViewById(R.id.popular_discount);
        }
    }
}

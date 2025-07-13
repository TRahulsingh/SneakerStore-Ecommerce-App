package com.example.brandshoesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.brandshoesapp.R;
import com.example.brandshoesapp.model.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    private Context context;
    private List<RecommendedModel> list;

    public RecommendedAdapter(Context context, List<RecommendedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.recommImage);
        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recommImage;
        TextView name, desc, rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recommImage=itemView.findViewById(R.id.recommended_item_image);
            name=itemView.findViewById(R.id.recommended_title);
           desc=itemView.findViewById(R.id.recommended_description);
            rating=itemView.findViewById(R.id.recommended_rating);
        }
    }
}

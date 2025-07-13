package com.example.brandshoesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.R;
import com.example.brandshoesapp.model.DetailsNewProductsModel;

import java.util.List;

public class DetailsNewProductsAdapter extends RecyclerView.Adapter<DetailsNewProductsAdapter.ViewHolder> {
    Context context;
    List<DetailsNewProductsModel> list;
    public DetailsNewProductsAdapter(Context context, List<DetailsNewProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailsNewProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.details_new_products_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsNewProductsAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.details_new_product_img);
            textView = itemView.findViewById(R.id.details_Nproducts_name);
        }
    }
}

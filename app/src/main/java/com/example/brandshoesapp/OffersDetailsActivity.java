package com.example.brandshoesapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.adapter.DetailsOffersAdapter;
import com.example.brandshoesapp.model.DetailsOffersModel;

import java.util.ArrayList;
import java.util.List;

public class OffersDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DetailsOffersModel> modelList;
    DetailsOffersAdapter adapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String type= getIntent().getStringExtra("type");

        setContentView(R.layout.activity_offers_details);

        recyclerView = findViewById(R.id.detail_offers_recycler);
        imageView = findViewById(R.id.detail_collapse_img);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new DetailsOffersAdapter(this, modelList);
        recyclerView.setAdapter(adapter);

        if (type!=null && type.equalsIgnoreCase("nike")){
            imageView.setImageResource(R.drawable.nike4);
            modelList.add(new DetailsOffersModel(R.drawable.nike3,"Nike","135$","Discount 30 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.nike1,"Nike","138$","Discount 30 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.nike4,"Nike","145$","Discount 30 % OFF"));
            adapter.notifyDataSetChanged();
        }

        if (type!=null && type.equalsIgnoreCase("nbalance")){
            imageView.setImageResource(R.drawable.newb2);
            modelList.add(new DetailsOffersModel(R.drawable.newb4,"New Balance","135$","Discount 40 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.newb1,"New Balance","138$","Discount 40 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.newb3,"New Balance","145$","Discount 40 % OFF"));
            adapter.notifyDataSetChanged();
        }

        if (type!=null && type.equalsIgnoreCase("adidas")){
            imageView.setImageResource(R.drawable.adidas3);
            modelList.add(new DetailsOffersModel(R.drawable.adidas1,"Adidas","135$","Discount 45 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.adidas4,"Adidas","138$","Discount 45 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.adidas2,"Adidas","145$","Discount 45 % OFF"));
            adapter.notifyDataSetChanged();
        }

        if (type!=null && type.equalsIgnoreCase("puma")){
            imageView.setImageResource(R.drawable.puma3);
            modelList.add(new DetailsOffersModel(R.drawable.puma2,"Puma","135$","Discount 55 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.puma1,"Puma","138$","Discount 55 % OFF"));
            modelList.add(new DetailsOffersModel(R.drawable.puma4,"Puma","145$","Discount 55 % OFF"));
            adapter.notifyDataSetChanged();
        }

    }
}
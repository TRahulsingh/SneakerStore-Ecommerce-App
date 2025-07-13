package com.example.brandshoesapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.adapter.DetailsNewProductsAdapter;
import com.example.brandshoesapp.model.DetailsNewProductsModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsProductsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DetailsNewProductsModel> modelList;
    DetailsNewProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_products);

        String type=getIntent().getStringExtra("type");

        recyclerView=findViewById(R.id.details_products_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        modelList=new ArrayList<>();
        adapter=new DetailsNewProductsAdapter(this,modelList);
        recyclerView.setAdapter(adapter);

        if (type!=null && type.equalsIgnoreCase("nike")){
            modelList.add(new DetailsNewProductsModel(R.drawable.new12,"Nike"));
            modelList.add(new DetailsNewProductsModel(R.drawable.new13,"Nike"));
        }
        if (type!=null && type.equalsIgnoreCase("adidas")){
            modelList.add(new DetailsNewProductsModel(R.drawable.new10,"Adidas"));
            modelList.add(new DetailsNewProductsModel(R.drawable.new11,"Adidas"));
        }
        if (type!=null && type.equalsIgnoreCase("nbalance")){
            modelList.add(new DetailsNewProductsModel(R.drawable.new14,"New Balance"));
            modelList.add(new DetailsNewProductsModel(R.drawable.new15,"New Balance"));
        }
        if (type!=null && type.equalsIgnoreCase("puma")){
            modelList.add(new DetailsNewProductsModel(R.drawable.new16,"Puma"));
            modelList.add(new DetailsNewProductsModel(R.drawable.new17,"Puma"));
        }

    }
}
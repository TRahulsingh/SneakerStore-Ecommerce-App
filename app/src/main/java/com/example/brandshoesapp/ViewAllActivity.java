package com.example.brandshoesapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.adapter.ViewAllAdapter;
import com.example.brandshoesapp.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView viewRecycler;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        db=FirebaseFirestore.getInstance();

        String type =getIntent().getStringExtra("type");
        viewRecycler=findViewById(R.id.viewAll_Recycler);
        viewRecycler.setLayoutManager(new LinearLayoutManager(this));



        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(this,viewAllModelList);
        viewRecycler.setAdapter(viewAllAdapter);

        if (type!=null && type.equalsIgnoreCase("nike")){
            db.collection("AllShoes").whereEqualTo("type","nike").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= document.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        if (type!=null && type.equalsIgnoreCase("adidas")){
            db.collection("AllShoes").whereEqualTo("type","adidas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= document.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        if (type!=null && type.equalsIgnoreCase("nbalance")){
            db.collection("AllShoes").whereEqualTo("type","nbalance").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= document.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        if (type!=null && type.equalsIgnoreCase("puma")){
            db.collection("AllShoes").whereEqualTo("type","puma").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= document.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        if (type!=null && type.equalsIgnoreCase("zara")){
            db.collection("AllShoes").whereEqualTo("type","zara").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= document.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }




    }
}
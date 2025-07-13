package com.example.brandshoesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.adapter.AddressAdapter;
import com.example.brandshoesapp.model.AddressModel;
import com.example.brandshoesapp.model.CategoryModel;
import com.example.brandshoesapp.model.PopularModel;
import com.example.brandshoesapp.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.OnRadioButtonSelectedListener {
    Button addressBtn, paymentBtn;
    RecyclerView recyclerView;
   private AddressAdapter addressAdapter;
   private List<AddressModel> addressModels;
    FirebaseFirestore db;
    FirebaseAuth auth;
    String mAddress="";
    int quantity = 1;
    double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        //// pass data from details activity
        Object object =getIntent().getSerializableExtra("items");
        quantity = getIntent().getIntExtra("quantity", 1);
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);


        addressBtn=findViewById(R.id.address_Btn);
        paymentBtn=findViewById(R.id.payment_Btn);

        recyclerView=findViewById(R.id.address_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModels=new ArrayList<>();
        addressAdapter=new AddressAdapter(getApplicationContext(),addressModels,this);
        recyclerView.setAdapter(addressAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                AddressModel addressModel=documentSnapshot.toObject(AddressModel.class);
                                addressModels.add(addressModel);
                                addressAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
            }
        });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = "0.0";
                double pricePerItem = 0.0;

                if (object instanceof ViewAllModel) {
                    ViewAllModel viewAllModel = (ViewAllModel) object;
                    String priceString = viewAllModel.getPrice().replaceAll("[^\\d.]", "");
                    if (!priceString.isEmpty()) {
                        pricePerItem = Double.parseDouble(priceString);
                    }
                }

                // Calculate the total price based on the quantity
                double totalPrice = pricePerItem * quantity;
                amount = String.valueOf(totalPrice);

                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRadioButtonSelected(String address) {
        mAddress=address;

    }
}
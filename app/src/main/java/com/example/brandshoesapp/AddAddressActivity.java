package com.example.brandshoesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddAddressActivity extends AppCompatActivity {
    EditText name, address, city,postal,phoneNumber;
    Button addAddress;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        setContentView(R.layout.activity_add_address);
        name=findViewById(R.id.editText_name);
        address=findViewById(R.id.editText_Address);
        city=findViewById(R.id.editText_city);
        phoneNumber=findViewById(R.id.editText_phone);
        postal=findViewById(R.id.editText_postal);
        addAddress=findViewById(R.id.add_address_Btn);

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userAddress = address.getText().toString();
                String userCity = city.getText().toString();
                String userPostal = postal.getText().toString();
                String userNumber = phoneNumber.getText().toString();

                String final_address = "";

                if (!userName.isEmpty()){
                    final_address+=userName;
                }
                if (!userAddress.isEmpty()){
                    final_address+=userAddress;
                }
                if (!userCity.isEmpty()){
                    final_address+=userCity;
                }
                if (!userPostal.isEmpty()){
                    final_address+=userPostal;
                }
                if (!userNumber.isEmpty()){
                    final_address+=userNumber;
                }
                if (!userName.isEmpty() && !userAddress.isEmpty() && !userCity.isEmpty() && !userPostal.isEmpty() && !userNumber.isEmpty()){

                    HashMap<String,String> map=new HashMap<>();
                    map.put("userAddress",final_address);
                    db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AddAddressActivity.this,DetailsActivity.class));
                                    }

                                }
                            });

                } else {
                    Toast.makeText(AddAddressActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
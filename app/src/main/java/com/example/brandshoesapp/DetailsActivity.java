package com.example.brandshoesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.brandshoesapp.model.CategoryModel;
import com.example.brandshoesapp.model.PopularModel;
import com.example.brandshoesapp.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    ImageView detailsImage;
    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    TextView name, rating, description,price;
    Button addToCart, buyNow;
    ImageView addBtn,removeBtn;

    ViewAllModel viewAllModel = null;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object=getIntent().getSerializableExtra("details");
        if (object instanceof ViewAllModel){
            viewAllModel=(ViewAllModel) object;
        }

        detailsImage=findViewById(R.id.details_img);
       name=findViewById(R.id.details_name);
       rating=findViewById(R.id.details_rating);
       description=findViewById(R.id.details_desc);
        price=findViewById(R.id.details_price);
        addToCart=findViewById(R.id.add_to_cart_Btn);
        buyNow=findViewById(R.id.buy_now_Btn);
        addBtn=findViewById(R.id.add_item);
        removeBtn=findViewById(R.id.remove_item);
        quantity=findViewById(R.id.quantity);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(DetailsActivity.this, AddressActivity.class);

                if (viewAllModel!=null){
                    intent.putExtra("items",viewAllModel);
                    intent.putExtra("quantity", totalQuantity);
                    intent.putExtra("totalPrice", totalPrice);
                }
              startActivity(intent);

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10){
                    totalQuantity ++;
                   quantity.setText(String.valueOf(totalQuantity));
                    updateTotalPrice();

                }
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    updateTotalPrice();
                }
            }
        });



        //// passing data through activity
        if (viewAllModel!=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailsImage);
            name.setText(viewAllModel.getName());
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText(viewAllModel.getPrice());


        }
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addedToCart();
                }
            });
    }

    private void updateTotalPrice() {
        // Check if the price string is not empty or null to avoid NumberFormatException
        if (viewAllModel.getPrice() != null && !viewAllModel.getPrice().isEmpty()) {
            // Remove any non-numeric characters (e.g., currency symbols) from the price string
            String priceString = viewAllModel.getPrice().replaceAll("[^\\d.]", "");
            // Convert the cleaned price string to a double
            double pricePerItem = Double.parseDouble(priceString);
            // Calculate the total price
            totalPrice = (int)(pricePerItem * totalQuantity);
        } else {
            totalPrice = 0;
        }
    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", String.valueOf(totalPrice));

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailsActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }


}
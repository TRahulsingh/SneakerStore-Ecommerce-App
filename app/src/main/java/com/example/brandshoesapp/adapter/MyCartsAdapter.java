package com.example.brandshoesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandshoesapp.R;
import com.example.brandshoesapp.model.MyCartsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartsAdapter extends RecyclerView.Adapter<MyCartsAdapter.ViewHolder> {

    Context context;
    List<MyCartsModel> myCartsModels;

    FirebaseFirestore db;
    FirebaseAuth auth;

    public MyCartsAdapter(Context context, List<MyCartsModel> myCartsModels) {
        this.context = context;
        this.myCartsModels = myCartsModels;
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyCartsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_carts_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartsAdapter.ViewHolder holder, int position) {
        holder.name.setText(myCartsModels.get(position).getProductName());
        holder.price.setText(myCartsModels.get(position).getProductPrice());
        holder.date.setText(myCartsModels.get(position).getCurrentDate());
        holder.time.setText(myCartsModels.get(position).getCurrentTime());
        holder.quantity.setText(myCartsModels.get(position).getTotalQuantity());
        holder.totalPrice.setText(myCartsModels.get(position).getTotalPrice());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(myCartsModels.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful());
                                myCartsModels.remove(myCartsModels.get(position));
                                notifyDataSetChanged();
                                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return myCartsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date , time,quantity,totalPrice;
        ImageView deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.myCart_product_name);
           price=itemView.findViewById(R.id.myCart_product_price);
           date=itemView.findViewById(R.id.myCart_currentDate);
            time=itemView.findViewById(R.id.myCart_currentTime);
            quantity=itemView.findViewById(R.id.myCart_total_Quantity);
           totalPrice=itemView.findViewById(R.id.myCartItem_total_Price);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);

        }
    }
}

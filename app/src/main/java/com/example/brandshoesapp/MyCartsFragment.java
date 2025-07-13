package com.example.brandshoesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.brandshoesapp.adapter.MyCartsAdapter;
import com.example.brandshoesapp.model.MyCartsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {
    RecyclerView cartsRecycler;
    TextView totalPrice;
    MyCartsAdapter myCartsAdapter;
    List<MyCartsModel> myCartsModelList;
    FirebaseFirestore db;
    FirebaseAuth auth;

    ProgressDialog progressDialog;

    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root=inflater.inflate(R.layout.fragment_my_carts, container, false);
        progressDialog=new ProgressDialog(getActivity());

       cartsRecycler=root.findViewById(R.id.my_carts_recycler);
       totalPrice=root.findViewById(R.id.my_carts_totalPrice);
       db=FirebaseFirestore.getInstance();
       auth=FirebaseAuth.getInstance();

        /// setting progress dialog
        progressDialog.setTitle("Welcome To My Cart");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

       cartsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
       myCartsModelList=new ArrayList<>();
       myCartsAdapter=new MyCartsAdapter(getActivity(),myCartsModelList);
       cartsRecycler.setAdapter(myCartsAdapter);

       db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
               .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful()){
                           for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                               String documentId = documentSnapshot.getId();
                               MyCartsModel cartsModel=documentSnapshot.toObject(MyCartsModel.class);
                               cartsModel.setDocumentId(documentId);
                               myCartsModelList.add(cartsModel);
                               myCartsAdapter.notifyDataSetChanged();
                               progressDialog.dismiss();

                           }
                           calculateTotalPrice(myCartsModelList);
                       }



                   }
               });

       return root;
    }

    private void calculateTotalPrice(List<MyCartsModel> myCartsModelList) {
        double total = 0.0;
        for (MyCartsModel myCartsModel : myCartsModelList) {
            // Convert the price string to a double
            String priceString = myCartsModel.getTotalPrice().replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceString);
            total += price;
        }
        // Convert the double value to a string
        String totalPriceString = "Total Price: $" + String.format("%.2f", total);
        totalPrice.setText(totalPriceString);




    }
}
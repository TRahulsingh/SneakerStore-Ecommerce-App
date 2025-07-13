package com.example.brandshoesapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.brandshoesapp.R;
import com.example.brandshoesapp.adapter.CategoryAdapter;
import com.example.brandshoesapp.adapter.PopularAdapter;
import com.example.brandshoesapp.adapter.RecommendedAdapter;
import com.example.brandshoesapp.model.CategoryModel;
import com.example.brandshoesapp.model.PopularModel;
import com.example.brandshoesapp.model.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ProgressDialog progressDialog;
    LinearLayout linearLayout;
    RecyclerView popularRecycler,categoryRecycler,recommRecycler;
    FirebaseFirestore db;

    //// Popular Items
    List<PopularModel> popularModels;
    PopularAdapter popularAdapter;

    /////// Category explore items
    List<CategoryModel> categoryModels;
    CategoryAdapter categoryAdapter;

    ///////// Recommended items

    List<RecommendedModel> recommendedModels;
    RecommendedAdapter recommendedAdapter;

    ImageSlider imageSlider;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog=new ProgressDialog(getActivity());
        linearLayout=root.findViewById(R.id.home_layout);
        imageSlider=root.findViewById(R.id.image_slider);
        linearLayout.setVisibility(View.GONE);

        popularRecycler=root.findViewById(R.id.popular_recycler);
        categoryRecycler=root.findViewById(R.id.category_recycler);
        recommRecycler=root.findViewById(R.id.recommended_recycler);
        db=FirebaseFirestore.getInstance();

        /// setting progress dialog
        progressDialog.setTitle("Welcome To Our App");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        //////////// Popular items
        popularRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModels=new ArrayList<>();
        popularAdapter=new PopularAdapter(getActivity(),popularModels);
        popularRecycler.setAdapter(popularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel=document.toObject(PopularModel.class);
                                popularModels.add(popularModel);
                                popularAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                        } else {

                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //////////////// Category Explore items
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModels=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getActivity(),categoryModels);
        categoryRecycler.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                               CategoryModel categoryModel=document.toObject(CategoryModel.class);
                               categoryModels.add(categoryModel);
                               categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                               progressDialog.dismiss();
                            }
                        } else {

                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                          /////////////// Recommended items
        recommRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModels=new ArrayList<>();
        recommendedAdapter=new RecommendedAdapter(getActivity(),recommendedModels);
        recommRecycler.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                              RecommendedModel recommendedModel=document.toObject(RecommendedModel.class);
                              recommendedModels.add(recommendedModel);
                              recommendedAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                              progressDialog.dismiss();
                            }
                        } else {

                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        settingImageSlider();

        return root;
    }

    private void settingImageSlider() {
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

    }

}

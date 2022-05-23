package com.lakens.shareameal.applicationlogic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lakens.shareameal.R;
import com.lakens.shareameal.domain.Cook;
import com.lakens.shareameal.domain.Meal;
import com.lakens.shareameal.presentation.MealDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;

public class MealList extends RecyclerView.Adapter<MealList.MealViewHolder> {
    // Globale attributen
    private final static String LOG_TAG = MealList.class.getSimpleName();
    private LinkedList<Meal> mMealList;
    private ImageView mImageView;
    private Context context;
    private LayoutInflater mInflater;

    // Constructor
    public MealList(Context context, LinkedList<Meal> mMealList) {
        this.mMealList = mMealList;
        this.context = context;
        Log.i(LOG_TAG, "Meal list aangemaakt");
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView date;
        public final TextView price;
        public final TextView city;
        public final ImageView image;
        final MealList mAdapter;

        public MealViewHolder(View itemView, MealList adapter) {
            super(itemView);
            name = itemView.findViewById(R.id.mealName);
            date = itemView.findViewById(R.id.mealDate);
            price = itemView.findViewById(R.id.mealPrice);
            city = itemView.findViewById(R.id.mealCity);
            image = itemView.findViewById(R.id.imageMeal);

            this.mAdapter = adapter;
        }
    }

    @NonNull
    @Override
    public MealList.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder maken");
        View mItemView = mInflater.inflate(R.layout.meal, parent, false);
        return new MealViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MealList.MealViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder positie = " + position);
        Meal mCurrentMeal = this.mMealList.get(position);
        Cook mCurrentCook = mCurrentMeal.getCook();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodPage = new Intent(context, MealDetail.class);
                foodPage.putExtra("mealName", mCurrentMeal.getName());
                foodPage.putExtra("foodPrice", mCurrentMeal.getPrice());
                foodPage.putExtra("mealDescription", mCurrentMeal.getDescription());
                foodPage.putExtra("mealDate", mCurrentMeal.getServeDate());
                foodPage.putExtra("mealAllergyInfo", mCurrentMeal.getAllergenes());
                foodPage.putExtra("cook", mCurrentMeal.getName());
                foodPage.putExtra("cookCity", mCurrentCook.getCity());
                foodPage.putExtra("cookStreet", mCurrentCook.getStreet());
                foodPage.putExtra("cookEmail", mCurrentCook.getEmailAddress());
                foodPage.putExtra("cookPhone", mCurrentCook.getPhoneNumber());
                foodPage.putExtra("foodImage", mCurrentMeal.getImageUrl());;
                foodPage.putExtra("isVega", mCurrentMeal.isVega());
                foodPage.putExtra("isVegan", mCurrentMeal.isVegan());
                Log.v(LOG_TAG, "Made new activity");
                context.startActivity(foodPage);
                Log.v(LOG_TAG, "Started new activity");
            }
        });

        holder.name.setText(mCurrentMeal.getName());
        holder.date.setText(mCurrentMeal.getServeDate());
        holder.price.setText(mCurrentMeal.getPrice());
        holder.city.setText(mCurrentCook.getCity());
        Log.d(LOG_TAG, "Image proberen te laden " + mCurrentMeal.getImageUrl());
        try {
            Picasso.with(context)
                    .load(Uri.parse(mCurrentMeal.getImageUrl()))
                    .into(holder.image);
        }catch (Exception exception){
            Log.e(LOG_TAG, "Image kon niet geladen worden");
        }
    }

    public int getItemCount() {
        Log.i(LOG_TAG, "Meals size: " + mMealList.size());
        return this.mMealList.size();
    }

    public void setMealList(LinkedList<Meal> mealList) {
        this.mMealList = mealList;
        notifyDataSetChanged();
    }

//    // Koppeling tussen itemscherm in de RV, en de data in de AL<Meal>.
//    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public TextView name;
//        public TextView date;
//        public TextView price;
//        public TextView city;
//        public ImageView image;
//
//        public void bindTo(Meal meal) {
//            name.setText(meal.getName());
//            Cook currentCook = meal.getCook();
//            date.setText(meal.getServeDate());
//            city.setText(currentCook.getCity());
//            price.setText(meal.getPrice());
//            Log.d(LOG_TAG, "Image proberen te laden " + meal.getImageUrl());
//            try {
//                Picasso.with(context)
//                        .load(Uri.parse(meal.getImageUrl()))
//                        .into(image);
//            }catch (Exception exception){
//                Log.e(LOG_TAG, "Image kon niet geladen worden");
//            }
//        }
//
//        @Override
//        public void onClick(View view) {
//            Meal meal = mMeals.get(getAdapterPosition());
//            Log.i(LOG_TAG, "Er is geklikt op: " + meal.getName());
//            Cook cook = mMeals.get(getAdapterPosition()).getCook();
//            Intent foodPage = new Intent(context, MealDetail.class);
//
//            foodPage.putExtra("mealName", meal.getName());
//            foodPage.putExtra("foodPrice", meal.getPrice());
//            foodPage.putExtra("mealDescription", meal.getDescription());
//            foodPage.putExtra("mealDate", meal.getServeDate());
//            foodPage.putExtra("mealAllergyInfo", meal.getAllergenes());
//            foodPage.putExtra("cook", cook.getName());
//            foodPage.putExtra("cookCity", cook.getCity());
//            foodPage.putExtra("cookStreet", cook.getStreet());
//            foodPage.putExtra("cookEmail", cook.getEmailAddress());
//            foodPage.putExtra("cookPhone", cook.getPhoneNumber());
//            foodPage.putExtra("foodImage", meal.getImageUrl());;
//            foodPage.putExtra("isVega", meal.isVega());
//            foodPage.putExtra("isVegan", meal.isVegan());
//            Log.v(LOG_TAG, "Made new activity");
//            context.startActivity(foodPage);
//            Log.v(LOG_TAG, "Started new activity");
//        }
//    }
}

package com.lakens.shareameal.applicationlogic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    // Globale attributen
    private final static String LOG_TAG = MealAdapter.class.getSimpleName();
    private ArrayList<Meal> meals;
    private ImageView mImageView;
    private Context context;
    private LayoutInflater mInflater;

    // Constructor
    public MealAdapter(Context context, ArrayList<Meal> mMeals) {
        this.meals = meals;
        this.context = context;
        Log.i(LOG_TAG, "Meal list aangemaakt");
        //mInflater = LayoutInflater.from(context);
    }

    @NonNull
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder maken");
        View itemView = mInflater.inflate(R.layout.meal, parent, false);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.meal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder positie = " + position);
        Meal meal = this.meals.get(position);
        holder.bindTo(meal);
    }

    public int getItemCount() {
        Log.i(LOG_TAG, "Meals size: " + meals.size());
        return this.meals.size();
    }

    // Koppeling tussen itemscherm in de RV, en de data in de AL<Meal>.
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView date;
        public TextView price;
        public TextView city;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.i(LOG_TAG, "ItemView items initialiseren");
            image = (ImageView) itemView.findViewById(R.id.imageMeal);
            name = (TextView) itemView.findViewById(R.id.mealName);
            date = (TextView) itemView.findViewById(R.id.mealDate);
            city = (TextView) itemView.findViewById(R.id.mealCity);
            price = (TextView) itemView.findViewById(R.id.mealPrice);

            itemView.setOnClickListener(this);
        }

        public void bindTo(Meal meal) {
            name.setText(meal.getName());
            Cook currentCook = meal.getCook();
            date.setText(meal.getServeDate());
            city.setText(currentCook.getCity());
            price.setText(meal.getPrice());
            Log.d(LOG_TAG, "Image proberen te laden " + meal.getImageUrl());
            try {
                Picasso.with(context)
                        .load(Uri.parse(meal.getImageUrl()))
                        .into(image);
            }catch (Exception exception){
                Log.e(LOG_TAG, "Image kon niet geladen worden");
            }
        }

        @Override
        public void onClick(View view) {
            Meal meal = meals.get(getAdapterPosition());
            Log.i(LOG_TAG, "Er is geklikt op: " + meal.getName());
            Cook cook = meals.get(getAdapterPosition()).getCook();
            Intent foodPage = new Intent(context, MealDetail.class);

            foodPage.putExtra("foodName", meal.getName());
            foodPage.putExtra("foodPrice", meal.getPrice());
            foodPage.putExtra("foodDescription", meal.getDescription());
            foodPage.putExtra("foodDate", meal.getServeDate());
            foodPage.putExtra("foodAllergies", meal.getAllergenes());
            foodPage.putExtra("foodCook", cook.getName());
            foodPage.putExtra("foodCity", cook.getCity());
            foodPage.putExtra("foodStreet", cook.getStreet());
            foodPage.putExtra("foodEmail", cook.getEmailAddress());
            foodPage.putExtra("foodPhone", cook.getPhoneNumber());
            foodPage.putExtra("image_resource", meal.getImageUrl());;
            foodPage.putExtra("foodVega", meal.isVega());
            foodPage.putExtra("foodVegan", meal.isVegan());
            Log.v(LOG_TAG, "Made new activity");
            context.startActivity(foodPage);
            Log.v(LOG_TAG, "Started new activity");
        }
    }
}

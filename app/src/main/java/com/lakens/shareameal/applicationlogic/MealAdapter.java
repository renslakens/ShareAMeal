package com.lakens.shareameal.applicationlogic;

import android.content.Context;
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
import com.lakens.shareameal.domain.Meal;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    // Globale attributen
    private final static String LOG_TAG = MealAdapter.class.getSimpleName();
    private ArrayList<Meal> meals;
    private Context context;
    private LayoutInflater mInflater;

    // Constructor
    public MealAdapter(Context context, ArrayList<Meal> mMeals) {
        this.meals = meals;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder");
        View itemView = mInflater.inflate(R.layout.meal, parent, false);
        return new MealViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder position = " + position);
        Meal meal = this.meals.get(position);
        holder.image.setImageResource(Integer.parseInt(meals.get(position).getImageUrl()));
    }

    public int getItemCount() {
        return this.meals.size();
    }

    // Koppeling tussen itemscherm in de RV, en de data in de AL<Meal>.
    class MealViewHolder extends RecyclerView.ViewHolder {
        final MealAdapter mAdapter;

        public TextView name;
        public TextView date;
        public TextView price;
        public ImageView image;

        public MealViewHolder(@NonNull View itemView, MealAdapter mealAdapter) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imageMeal);
            name = (TextView) itemView.findViewById(R.id.mealName);
            date = (TextView) itemView.findViewById(R.id.mealDate);
            price = (TextView) itemView.findViewById(R.id.mealPrice);

            this.mAdapter = mealAdapter;
        }

    }
}

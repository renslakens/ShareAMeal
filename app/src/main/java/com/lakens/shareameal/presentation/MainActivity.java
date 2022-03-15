package com.lakens.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lakens.shareameal.R;
import com.lakens.shareameal.applicationlogic.MealAdapter;
import com.lakens.shareameal.domain.Meal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Meal> mMeal;
    private RecyclerView mRecyclerView;
    private MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed
        mealAdapter = new MealAdapter(this, mMeal);

        // Connect adapter with RecyclerView
        mRecyclerView.setAdapter(mealAdapter);

        // Give RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Log.e(TAG, "Fetching meals");

    }
}
package com.lakens.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import com.lakens.shareameal.R;
import com.lakens.shareameal.applicationlogic.FetchMeal;
import com.lakens.shareameal.applicationlogic.MealAdapter;
import com.lakens.shareameal.dataaccess.ApiClient;
import com.lakens.shareameal.dataaccess.ApiInterface;
import com.lakens.shareameal.domain.Meal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private FetchMeal mMeal = new FetchMeal(this);

    private RecyclerView mRecyclerView;
    private MealAdapter mealAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Context context;

    private final ArrayList<Meal> mMealList = new ArrayList<>();

    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setAdapter();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed
        mealAdapter = mMeal.getAdapter();

        // Connect adapter with RecyclerView
        mRecyclerView.setAdapter(mealAdapter);

        // Give RecyclerView a default layout manager
        int gridColumnCount = getResources().getInteger(R.integer.gridColumnCount);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Zorgt dat internetverbinding wordt gemaakt en of die ook uitgevoerd wordt of niet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
            Log.w(LOG_TAG, "Geen internetverbinding");
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            mMeal.execute();
            Log.i(LOG_TAG, "Fetching meal");
        }
    }

    private void getAllMovies(String sortBy) {
        mSwipeRefreshLayout.setRefreshing(true);

        Call<Movie> call = apiInterface.getMeals();

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Meal meals = response.body();
                mMealList.addAll(meals.getResults());
                Log.d(LOG_TAG, "response = " + response);
                Log.d(LOG_TAG, "Movies = " + mMealList);
                mSwipeRefreshLayout.setRefreshing(false);
                mealAdapter.setMovieList(mMovieList);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }

    private void setAdapter() {
        mRecyclerView = findViewById(R.id.recyclerview);
        Log.d(LOG_TAG, getIntent().getStringExtra("session_id"));
        mealAdapter = new MealAdapter(context, mMealList);
        mRecyclerView.setAdapter(mealAdapter);
        int gridColumnCount = 2;

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
    }

    // SavedOnInstanceState methodes
    @Override
    protected void onStart() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        mRecyclerView.setAdapter(mealAdapter);
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
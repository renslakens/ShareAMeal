package com.lakens.shareameal.applicationlogic;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lakens.shareameal.R;
import com.lakens.shareameal.dataaccess.NetworkUtils;
import com.lakens.shareameal.domain.Cook;
import com.lakens.shareameal.domain.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FetchMeal extends AsyncTask<String, Void, String> {
    private final String TAG = FetchMeal.class.getSimpleName();

    private ArrayList<Meal> mMeals;
    private final MealAdapter mAdapter;
    private final Context mContext;

    private WeakReference<TextView> mTitleMeal;
    private WeakReference<ImageButton> mImageMeal;
    private Object MealAdapter;

    public FetchMeal(Context context) {
        this.mContext = context;
        mMeals = new ArrayList<Meal>();
        mAdapter = new MealAdapter(context, mMeals);
        Log.d(TAG, "Made Fetchmeal class");
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i(TAG, "Started doInBackground");
        return NetworkUtils.getMealInfo();
    }

    @Override
    protected void onPostExecute(String jsonString) {
        try {
            mMeals.clear();
            JSONObject obj = new JSONObject(jsonString);
            JSONArray mealArray = obj.getJSONArray("result");
            Log.i(TAG, "Starting JSON operation");
            for (int i = 0; i < mealArray.length(); i++) {
                JSONObject mealInfo = mealArray.getJSONObject(i);

                // Datum omzetten
                String date = mealInfo.getString("dateTime").toString();
                String serveDate = null;
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    serveDate = String.valueOf(dateFormat.format(tempDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Cook maken
                JSONObject objcook = new JSONObject(mealInfo.getString("cook"));
                String cookNameString = objcook.getString("firstName")+ " " + objcook.getString("lastName");
                Cook cook = new Cook(
                        cookNameString,
                        objcook.getString("city"),
                        objcook.getString("street"),
                        objcook.getString("phoneNumber"),
                        objcook.getString("emailAddress"));

                // Meal maken
                mMeals.add(new Meal(
                        mealInfo.getString("name"),
                        mealInfo.getString("description"),
                        mealInfo.getString("price"),
                        mealInfo.getString("imageUrl"),
                        mealInfo.getInt("id"),
                        mealInfo.getBoolean("isVega"),
                        mealInfo.getBoolean("isVegan"),
                        serveDate,
                        mealInfo.getString("allergenes"),
                        cook,
                        mealInfo.getBoolean("isToTakeHome"),
                        mealInfo.getBoolean("isActive")));
                Log.d(TAG, "Created meal" + mealInfo.getInt("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onPostExecute(jsonString);
        Toast.makeText(mContext,
                mMeals.size() + mContext.getString(R.string.meals_loaded_toast),
                Toast.LENGTH_SHORT)
                .show();
        mAdapter.notifyDataSetChanged();
    }

    public MealAdapter getAdapter() {
        Log.d(TAG, "returned MealList Adapter");
        return mAdapter;
    }
}
package com.lakens.shareameal.presentation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.lakens.shareameal.R;
import com.squareup.picasso.Picasso;

public class MealDetail extends AppCompatActivity {
    private final String LOG_TAG = MealDetail.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //Actionbar aanroepen
        ActionBar actionBar = getSupportActionBar();

        //Back knop laten zien in actionbar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Views maken
        Log.i(LOG_TAG, "Views aanmaken");
        TextView name = (TextView) findViewById(R.id.mealName);
        TextView price = (TextView) findViewById(R.id.mealPrice);
        TextView description = (TextView) findViewById(R.id.mealDescription);
        TextView date = (TextView) findViewById(R.id.mealDate);
        TextView allergens = (TextView) findViewById(R.id.mealAllergyInfo);
        ImageView image = (ImageView) findViewById(R.id.foodImage);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        TextView cook = (TextView) findViewById(R.id.cook);
        TextView cookCity = (TextView) findViewById(R.id.cookCity);
        TextView cookStreet = (TextView) findViewById(R.id.cookStreet);
        ImageView cookEmail = (ImageView) findViewById(R.id.cookEmail);
        ImageView cookPhone = (ImageView) findViewById(R.id.cookSms);
        ImageView cookWhatsapp = (ImageView) findViewById(R.id.cookPhone);

        CheckedTextView foodItemVega = (CheckedTextView) findViewById(R.id.vegaCheckmark);
        CheckedTextView foodItemVegan = (CheckedTextView) findViewById(R.id.veganCheckmark);

        //Waarden van views laten zien op scherm
        Log.i(LOG_TAG, "Data geven aan de views");
        name.setText(getIntent().getStringExtra("foodName"));
        price.setText(getIntent().getStringExtra("foodPrice"));
        description.setText(getIntent().getStringExtra("foodDescription"));
        date.setText(getIntent().getStringExtra("foodDate"));
        allergens.setText(getIntent().getStringExtra("foodAllergies"));
        cook.setText(getIntent().getStringExtra("foodCook"));
        cookCity.setText(getIntent().getStringExtra("foodCity"));
        cookStreet.setText(getIntent().getStringExtra("foodStreet"));

        //change button value based on vega/vegan
        if (getIntent().getBooleanExtra("foodVega", false)) {
            Log.d(LOG_TAG, "Food is vega");
            foodItemVega.setCheckMarkDrawable(R.drawable.icon_check_circle);
        } else {
            Log.d(LOG_TAG, "Food is niet vega");
            foodItemVega.setCheckMarkDrawable(R.drawable.icon_uncheck_circle);
        }
        if (getIntent().getBooleanExtra("foodVegan", false)) {
            Log.d(LOG_TAG, "Food is vegan");
            foodItemVegan.setCheckMarkDrawable(R.drawable.icon_check_circle);
        } else {
            Log.d(LOG_TAG, "Food is niet vegan");
            foodItemVegan.setCheckMarkDrawable(R.drawable.icon_uncheck_circle);
        }

        //Image inladen met Picasso
        Log.d(LOG_TAG, "Image proberen te laden" + getIntent().getStringExtra("image_resource"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("image_resource"))
                .into(image);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Log.d(LOG_TAG, "Terug naar hoofdscherm");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.rochet.eatsmart;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.rochet.eatsmart.Model.Meal;

public class FoodDetailActivity extends AppCompatActivity {

    private final String LOG_TAG = FoodDetailActivity.class.getSimpleName();

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Init view
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        food_description = findViewById(R.id.food_description);
        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_image = findViewById(R.id.img_food);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);


        // Get food from Intent
        Intent intent = this.getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(MealListAdapter.MealViewHolder.EXTRA_MEAL);
            //Type object = (Type) bundle.getSerializable("KEY");
            Meal myMeal = (Meal) bundle.getSerializable("meal_selected");
            Log.d(LOG_TAG, "meal qui a lance cette activity : " + myMeal.getName());
            loadFoodDetail(myMeal);
        }
    }

    private void loadFoodDetail(Meal myMeal) {
        // Put initial data into the food detail.
        collapsingToolbarLayout.setTitle(myMeal.getName());
        // todo implementer les get et seter du lmeal
        //food_price.setText(myMeal.getPrice());
        food_name.setText(myMeal.getName());
        //food_description.setText(myMeal.getDescription());

    }
}

package com.example.rochet.eatsmart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.rochet.eatsmart.Database.Database;
import com.example.rochet.eatsmart.Model.Meal;
import com.example.rochet.eatsmart.Model.Order;

public class FoodDetailActivity extends AppCompatActivity {


    private final String LOG_TAG = FoodDetailActivity.class.getSimpleName();

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    Meal currentMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Init view
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Database(getBaseContext()).addToCart(new Order(
                         currentMeal.getId(),
                        currentMeal.getName(),
                        numberButton.getNumber(),
                        currentMeal.getPrice()
                ));
                Toast.makeText(FoodDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();

                Snackbar mySnackbar =  Snackbar.make(v, "Have you finish ?", Snackbar.LENGTH_LONG);
                mySnackbar.setAction("Cart List", new SnackBarListener()); //new MyUndoListener()
                mySnackbar.show();

            }
        });


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
            currentMeal = (Meal) bundle.getSerializable("meal_selected");
            Log.d(LOG_TAG, "meal qui a lance cette activity : " + currentMeal.getName());
            loadFoodDetail(currentMeal);
        }
    }

    private void loadFoodDetail(Meal myMeal) {
        // Put initial data into the food detail.
        collapsingToolbarLayout.setTitle(myMeal.getName());
        // todo implementer les image
        food_name.setText(myMeal.getName());
        food_price.setText(myMeal.getPrice().toString());
        food_description.setText(myMeal.getDescription());
        // Return the id of the drawable you want to access.
        int imgDrawableId = getResources().getIdentifier(
                "com.example.rochet.eatsmart:drawable/" + myMeal.getImage(), null, null);
        // Sets the image in the imageview
        food_image.setImageResource(imgDrawableId);
    }

    public class SnackBarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            // Code to execute when the action snakbar button is pressed
            Intent cartIntent = new Intent(FoodDetailActivity.this, CartActivity.class);
            startActivity(cartIntent);

        }
    }
}

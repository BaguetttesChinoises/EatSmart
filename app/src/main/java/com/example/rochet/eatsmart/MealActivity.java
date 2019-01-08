package com.example.rochet.eatsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.rochet.eatsmart.Model.Category;
import com.example.rochet.eatsmart.Model.Meal;

import java.util.LinkedList;

public class MealActivity extends AppCompatActivity {
    private static final String LOG_TAG = MealActivity.class.getSimpleName();
    private LinkedList<Meal> mMealList = new LinkedList<>();


    private RecyclerView mRecycler_meal;
    private MealListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(MealActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra(CategoryListAdapter.MenuViewHolder.EXTRA_CATEGORY);
        //Type object = (Type) bundle.getSerializable("KEY");
        Category myCategory = (Category) bundle.getSerializable("category_selected");
        Log.d(LOG_TAG, "category qui a lance cette activity : " + myCategory.getName());

        loadMenu();

        // Create recycler view.
        mRecycler_meal = findViewById(R.id.recycler_meal);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new MealListAdapter(this, mMealList);
        // Connect the adapter with the recycler view.
        mRecycler_meal.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecycler_meal.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loadMenu() {
        // Put initial data into the category list.
        for (int i = 0; i < 10; i++) {
            mMealList.add(new Meal("meal" + i, ""));
        }
    }

}

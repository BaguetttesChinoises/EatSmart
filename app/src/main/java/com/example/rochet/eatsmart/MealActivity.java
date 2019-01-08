package com.example.rochet.eatsmart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rochet.eatsmart.Model.Category;
import com.example.rochet.eatsmart.Model.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MealActivity extends AppCompatActivity {
    private static final String LOG_TAG = MealActivity.class.getSimpleName();
    private LinkedList<Meal> mMealList = new LinkedList<>();
    Toast toast;

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

        //Check de la connexion Internet
        if (!isConnected()) {
            toast = Toast.makeText(getApplicationContext(), "Pas de connexion !", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        loadMenu();



    }

    private void loadMenu() {
        // Put initial data into the category list.
        /*for (int i = 0; i < 10; i++) {
            mMealList.add(new Meal("meal" + i, ""));
        }*/

        //Requête API
        //Intent intent = getIntent();
        //String strCode = intent.getStringExtra("codeMenu");
        new MealActivity.FetchTask().execute("https://eatsmartapi.herokuapp.com/dishes?code=AZERTY");
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



    //Récupère l'API
    private class FetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            toast = Toast.makeText(getApplicationContext(), "Chargement", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            String stringUrl = strings[0];
            Request request = new Request.Builder().url(stringUrl).build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                toast = Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT);
                toast.show();
            } else {

                try {
                    //Parsing JSON
                    JSONObject response = new JSONObject(s);
                    //List<String> list = new ArrayList<String>();

                    //Menu Name
                    JSONArray array = response.getJSONArray("menue");
                    for(int i = 0 ; i < array.length() ; i++) {
                        //list.add(array.getJSONObject(i).getString("name"));
                        mMealList.add(new Meal(array.getJSONObject(i).getString("name"), ""));
                    }

                    // Create recycler view.
                    mRecycler_meal = findViewById(R.id.recycler_meal);
                    // Create an adapter and supply the data to be displayed.
                    mAdapter = new MealListAdapter(MealActivity.this, mMealList);
                    // Connect the adapter with the recycler view.
                    mRecycler_meal.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    mRecycler_meal.setLayoutManager(new LinearLayoutManager(MealActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            toast = Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

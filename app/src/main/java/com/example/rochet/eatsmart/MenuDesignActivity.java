package com.example.rochet.eatsmart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rochet.eatsmart.Model.Category;
import com.google.android.gms.wallet.Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuDesignActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private LinkedList<Category> mCategoryList = new LinkedList<>();

    private RecyclerView mRecycler_menu;
    private CategoryListAdapter mAdapter;

    Toast toast;
    public static String strCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_design);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(MenuDesignActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Check de la connexion Internet
        if (!isConnected()) {
            toast = Toast.makeText(getApplicationContext(), "Pas de connexion !", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        loadMenu();


        /*
        // Create recycler view.
        mRecycler_menu = findViewById(R.id.recycler_menu);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new CategoryListAdapter(this, mCategoryList);
        // Connect the adapter with the recycler view.
        mRecycler_menu.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecycler_menu.setLayoutManager(new LinearLayoutManager(this));
        */

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void loadMenu() {

        //Requête API
        Intent intent = getIntent();
        strCode = intent.getStringExtra("codeMenu");
        new MenuDesignActivity.FetchTask().execute("https://eatsmartapi.herokuapp.com/categories?code=" + strCode);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            // Handle the order action
            Intent cartIntent = new Intent(MenuDesignActivity.this, CartActivity.class);
            startActivity(cartIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Récupère l'API
    private class FetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            
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
                    JSONArray array = response.getJSONArray("dishes");
                    for(int i = 0 ; i < array.length() ; i++) {
                        //list.add(array.getJSONObject(i).getString("name"));
                        mCategoryList.add(new Category(
                                Integer.parseInt(array.getJSONObject(i).getJSONObject("category").getString("id")),
                                array.getJSONObject(i).getJSONObject("category").getString("name"),
                                array.getJSONObject(i).getJSONObject("category").getString("image")));
                    }

                    // Create recycler view.
                    mRecycler_menu = findViewById(R.id.recycler_menu);
                    // Create an adapter and supply the data to be displayed.
                    mAdapter = new CategoryListAdapter(MenuDesignActivity.this, mCategoryList);
                    // Connect the adapter with the recycler view.
                    mRecycler_menu.setAdapter(mAdapter);
                    // Give the recycler view a default layout manager.
                    mRecycler_menu.setLayoutManager(new LinearLayoutManager(MenuDesignActivity.this));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

package com.example.rochet.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.rochet.eatsmart.Database.Database;
import com.example.rochet.eatsmart.Model.Meal;
import com.example.rochet.eatsmart.Model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private static final String LOG_TAG = CartActivity.class.getSimpleName();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    TextView txtTotalPrice;
    Button btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Init
        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        loadListFood();
    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        cartAdapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(cartAdapter);

        //Caculate total price
        int total = 0;
        for (Order order : cart) {
            total += (order.getPrice()) * (Integer.parseInt(order.getQuantity()));
        }
        Locale locale = new Locale("fr", "FR"); // ATTENTION
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));

    }

    public void cleanDatabase(View view) {
        new Database(getBaseContext()).cleanCart();
        Intent menuIntent = new Intent(CartActivity.this, MenuDesignActivity.class);
        startActivity(menuIntent);

    }
}

package com.example.rochet.eatsmart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rochet.eatsmart.Database.Database;
import com.example.rochet.eatsmart.Model.Order;

public class MainActivity extends AppCompatActivity {

    Button buttonCommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Page de bienvenue
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        requestAppPermissions();
*/

        buttonCommencer = findViewById(R.id.buttonCommencer);
    }

    void goToScanActivity(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void goToPayActivity(View view) {
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
    }

    public void goToMenuDesignActivity(View view) {
        Intent intent = new Intent(this, MenuDesignActivity.class);
        startActivity(intent);
    }

    public void goToMealActivity(View view) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
    }

    public void goToFoodDetailActivity(View view) {
        Intent intent = new Intent(this, FoodDetailActivity.class);
        startActivity(intent);
    }

   /* private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            new Database(getBaseContext()).addToCart(new Order(
                    "1",
                    "name",
                    "1",
                    "12"
            ));
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1234); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }*/
}

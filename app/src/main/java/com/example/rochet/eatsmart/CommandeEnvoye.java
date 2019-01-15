package com.example.rochet.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CommandeEnvoye extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_envoye);
    }

    public void retourMenuPrincipale(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

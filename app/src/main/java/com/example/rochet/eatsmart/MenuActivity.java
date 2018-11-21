package com.example.rochet.eatsmart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuActivity extends AppCompatActivity {

    ListView listMenu;
    TextView txtTest;

    String[] list_menu_NAME;
    String[] list_menu_DESCRIPTION = {"Pour une faim", "Grande faim", "Très très faim"};

    Toast toast;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Initialisation
        Toast toast = Toast.makeText(getApplicationContext(), "Chargement", Toast.LENGTH_LONG);
        txtTest = (TextView) findViewById(R.id.textTest);
        listMenu = (ListView) findViewById(R.id.listMenu);

        //Check de la connexion Internet
        if (!isConnected()) {
            toast = Toast.makeText(getApplicationContext(), "Pas de connexion !", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        //Requête API
        Intent intent = getIntent();
        String strCode = intent.getStringExtra("codeMenu");
        new FetchTask().execute("https://eatsmartapi.herokuapp.com/dishes?code=" + strCode);

    }

    //Liste de menu custom
    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_menu_NAME.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.element_menu_layout, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView_Name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_Description = (TextView) view.findViewById(R.id.textView_description);

            imageView.setImageResource(R.drawable.ic_launcher_foreground);
            textView_Name.setText(list_menu_NAME[i]);
            textView_Description.setText(list_menu_DESCRIPTION[i]);
            return view;
        }
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
                    List<String> list = new ArrayList<String>();

                    //Menu Name
                    JSONArray array = response.getJSONArray("menue");
                    for(int i = 0 ; i < array.length() ; i++){
                        list.add(array.getJSONObject(i).getString("name"));
                    }
                    list_menu_NAME = list.toArray(new String[list.size()]);

                    //Menu created_at
                    array = response.getJSONArray("menue");
                    list.clear();
                    for(int i = 0 ; i < array.length() ; i++){
                        list.add(array.getJSONObject(i).getString("created_at"));
                    }
                    list_menu_DESCRIPTION = list.toArray(new String[list.size()]);

                    MenuAdapter menuAdapter = new MenuAdapter();
                    listMenu.setAdapter(menuAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            toast = Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

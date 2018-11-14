package com.example.rochet.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    ListView listMenu;

    String[] list_menu_NAME = {"Menu Best Of", "Menu Maxi Best Of", "Menu Golden"};
    String[] list_menu_DESCRIPTION = {"Pour une faim", "Grande faim", "Très très faim"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listMenu = (ListView) findViewById(R.id.listMenu);
        MenuAdapter menuAdapter = new MenuAdapter();
        listMenu.setAdapter(menuAdapter);
    }

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
}

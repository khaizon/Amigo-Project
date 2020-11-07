package com.example.amigoproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // for testing purposes, Main Activity file should be compiled after every view has been completed.

    ListView suggestedListView;
    List suggestedList = new ArrayList();
    ArrayAdapter adapter;
    ChipNavigationBar menu_bottom;
    private Button seeAllButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use this to switch between activity views
        setContentView(R.layout.activity_main);
        this.setTitle("Explore");

        seeAllButton = findViewById(R.id.seeAllButton);
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProjectListings();
            }
        });

        menu_bottom = findViewById(R.id.navigation);
        menu_bottom.setItemSelected(0, true);

        suggestedListView = findViewById(R.id.suggestedListView);

        suggestedList.add("Orange");
        suggestedList.add("123");
        suggestedList.add("456");
        suggestedList.add("789");
        suggestedList.add("2343e");
        suggestedList.add("2343e");
        suggestedList.add("2sdsa");
        suggestedList.add("23dsfdsfe");

        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,suggestedList);
        suggestedListView.setAdapter(adapter);


    }

    private void openProjectListings() {
        Intent intent = new Intent(this, ExploreProjectListings.class);
        startActivity(intent);

    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

}


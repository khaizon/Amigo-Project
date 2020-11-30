package com.example.amigoproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
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
    FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button seeAllButton;

    String s1[] = {"Learn Python with Me :)", "two", "three", "four", "five"};
    String s2[] = {"one", "two", "three", "four", "five"};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use this to switch between activity views
        setContentView(R.layout.explore_homepage);
        this.setTitle("Explore");

//        WindowInsetsController.hide(WindowInsets.Type.navigationBars());

        seeAllButton = findViewById(R.id.seeAllButton);
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProjectListings();
            }
        });

        menu_bottom = findViewById(R.id.navigation);
        menu_bottom.setItemSelected(0, true);

        if (savedInstanceState == null){
            menu_bottom.setItemSelected(R.id.explore, true);
            fragmentManager = getSupportFragmentManager();
            DiscoverFragment discoverFragment = new DiscoverFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, discoverFragment)
                    .commit();
        }

        menu_bottom.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch(id) {
                    case R.id.explore:
                        fragment = new DiscoverFragment();
                        break;
                    case R.id.chats:
                        fragment = new ChatsFragment();
                        break;
                    case R.id.projects:
                        fragment = new MyProjectsFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }

                if (fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
                else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });

        MyAdapter myAdapter = new MyAdapter(s1,s2);

        recyclerView = findViewById(R.id.suggestedRecycler);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//
//        suggestedListView = findViewById(R.id.suggestedListView);
//
//        suggestedList.add("Orange");
//        suggestedList.add("123");
//        suggestedList.add("456");
//        suggestedList.add("789");
//        suggestedList.add("2343e");
//        suggestedList.add("2343e");
//        suggestedList.add("2sdsa");
//        suggestedList.add("23dsfdsfe");
//
//        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,suggestedList);
//        suggestedListView.setAdapter(adapter);


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


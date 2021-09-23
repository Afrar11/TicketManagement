package com.example.mobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout1);
        viewPager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Unresolved"));
        tabLayout.addTab(tabLayout.newTab().setText("Resolved"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        Context context = getApplicationContext();
        DBHandler db = new DBHandler(context);
/*
        db.addInfo("New pending ticket", "pending");
        db.addInfo("New resolved ticket", "resolved");
        db.addInfo("New unresolved ticket", "unresolved");*/

        List<Ticket> listOfTickets = db.fetchTicketsForType("pending");

        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();

        initRecyclerView((ArrayList<Ticket>) listOfTickets);
    }

    private void initRecyclerView(ArrayList<Ticket> listOfTickets){
        setContentView(R.layout.fragment_pending);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_pending);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listOfTickets,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setContentView(R.layout.activity_main);

    }

}
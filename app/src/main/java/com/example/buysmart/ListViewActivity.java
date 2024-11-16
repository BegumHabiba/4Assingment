package com.example.buysmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    private ListView ListView;
    private Button btnExpandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // Initialize views
        ListView = findViewById(R.id.listView);
        btnExpandableList = findViewById(R.id.btnExpandableList);

        // Categories for shopping items (Electronics, Clothing, Home Appliances)
        String[] categories = getResources().getStringArray(R.array.categories);

        // Product arrays based on category
        String[] electronics = getResources().getStringArray(R.array.electronics);
        String[] clothing = getResources().getStringArray(R.array.clothing);
        String[] homeAppliances = getResources().getStringArray(R.array.home_appliances);

        // Image resources for the list items
        Integer[] imgId = {
                R.drawable.h,
                R.drawable.a,
                R.drawable.bb
        };

        // Pass category and respective products as subtitles to the adapter
        ItemAdapter adapter = new ItemAdapter(this, categories, electronics, clothing, homeAppliances, imgId);
        ListView.setAdapter(adapter);

        // Navigate to ExpandableListViewActivity on button click
        btnExpandableList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewActivity.this, ExpandableListViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
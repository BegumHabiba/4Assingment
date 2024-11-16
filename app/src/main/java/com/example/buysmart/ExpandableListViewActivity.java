package com.example.buysmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> groupData;
    HashMap<String, List<String>> childData;
    int lastExpandedGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        expandableListView = findViewById(R.id.expandableListView);

        prepareData();

        ProductAdapter adapter = new ProductAdapter(this, groupData, childData);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            String groupText = groupData.get(groupPosition);
            Toast.makeText(getApplicationContext(), groupText, Toast.LENGTH_SHORT).show();
            return false;
        });

        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            String groupText = groupData.get(groupPosition);
            Toast.makeText(getApplicationContext(), groupText + " is Collapsed", Toast.LENGTH_SHORT).show();
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String childText = childData.get(groupData.get(groupPosition)).get(childPosition);
            Toast.makeText(getApplicationContext(), childText, Toast.LENGTH_SHORT).show();
            return false;
        });

        expandableListView.setOnGroupExpandListener(groupPosition -> {
            if (lastExpandedGroup != -1 && lastExpandedGroup != groupPosition) {
                expandableListView.collapseGroup(lastExpandedGroup);
            }
            lastExpandedGroup = groupPosition;
        });
    }

    private void prepareData() {
        String[] groupArray = getResources().getStringArray(R.array.categories);

        String[] electronicsArray = getResources().getStringArray(R.array.electronics);
        String[] clothingArray = getResources().getStringArray(R.array.clothing);
        String[] homeAppliancesArray = getResources().getStringArray(R.array.home_appliances);

        groupData = new ArrayList<>();
        childData = new HashMap<>();

        for (String group : groupArray) {
            groupData.add(group);

            List<String> childList = new ArrayList<>();

            if (group.equals("Electronics")) {
                for (String product : electronicsArray) {
                    childList.add(product);
                }
            } else if (group.equals("Clothing")) {
                for (String product : clothingArray) {
                    childList.add(product);
                }
            } else if (group.equals("Home Appliances")) {
                for (String product : homeAppliancesArray) {
                    childList.add(product);
                }
            }

            childData.put(group, childList);
        }
    }
}
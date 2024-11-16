package com.example.buysmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ProductAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupData; // Categories or groups
    private HashMap<String, List<String>> childData; // Products under each category

    // Constructor
    public ProductAdapter(Context context, List<String> groupData, HashMap<String, List<String>> childData) {
        this.context = context;
        this.groupData = groupData;
        this.childData = childData;
    }

    @Override
    public int getGroupCount() {
        return groupData.size(); // Return number of categories
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupData.get(groupPosition)).size(); // Return number of products in each category
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition); // Return category name
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupData.get(groupPosition)).get(childPosition); // Return product name
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition; // ID for the group (category)
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition; // ID for the child (product)
    }

    @Override
    public boolean hasStableIds() {
        return false; // No stable IDs in this case
    }

    // Inflate the group layout for categories (group_item.xml)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, parent, false);
        }

        // Set category name to the group TextView
        TextView groupTextView = convertView.findViewById(R.id.groupTextView);
        groupTextView.setText(groupData.get(groupPosition)); // Set category name

        return convertView;
    }

    // Inflate the child layout for products (child_item.xml)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, parent, false);
        }

        // Set product name to the child TextView
        TextView childTextView = convertView.findViewById(R.id.childTextView);
        childTextView.setText(childData.get(groupData.get(groupPosition)).get(childPosition)); // Set product name

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true; // Make the child items selectable
    }
}
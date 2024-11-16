package com.example.buysmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    private final String[] titles; // Categories
    private final String[] electronics; // Electronics products
    private final String[] clothing; // Clothing products
    private final String[] homeAppliances; // Home appliances products
    private final Integer[] images; // Images
    private final Context context;

    public ItemAdapter(Context context, String[] titles, String[] electronics, String[] clothing, String[] homeAppliances, Integer[] images) {
        this.context = context;
        this.titles = titles;
        this.electronics = electronics;
        this.clothing = clothing;
        this.homeAppliances = homeAppliances;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_view, parent, false);
        }

        // Get references to views
        TextView titleView = convertView.findViewById(R.id.title);
        TextView subtitleView = convertView.findViewById(R.id.subtitle);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        // Set the data for title (category) and subtitle (products)
        titleView.setText(titles[position]);

        // Display the corresponding products for each category
        switch (position) {
            case 0: // Electronics
                subtitleView.setText(String.join(", ", electronics)); // Join electronics products with commas
                break;
            case 1: // Clothing
                subtitleView.setText(String.join(", ", clothing)); // Join clothing products with commas
                break;
            case 2: // Home Appliances
                subtitleView.setText(String.join(", ", homeAppliances)); // Join home appliances products with commas
                break;
            default:
                subtitleView.setText("");
        }

        // Set the image for the category
        imageView.setImageResource(images[position]);

        return convertView;
    }
}
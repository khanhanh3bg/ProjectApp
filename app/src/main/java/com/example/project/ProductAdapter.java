package com.example.project;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    List<Product> data;
    Activity activity;

    public ProductAdapter(List<Product> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = activity.getLayoutInflater().inflate(R.layout.itemproduct, null);
        ImageView image = view.findViewById(R.id.in_url);
        TextView name = view.findViewById(R.id.in_name);
        TextView quantity = view.findViewById(R.id.in_quantity);
        Product product = data.get(i);
        name.setText(product.getName());
        quantity.setText(String.valueOf(product.getQuantity()));
        Picasso.get().load(product.getUrl()).into(image);

        return view;
    }
}

package com.labs.covid19africatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Country> {

    public ListViewAdapter(@NonNull Context context, List<Country> countries) {
        super(context, 0, countries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tvCountryName, tvNumberInfected, tvRecovered;
        ImageView ivFlag;

        tvCountryName = listItemView.findViewById(R.id.tvCountryName);
        tvNumberInfected = listItemView.findViewById(R.id.tvInfectedPeople);
        tvRecovered = listItemView.findViewById(R.id.tvRecovered);
        ivFlag = listItemView.findViewById(R.id.imageView);
        Country currentCountry = getItem(position);

        tvCountryName.setText(currentCountry.getCountryName());
        tvNumberInfected.setText(Integer.toString(currentCountry.getCases()));
        tvRecovered.setText(Integer.toString(currentCountry.getRecovered()));

        Glide.with(getContext())
                .load(currentCountry.getCountryFlag())
                .apply(new RequestOptions().override(240, 160))
                .into(ivFlag);

        return listItemView;
    }
}

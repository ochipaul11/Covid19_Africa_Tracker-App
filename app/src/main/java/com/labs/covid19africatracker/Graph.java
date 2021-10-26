package com.labs.covid19africatracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {
    private PieChart pieChart;
    private Country country;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
        pieChart = findViewById(R.id.pieChart);

        Intent intent = getIntent();
        country = (Country) intent.getSerializableExtra("clickedObject");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(country.getCountryName());
        }
        getChartData();
    }

    private void getChartData() {
        String labels[] = {"Recovered","Active", "Deaths"};
        int figures[] = {country.getRecovered(),
                country.getActive(),
                country.getDeaths()};

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < labels.length; i++) {
            pieEntries.add(new PieEntry(figures[i], labels[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "People");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(pieDataSet);

        Description description = new Description();
        description.setText("COUNTRY ANALYSIS: " + country.getCountryName().toUpperCase());
        pieChart.setDescription(description);
        pieChart.setData(data);
        pieChart.animateXY(2000, 2000);
        pieChart.invalidate();


    }
}

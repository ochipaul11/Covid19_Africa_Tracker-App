package com.labs.covid19africatracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFrag extends Fragment {

    private PieChart pieChart;

    public GraphFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        pieChart = view.findViewById(R.id.pieChart);

        getChartData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

    }

    void getChartData() {
      //Bundle bundle = getArguments();
     // System.out.println("..............."+ bundle.getInt("index"));
//        int index = bundle.getInt("index");

        String xAxis[] = {"Cases", "Active", "Recovered", "Deaths"};
        int yAxix[] = {MainActivity.countries.get(MainActivity.index).getCases(),
                MainActivity.countries.get(MainActivity.index).getActive(),
                MainActivity.countries.get(MainActivity.index).getRecovered(),
                MainActivity.countries.get(MainActivity.index).getDeaths()};

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < xAxis.length; i++) {
            pieEntries.add(new PieEntry(yAxix[i], xAxis[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Country Analysis: "+MainActivity.countries.get(MainActivity.index).getCountryName().toUpperCase());
        PieData data = new PieData(pieDataSet);

        pieChart.setData(data);
        pieChart.invalidate();


    }
}

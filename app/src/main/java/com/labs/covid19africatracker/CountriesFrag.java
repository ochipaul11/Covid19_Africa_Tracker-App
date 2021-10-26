package com.labs.covid19africatracker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesFrag extends Fragment {

    ListView lvCountries;
    ProgressDialog progressDialog;
    CountriesFragListener countriesFragListener;

    public CountriesFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        lvCountries = view.findViewById(R.id.lvCountries);
        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), Graph.class);
                intent.putExtra("clickedObject", MainActivity.countries.get(i));
                startActivity(intent);
            }
        });
        getCountriesList();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        countriesFragListener = (CountriesFragListener) activity;
    }

    private void getCountriesList() {
//GET countries using their ISO2 codes
        progressDialog = ProgressDialog.show(getContext(), "Loading...", null, true, true);

        String url = "https://corona.lmao.ninja/v2/countries/DZ,AO,BJ,BW,BF,BI,CM,CV,CF,KM,CD,DJ,EG,GQ,ER,ET,GA,GM,GH,GN,GW,CI,KE,LS,LR,LY,MG,MW,ML,MR,MU,MA,MZ,NA,NE,NG,CG,RE,RW,SH,ST,SN,SC,SL,SO,ZA,SS,SD,SZ,TZ,TG,TN,UG,EH,ZM,ZW";
        MainActivity.countries = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                if (response != null) {

                    try {

                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryFlag = data.getJSONObject("countryInfo");

                            MainActivity.countries.add(new Country(
                                    data.getString("country"), data.getInt("cases"),
                                    data.getInt("active"), data.getInt("deaths"),
                                    data.getInt("recovered"), countryFlag.getString("flag")
                            ));
                        }

                        MainActivity.listViewAdapter = new ListViewAdapter(Objects.requireNonNull(getActivity()), MainActivity.countries);
                        lvCountries.setAdapter(MainActivity.listViewAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
    public interface CountriesFragListener {

        public void openGraph();
    }
}

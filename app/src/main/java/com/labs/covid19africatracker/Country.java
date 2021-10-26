package com.labs.covid19africatracker;

import java.io.Serializable;

public class Country implements Serializable {

    private String countryName;
    private int cases;
    private int active;
    private int deaths;
    private int recovered;
    private String countryFlag;

    public Country(String countryName, int cases, int active, int deaths, int recovered, String countryFlag) {
        this.countryName = countryName;
        this.cases = cases;
        this.active = active;
        this.deaths = deaths;
        this.recovered = recovered;
        this.countryFlag = countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCases() {
        return cases;
    }

    public int getActive() {
        return active;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public String getCountryFlag() {
        return countryFlag;
    }
}

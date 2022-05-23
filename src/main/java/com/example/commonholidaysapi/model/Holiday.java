package com.example.commonholidaysapi.model;

import java.time.LocalDate;

public class Holiday {

    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean global;
    private String[] counties;

    public Holiday(LocalDate date, String localName, String name,
                   String countryCode, boolean global, String[] counties) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.global = global;
        this.counties = counties;
    }

    public Holiday(LocalDate date, String localName) {
        this.date = date;
        this.localName = localName;
    }

    public Holiday() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String[] getCounties() {
        return counties;
    }

    public void setCounties(String[] counties) {
        this.counties = counties;
    }
}

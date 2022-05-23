package com.example.commonholidaysapi.model;

import java.time.LocalDate;
import java.util.Objects;

public class HolidayResult {

    private LocalDate date;
    private String firstCountryHoliday;
    private String secondCountryHoliday;

    public HolidayResult(LocalDate date, String firstCountryHoliday, String secondCountryHoliday) {
        this.date = date;
        this.firstCountryHoliday = firstCountryHoliday;
        this.secondCountryHoliday = secondCountryHoliday;
    }

    public HolidayResult() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFirstCountryHoliday() {
        return firstCountryHoliday;
    }

    public void setFirstCountryHoliday(String firstCountryHoliday) {
        this.firstCountryHoliday = firstCountryHoliday;
    }

    public String getSecondCountryHoliday() {
        return secondCountryHoliday;
    }

    public void setSecondCountryHoliday(String secondCountryHoliday) {
        this.secondCountryHoliday = secondCountryHoliday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayResult that = (HolidayResult) o;
        return Objects.equals(date, that.date) && Objects.equals(firstCountryHoliday, that.firstCountryHoliday) && Objects.equals(secondCountryHoliday, that.secondCountryHoliday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, firstCountryHoliday, secondCountryHoliday);
    }
}

package com.kxkriszna.xpense;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SingleItem implements Serializable {

    private String itemNameSingle;
    private double itemPriceSingle;
    private String dateAdded;

    public SingleItem(String itemNameObject, double itemPriceObject) {
        this.itemNameSingle = itemNameObject;
        this.itemPriceSingle = itemPriceObject;
        dateAdded = getCurrentDate();
    }

    public SingleItem(){}

    public String getItemName() {
        return itemNameSingle;
    }

    public double getItemPrice() {
        return itemPriceSingle;
    }

    public static String getCurrentDate(){
        String pattern = "dd/MM/yyyy";

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);

        // Get the today date using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String todayAsString = df.format(today);

        // Print the result!
        return todayAsString;
    }

    public String getDate(){
        return dateAdded;
    }



}
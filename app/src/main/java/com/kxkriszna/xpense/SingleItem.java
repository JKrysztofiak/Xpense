package com.kxkriszna.xpense;

public class SingleItem {

    private String itemNameSingle;
    private double itemPriceSingle;

    public SingleItem(String itemNameObject, double itemPriceObject) {
        this.itemNameSingle = itemNameObject;
        this.itemPriceSingle = itemPriceObject;
    }

    public SingleItem(){}

    public String getItemName() {
        return itemNameSingle;
    }

    public double getItemPrice() {
        return itemPriceSingle;
    }

}
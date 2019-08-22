package com.kxkriszna.xpense;

import java.io.Serializable;

public class SingleItem implements Serializable {

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
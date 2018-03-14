package com.txmpay.ewallet.ui.payment;

/**
 * created by czh on 2018-03-14
 */

public class PriceItem {
    private int price;
    private boolean isCheck;
    private String text;

    public PriceItem(int price,String unit) {
        this.price = price;
        this.isCheck=false;
        this.text=price+unit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package com.example.miniprojetevents.step.event;

public class CustomItemsCat {
    private String spinnerText;
    private int spinnerImage;

    public CustomItemsCat(String spinnerText, int spinnerImage) {
        this.spinnerText = spinnerText;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerText() {
        return spinnerText;
    }

    public int getSpinnerImage() {
        return spinnerImage;
    }
}

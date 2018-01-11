package com.example.lesze.myapplication.model;

/**
 * Created by lesze on 06.12.2017.
 */

public class MyFont {

    String kolor;
    String rozmiar;

    public MyFont(String kolor, String rozmiar) {
        this.kolor = kolor;
        this.rozmiar = rozmiar;
    }

    public MyFont() {
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public String getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }
}

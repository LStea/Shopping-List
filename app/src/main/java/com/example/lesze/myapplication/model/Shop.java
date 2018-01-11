package com.example.lesze.myapplication.model;

/**
 * Created by lesze on 19.11.2017.
 */

public class Shop {

    private String nazwa;
    private int id;
    private double cena;
    private double ilosc;

    public Shop() {
    }

    public Shop(int id, String produkt, double cena, double ilosc) {
        this.id = id;
        this.nazwa = produkt;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    public Shop(String produkt, double cena, double ilosc) {
        this.nazwa = produkt;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    public static String[] zakupy = {
            "aaa",
            "bbb",
    };

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String produkt) {
        this.nazwa = produkt;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package com.example.lauribohm.uusivaiheviikko8;

public class Bottle {

    private String nimi, tekija, koko;
    private int energia, hinta;

    public Bottle (String n, String m, int e, String k, int h) {
        nimi = n;
        tekija = m;
        energia = e;
        koko = k;
        hinta = h;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTekija() {
        return tekija;
    }

    public int getEnergia() {
        return energia;
    }

    public String getKoko() {
        return koko;
    }

    public int getHinta() {
        return hinta;
    }
}



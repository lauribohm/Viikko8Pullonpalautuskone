package com.example.lauribohm.uusivaiheviikko8;

import android.content.Context;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser pO = new BottleDispenser();

    public static BottleDispenser getInstance() {

        return pO;
    }

    ArrayList<Bottle> pulloOsoittaja = new ArrayList<Bottle>();
    ArrayList<Receipt> kuittiOsoittaja = new ArrayList<Receipt>();

    int pulloja;
    int i;
    int valinta;

    public BottleDispenser() {

        pulloja = 6;

        String Nimi = "n", Tekija = "t", Koko = "k";
        int Energia = 0, Hinta = 0;

        for (i = 0; i < pulloja; i++) {

            if (i == 0) {
                Nimi = "PepsiMax";
                Tekija = "Pepsi";
                Energia = 3;
                Koko = "0.50";
                Hinta = 3;
            } else if (i == 1) {
                Nimi = "Pepsi";
                Tekija = "Pepsi";
                Energia = 3;
                Koko = "0.50";
                Hinta = 3;
            } else if (i == 2) {
                Nimi = "Coca-ColaZero";
                Tekija = "Coca-Cola";
                Energia = 3;
                Koko = "0.50";
                Hinta = 3;
            } else if (i == 3) {
                Nimi = "Coca-ColaZero";
                Tekija = "Coca-Cola";
                Energia = 3;
                Koko = "1.50";
                Hinta = 2;
            } else if (i == 4) {
                Nimi = "FantaZero";
                Tekija = "Hartwall";
                Energia = 3;
                Koko = "0.33";
                Hinta = 3;
            } else if (i == 5) {
                Nimi = "FantaZero";
                Tekija = "Hartwall";
                Energia = 3;
                Koko = "0.50";
                Hinta = 2;
            }

            pulloOsoittaja.add(i, new Bottle(Nimi, Tekija, Energia, Koko, Hinta));
        }
    }

    public int ostaPullo(String pullonnimi, String pullonkoko, int rahaa) {

        int a;
        int i = 0;
        int k = 0;
        int hinta;

        for (a = 0; a <= (pulloja - 1); a++) {

            if (pullonnimi.equals(pulloOsoittaja.get(a).getNimi()) && pullonkoko.equals(pulloOsoittaja.get(a).getKoko())) {

                hinta = pulloOsoittaja.get(a).getHinta();

                if ((rahaa - hinta) > 0) {
                    rahaa = (rahaa - hinta);
                    i = 2;
                    k = a;
                    break;
                } else {
                    i = -1;
                    break;
                }
            } else {
                i = 0;
            }
        }

        if (i == 2) {
            pulloja = pulloja - 1;
            kuittiOsoittaja.add(new Receipt(pulloOsoittaja.get(k).getNimi(), pulloOsoittaja.get(k).getTekija(), pulloOsoittaja.get(k).getEnergia(), pulloOsoittaja.get(k).getKoko(), pulloOsoittaja.get(k).getHinta()));

            pulloOsoittaja.remove(k);
            System.out.println("Rahaa: " + rahaa);
            return (rahaa);
        } else if (i == -1) {
            //jos ei tarpeeksi rahaa
            return (-1);
        } else {
            //jos loppunut
            return (-2);
        }
    }
}

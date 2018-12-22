package com.example.lauribohm.uusivaiheviikko8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText text;
    Button kuitti;
    Button lisaa;
    TextView money;
    TextView money1;
    TextView money2;
    int rahaa = 0;

    private Spinner spinner;

    Context context;

    BottleDispenser Po = new BottleDispenser().getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        kuitti = (Button) findViewById(R.id.Lopeta);
        lisaa = (Button) findViewById(R.id.Lisaa);
        money = (TextView) findViewById(R.id.money);
        money1 = (TextView) findViewById(R.id.money1);
        money2 = (TextView) findViewById(R.id.money2);

        context = MainActivity.this;

        final ArrayList<String> kuitille = new ArrayList<>();
        int kuitinNumero = 0;

        int rahetta = getIntent().getIntExtra("rahet", 0);
        System.out.println(rahetta);
        //System.out.println(rahaa);
        rahaa = rahetta;
        System.out.println(rahaa);
        String massii = String.valueOf(rahaa);
        money.setText(massii);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// SPINNER --> VALIKKO PULLOILLE

        spinner = findViewById(R.id.spinner);

        final List<String> pullotLista = new ArrayList<>();

        pullotLista.add(0,"Valitse Pullo: ");
        pullotLista.add("PepsiMax 0.50 l 3 euroa");
        pullotLista.add("Pepsi 0.50 l 3 euroa");
        pullotLista.add("Coca-ColaZero 0.50 l 3 euroa");
        pullotLista.add("Coca-ColaZero 1.50 l 2 euroa");
        pullotLista.add("FantaZero 0.33 l 3 euroa");
        pullotLista.add("FantaZero 0.50 l 2 euroa");

        ArrayAdapter<String> pulloja = new ArrayAdapter(this,android.R.layout.simple_spinner_item, pullotLista);

        pulloja.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(pulloja);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text.setText(" ");

                if (parent.getItemAtPosition(position).equals("Valitse Pullo")) {


                }

                else {

                    String valinta = parent.getItemAtPosition(position).toString();
                    String[] valinnat = valinta.split(" ");

                    valinnat[0].toString().trim();
                    valinnat[1].toString().trim();

                    int osto = 0;

                    osto = Po.ostaPullo(valinnat[0], valinnat[1], rahaa);
                    System.out.println("rahaa: " + osto);

                    if (osto == -1) {

                        text.setText("Syötä rahaa ensin!");
                    }

                    else if (osto == -2) {

                        text.setText("Valintasi on loppunut:(");
                    }

                    else {
                        text.setText(" KACHUNK! " + valinnat[0] + " " + valinnat[1] + " tipahti masiinasta");
                        rahaa = osto;
                        String masi = String.valueOf(osto);
                        money.setText(masi);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// LISÄTÄÄN RAHAA --> SEEKBAR

        lisaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, lisaarahaa.class);
                intent.putExtra("massia", rahaa);
                startActivity(intent);

            }

        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// KUITIN TULOSTUS

        kuitti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tiedosto = "KuittiOstoksista.txt";

                try {

                    OutputStreamWriter kirjoittaja = new OutputStreamWriter(context.openFileOutput("hei",Context.MODE_PRIVATE));

                    kirjoittaja.write("KUITTI OSTOKSISTA:\n");
                    for (int i = 0; i < Po.kuittiOsoittaja.size(); i++) {

                        kirjoittaja.write("NIMI: " + Po.kuittiOsoittaja.get(i).getNimi() + "\n"
                        + "KOKO: " + Po.kuittiOsoittaja.get(i).getKoko() + "\n" + "ENERGIA: " + Po.kuittiOsoittaja.get(i).getEnergia() + "\n"
                        + "TEKIJÄ: " + Po.kuittiOsoittaja.get(i).getTekija() + "\n" + "HINTA: " + Po.kuittiOsoittaja.get(i).getHinta() + "\n\n");
                    }

                    kirjoittaja.write("Rahaa palautettu: " + rahaa + "\n"
                    + "Kiitos käytöstä!!");


                    kirjoittaja.close();

                    text.setText("Kiitos käytöstä, kuitti tulostettu:)");
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}

package com.example.lauribohm.uusivaiheviikko8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class lisaarahaa extends Activity {

    TextView text;
    ProgressBar progressbar;
    SeekBar bar;
    TextView tallenna;
    int palautettava;

    //BottleDispenser Po = new BottleDispenser().getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lisaarahaa);

        text = (TextView) findViewById(R.id.money);
        bar = (SeekBar) findViewById(R.id.seekBar);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        tallenna = (TextView) findViewById(R.id.tallennaraha);

        final int rahetta = getIntent().getIntExtra("massia", 0);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressbar.setProgress(progress);
                text.setText(" " + progress + " euroa");
                palautettava = progress;
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
        });

    tallenna.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View v) {

            palautettava = (palautettava + rahetta);

            Intent intent = new Intent(lisaarahaa.this, MainActivity.class);
            intent.putExtra("rahet", palautettava);
            startActivity(intent);

        }
    });

    }
}

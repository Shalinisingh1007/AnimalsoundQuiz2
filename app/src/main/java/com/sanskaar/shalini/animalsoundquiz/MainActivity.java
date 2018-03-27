package com.sanskaar.shalini.animalsoundquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {


    //define spinner;
    Spinner sp;

    int selectedLanguage=0;
    //make string array
    String languages[]={"English","Italian","Hindi"};

    //define arrary adapter
    ArrayAdapter<String> adapter;

    //definig media player
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=(Spinner)findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,languages);
        sp.setAdapter(adapter);


        //set spinner method
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        //Button method
        Button playBt=(Button)findViewById(R.id.play);
        playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent=new Intent(MainActivity.this,sound.class);
                myIntent.putExtra("language",selectedLanguage);
                startActivity(myIntent);

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        mp=MediaPlayer.create(MainActivity.this,R.raw.welcome);
        mp.start();
    }

    @Override
    public void onBackPressed() {
        if(MainActivity.this!=null){
            MainActivity.this.mp.stop();
           // MainActivity.this.mp.release();
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        if(MainActivity.this!=null){
            MainActivity.this.mp.stop();
            MainActivity.this.mp.release();
        }

        super.onPause();
    }
}

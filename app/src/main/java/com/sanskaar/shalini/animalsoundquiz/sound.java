package com.sanskaar.shalini.animalsoundquiz;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class sound extends AppCompatActivity {

    //Arrays of sounds and pictures
    int[][] animals={{R.drawable.cat,R.raw.cat},{R.drawable.cow,R.raw.cow},{R.drawable.dog,R.raw.dog},
            {R.drawable.duck,R.raw.duck},{R.drawable.elephant,R.raw.elephant},{R.drawable.frog,R.raw.frog},
            {R.drawable.goat,R.raw.goat},{R.drawable.lion,R.raw.lion},{R.drawable.monkey,R.raw.monkey},
            {R.drawable.mosquito,R.raw.mosquito},{R.drawable.peacock,R.raw.pecock},
            {R.drawable.pig,R.raw.pigs},{R.drawable.rooster,R.raw.rooster},{R.drawable.sheep,R.raw.sheep},
            {R.drawable.horse,R.raw.horse},{R.drawable.crow,R.raw.crow},{R.drawable.donkey,R.raw.donkey}
            ,{R.drawable.owl,R.raw.owl},{R.drawable.snake,R.raw.snake},{R.drawable.pigeon,R.raw.pigeon}};
    int []question={R.raw.enhlishq,R.raw.italianqs,R.raw.hindiq};//Questions
    int[]rightAns={R.raw.englishra,R.raw.italianras,R.raw.hindira};//sound to be played when user get correct answer
    int[]wrongAns={R.raw.englishwa,R.raw.italianwa,R.raw.hindiwa};//sound to be played when user get wrong answer


    boolean play=true;
    boolean audioFinished=false;

    //Media player
    MediaPlayer mpSound;

    int selectedLanguage,r,w,p;
    ImageButton topBt;
    ImageButton bottomBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Bundle bundle=getIntent().getExtras();
        selectedLanguage=bundle.getInt("language");

    }


    @Override
    protected void onResume() {
        super.onResume();

        topBt=(ImageButton)findViewById(R.id.imageButton1);
        bottomBt=(ImageButton)findViewById(R.id.imageButton2);

        select_question();


        //Top image selected

        topBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topBt.setClickable(false);
                bottomBt.setClickable(false);
                if(p==0){
                    playAudio(rightAns[selectedLanguage],10);
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            select_question();
                        }
                    },5000);
                }
                else {
                    playAudio(wrongAns[selectedLanguage],10);
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            topBt.setClickable(true);
                            bottomBt.setClickable(true);
                            playAudio(animals[r][1],200);
                        }
                    },5000);

                }
            }
        });

        //bottom image selected
        bottomBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topBt.setClickable(false);
                bottomBt.setClickable(false);
                if(p==1){
                    playAudio(rightAns[selectedLanguage],10);
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            select_question();
                        }
                    },5000);

                }
                else {
                    playAudio(wrongAns[selectedLanguage],10);
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            topBt.setClickable(true);
                            bottomBt.setClickable(true);
                            playAudio(animals[r][1],200);
                        }
                    },5000);

                }
            }
        });

    }

    //Method to select a question

    private void select_question(){

        r= random_select(animals.length);
        w= random_select(animals.length);
        while (r==w){
            w= random_select(animals.length);
        };
        p=random_select(2);
        askQuestion(r,w,p);

    }

    //method to randomly select a number
    private int random_select(int i){
        Random rd=new Random();
        int randomNo=rd.nextInt(i);
        return randomNo;
    }


    //Ask question function
    private void askQuestion(final int rightoption, int wrongoption, final int position){

        if(position==0){
            topBt.setImageResource(animals[rightoption][0]);
            bottomBt.setImageResource(animals[wrongoption][0]);
        }
        else {
            bottomBt.setImageResource(animals[rightoption][0]);
            topBt.setImageResource(animals[wrongoption][0]);
        }
        playAudio(question[selectedLanguage],10);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                topBt.setClickable(true);
                bottomBt.setClickable(true);
                playAudio(animals[rightoption][1],200);
            }
        },5000);


    }



    //Methods to play audio
    private void playAudio(int idSound,float vol){
        if(mpSound!=null) {

            mpSound.stop();
            mpSound.release();
        }
        mpSound=MediaPlayer.create(sound.this,idSound);
        mpSound.setVolume(vol,vol);
        if(play){
            mpSound.start();
        }

    }

    @Override
    public void onBackPressed() {
        play=false;
        if(sound.this.mpSound!=null) {

            sound.this.mpSound.stop();
            finish();

        }
        sound.this.finish();

        sound.super.onBackPressed();



    }

    @Override
    protected void onPause() {
        play=false;
        if(sound.this.mpSound!=null) {

            sound.this.mpSound.stop();
            //finish();

        }
        sound.this.finish();
        super.onPause();
    }
}

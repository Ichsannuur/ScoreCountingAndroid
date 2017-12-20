package com.ics.skortenismeja;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class WinnerActivity extends AppCompatActivity {
    Intent i;
    TextView textWin;
    TextToSpeech tts;
    ImageView imageWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        i = getIntent();
        imageWin = (ImageView)findViewById(R.id.imageWinner);
        textWin = (TextView)findViewById(R.id.textWin);

        //Animation
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        imageWin.setAnimation(animation);


        if (i.getStringExtra("teamWin").equals("Team 1 Win")){
            textWin.setText(i.getStringExtra("teamWin"));
            tts = new TextToSpeech(WinnerActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR){
                        tts.setLanguage(Locale.US);
                        tts.speak("Congratulation Team 1 Win",TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
            });
        }else{
            textWin.setText(i.getStringExtra("teamWin"));
            tts = new TextToSpeech(WinnerActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR){
                        tts.setLanguage(Locale.US);
                        tts.speak("Congratulation Team 2 Win",TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
            });
        }
    }
}

class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
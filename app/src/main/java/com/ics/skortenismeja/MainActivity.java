package com.ics.skortenismeja;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    TextView score1,score2,finalS,deuce;
    TextToSpeech tts,tta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);
        finalS = (TextView) findViewById(R.id.finalS);
        deuce = (TextView)findViewById(R.id.deuce);
        //Animation
        final Animation animation = new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        score1.setAnimation(animation);
        score2.setAnimation(animation);

        final int finalScore = 21;
        final int deuceScore = 20;
        final int firstScore1 = 0;
        int firstScore2 = 0;

        score1.setText(firstScore1 + "");
        score2.setText(firstScore2 + "");
        finalS.setText(finalScore + "");
        deuce.setText(deuceScore + "");

        score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scoreA = Integer.parseInt(score1.getText().toString());

                for (int i = scoreA;i < Integer.parseInt(finalS.getText().toString());i++){
                    score1.setText((scoreA + 1) + "");
                    tta = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            if (i != TextToSpeech.ERROR){
                                tta.setLanguage(Locale.US);
                                tta.speak("Team 1 ,"+score1.getText().toString()+ ",And," + "Team 2 ," + score2.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                            }
                        }
                    });

                    if (score1.getText().toString().equals(finalS.getText().toString() + "")){
                        Toast.makeText(MainActivity.this, "Team 1 Win", Toast.LENGTH_SHORT).show();
                        Intent z = new Intent(MainActivity.this,WinnerActivity.class);
                        z.putExtra("teamWin","Team 1 Win");
                        startActivity(z);
                        finish();

                    }else if (score1.getText().toString().equals(deuce.getText().toString()) && score2.getText().equals(deuce.getText().toString())){
                        Toast.makeText(MainActivity.this, "Deuce", Toast.LENGTH_SHORT).show();
                        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                if (i != TextToSpeech.ERROR){
                                    tta.shutdown();
                                    tts.setLanguage(Locale.US);
                                    tts.speak("Deuce",TextToSpeech.QUEUE_FLUSH,null);
                                }
                            }
                        });

                        int fscore = Integer.parseInt(finalS.getText().toString()) + 1;
                        finalS.setText(fscore + "");
                        int dscore = Integer.parseInt(deuce.getText().toString()) + 1;
                        deuce.setText(dscore + "");
                    }
                }
            }
        });

        score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scoreA = Integer.parseInt(score2.getText().toString());
                for (int i = scoreA;i < Integer.parseInt(finalS.getText().toString());i++){
                    score2.setText((scoreA + 1) + "");
                    tta = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            if (i != TextToSpeech.ERROR){
                                tta.setLanguage(Locale.US);
                                tta.speak("Team 1 ,"+score1.getText().toString()+ ",And," + "Team 2 ," + score2.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                            }
                        }
                    });

                    if (score2.getText().toString().equals(finalS.getText().toString() + "")){
                        Toast.makeText(MainActivity.this, "Team 2 Win", Toast.LENGTH_SHORT).show();
                        Intent x = new Intent(MainActivity.this,WinnerActivity.class);
                        x.putExtra("teamWin","Team 2 Win");
                        startActivity(x);
                        finish();

                    }else if (score1.getText().toString().equals(deuce.getText().toString()) && score2.getText().equals(deuce.getText().toString())){
                        Toast.makeText(MainActivity.this, "Deuce", Toast.LENGTH_SHORT).show();
                        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                if (i != TextToSpeech.ERROR){
                                    tta.shutdown();
                                    tts.setLanguage(Locale.US);
                                    tts.speak("Deuce",TextToSpeech.QUEUE_FLUSH,null);
                                }
                            }
                        });

                        int fscore = Integer.parseInt(finalS.getText().toString()) + 1;
                        finalS.setText(fscore + "");
                        int dscore = Integer.parseInt(deuce.getText().toString()) + 1;
                        deuce.setText(dscore + "");
                    }
                }
            }
        });
    }

}

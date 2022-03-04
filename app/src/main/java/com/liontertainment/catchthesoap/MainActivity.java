package com.liontertainment.catchthesoap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewScore;
    TextView textViewTime;
    int score;
    ImageView soap1;
    ImageView soap2;
    ImageView soap3;
    ImageView soap4;
    ImageView soap5;
    ImageView soap6;
    ImageView soap7;
    ImageView soap8;
    ImageView soap9;
    ImageView[] imageArrayDizisi;
    Handler handlertoolu;
    Runnable runnabletoolu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize ediyoruz burda
        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        soap1 = findViewById(R.id.imageView);
        soap2 = findViewById(R.id.imageView2);
        soap3 = findViewById(R.id.imageView3);
        soap4 = findViewById(R.id.imageView4);
        soap5 = findViewById(R.id.imageView5);
        soap6 = findViewById(R.id.imageView6);
        soap7 = findViewById(R.id.imageView7);
        soap8 = findViewById(R.id.imageView8);
        soap9 = findViewById(R.id.imageView9);
        imageArrayDizisi = new ImageView[] {soap1,soap2,soap3,soap4,soap5,soap6,soap7,soap8,soap9};
        hideImages ();

        score = 0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                textViewTime.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time is Up");
                handlertoolu.removeCallbacks(runnabletoolu);
                for (ImageView images : imageArrayDizisi){
                    images.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder yenioyunalarmi = new AlertDialog.Builder(MainActivity.this);
                yenioyunalarmi.setTitle("Restart the game?");
                yenioyunalarmi.setMessage("Are u sure dude??");
                yenioyunalarmi.setPositiveButton("Yess", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart güncel aktiviteyi bitirip yeniden başlatır çok kullanılan bir restart metodu değil
                        Intent intentbitane = getIntent();
                        finish();
                        startActivity(intentbitane);
                    }
                });
                yenioyunalarmi.setNegativeButton("Noo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over:)", Toast.LENGTH_SHORT).show();
                    }
                });
                yenioyunalarmi.show();

            }
        }.start();

    }


    public void increasescore (View view){
        score++;
        textViewScore.setText("Score is: " + score);
    }

    public void increasescore2 (View view){
        score = score + 2;
        textViewScore.setText("SScore is: " + score);
    }

    public void hideImages () {

        handlertoolu = new Handler();
        runnabletoolu = new Runnable() {
            @Override
            public void run() {
                for (ImageView images : imageArrayDizisi){
                    images.setVisibility(View.INVISIBLE);
                }

                //RANDOM kullanımı
                Random rastgele = new Random();
                int i = rastgele.nextInt(9);
                imageArrayDizisi[i].setVisibility(View.VISIBLE);

                handlertoolu.postDelayed(this, 400);
            }
        };

        handlertoolu.post(runnabletoolu);

    }



}
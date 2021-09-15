package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView TimertextView;
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    Boolean counterISActive = false;
    Button goButton;

    public void resetTimer(){
        TimertextView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterISActive = false;
    }

    public void buttonClicked(View view) {
        if (counterISActive) {
           resetTimer();
        } else {
            counterISActive = true;
            seekBar.setEnabled(false);
            goButton.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.clicks);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }


    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft -(minutes * 60);
        String secondString = Integer.toString((seconds));
        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        TimertextView.setText(Integer.toString(minutes) + ":" + secondString);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        TimertextView = findViewById(R.id.TimetextView);
        goButton = findViewById(R.id.G0button);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
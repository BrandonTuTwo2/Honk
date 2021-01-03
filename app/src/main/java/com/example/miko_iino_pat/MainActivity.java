package com.example.miko_iino_pat;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView update;
    private int counter = 0;
    private String stringForm;
    private boolean clicked = false;
    private TextView instruct;
    private Button button;
    private SoundPool soundPool;
    private TextView hnkCounter;
    private int patSounds, chunchunTest, honkSound;
    private Animation fadeOut;
    private Animation fadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instruct = (TextView) findViewById(R.id.instructions);
        hnkCounter = (TextView) findViewById(R.id.honkcounter);
        fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeIn =  new AlphaAnimation(0.0f,1.0f);
        update = (TextView) findViewById(R.id.events);
        fadeOut.setDuration(1000);
        fadeIn.setDuration(1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAtrrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAtrrib)
                    .build();

        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        patSounds = soundPool.load(this, R.raw.pat, 1);
        chunchunTest = soundPool.load(this, R.raw.chunchunmaru, 2);
        honkSound = soundPool.load(this, R.raw.honksound,3);
        button = (Button) findViewById(R.id.honk_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clicked) {
                    instruct.startAnimation(fadeOut);
                    instruct.setVisibility(View.INVISIBLE);
                    clicked = true;
                }
                counter++;
                if (counter == 10) {
                    setEvent("you reached 10 HONKS");
                } else if(counter == 30) {
                    setEvent("You reached 30 HONKS such dedication to the craft!");
                } else if(counter ==  50) {
                    setEvent("You reached 50 HONKS have you annoyed everyone in the room yet?");
                } else if(counter == 69) {
                    setEvent("You reached 69 HONKS Nice");
                } else if(counter == 100) {
                    setEvent("You reached 100 HONKS you can stop now im running out of things to say");
                } else if(counter == 150) {
                    setEvent("bruh");
                } else if(counter == 243) {
                    setEvent("You reached 243 HONKS uh how was your [INSERT_EVENT_HERE]?");
                } else if(counter == 300) {
                    setEvent("You reached 300 HONKS are you that bored?");
                } else if(counter == 500) {
                    setEvent("You reached 500 HONKS....wait a minute are you...?");
                } else if (counter == 650) {
                    setEvent("You reached 650 HONKS ARE YOU A GODDAMN GOOSE?");
                } else if (counter == 800) {
                    setEvent("You reached 8000 HONKS Naw I don't think A goose has that kind of attention span");
                } else if(counter == 1000) {
                    setEvent("You reached 1000  HONKS.... or maybe they do anyway there are no more comments from here on out enjoy silly Goose");
                    update.setVisibility(View.INVISIBLE);
                }
                stringForm = String.valueOf(counter);
                hnkCounter.setText(stringForm);
                soundPool.play(honkSound,1,1,0,0,1);
            }
        });



    }

    private void setEvent(String comment) {
        update.startAnimation(fadeOut);
        update.setText(comment);
        update.startAnimation(fadeIn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

}

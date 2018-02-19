package org.bubulescu.soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTouch;
    private SoundPool soundPool;
    private int soundId;
    private boolean isLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        tvTouch = findViewById(R.id.tvTouch);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.drop, 1);
    }

    private void setupListeners() {

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                isLoaded = true;
            }
        });

        tvTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (isLoaded) {
                    soundPool.play(soundId, 1.0f, 1.0f, 1, 3, 1.0f);
                }

                return false;
            }
        });
    }
}

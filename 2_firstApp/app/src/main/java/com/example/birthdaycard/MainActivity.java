package com.example.birthdaycard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music_file);

        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = false;
            public void onClick(View v) {
                if (!isPlaying) {
                    button.setText(R.string.StopMusic);
                    mediaPlayer.start();
                    isPlaying = true;
                }
                else {
                    button.setText(R.string.PlayMusic);
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    isPlaying = false;
                }
            }
        });
    }

}
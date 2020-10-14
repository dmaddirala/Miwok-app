package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        final int[] songs = {R.raw.color_red,
                R.raw.color_green,
                R.raw.color_brown,
                R.raw.color_gray,
                R.raw.color_black,
                R.raw.color_white,
                R.raw.color_dusty_yellow,
                R.raw.color_mustard_yellow,};

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "weṭeṭṭi",R.drawable.color_red));
        words.add(new Word("green", "chokokki",R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki",R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi",R.drawable.color_gray));
        words.add(new Word("black", "kululli",R.drawable.color_black));
        words.add(new Word("white", "kelelli",R.drawable.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, songs[i] );

                //Starts the audio file
                mediaPlayer.start();

                //Setup a Listener on media player , so that we can stop and release
                //the song once its done playing
                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("TAG", "Paused");
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}

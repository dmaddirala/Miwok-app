package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        final int[] songs = {R.raw.family_father,
                R.raw.family_mother,
                R.raw.family_son,
                R.raw.family_daughter,
                R.raw.family_older_brother,
                R.raw.family_younger_brother,
                R.raw.family_older_sister,
                R.raw.family_younger_sister,
                R.raw.family_grandmother,
                R.raw.family_grandfather,};

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("father", "әpә",R.drawable.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister));
        words.add(new Word("grandmother", "ama",R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, songs[i] );

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

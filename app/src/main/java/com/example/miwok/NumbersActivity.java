package com.example.miwok;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) || (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };
        final int[] songs = {R.raw.number_one,
                R.raw.number_two,
                R.raw.number_three,
                R.raw.number_four,
                R.raw.number_five,
                R.raw.number_six,
                R.raw.number_seven,
                R.raw.number_eight,
                R.raw.number_nine,
                R.raw.number_ten,};

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten));


//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");

//        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, songs[i]);

                    //Starts the audio file
                    mediaPlayer.start();

                    //Setup a Listener on media player , so that we can stop and release
                    //the song once its done playing
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("TAG", "Paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("TAG", "Resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("TAG", "Stopped");
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("TAG", "Destroyed");
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
        audioManager.abandonAudioFocus(afChangeListener);
    }

    public ArrayList<String> reverse(ArrayList<String> words) {
        String temp;
        int i = 0;
        int j = words.size() - 1;

        while (i < j) {
            temp = words.get(i);
            words.set(i, words.get(j));
            words.set(j, temp);
            i++;
            j--;
        }
        return words;
    }

}

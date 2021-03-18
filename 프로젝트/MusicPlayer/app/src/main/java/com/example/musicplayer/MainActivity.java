package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView play, prev, next, imageView;
    TextView songTitle;
    SeekBar mSeekBarTime, mSeekBarVol;
    static MediaPlayer mMediaPlayer;
    private Runnable runnable;
    private AudioManager mAudioManager;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // initializing views

        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        songTitle = findViewById(R.id.songTitle);
        imageView = findViewById(R.id.imageView);
        mSeekBarTime = findViewById(R.id.seekBarTime);
        mSeekBarVol = findViewById(R.id.seekBarVol);

        // creating an ArrayList to store our songs

        final ArrayList<Integer> songs = new ArrayList<>();

        songs.add(0, R.raw.jin_abyss);   // music 넣기. mp3 파일 찾아서
        songs.add(1, R.raw.jin_tonight);
        songs.add(2, R.raw.bts_23);
        songs.add(3, R.raw.bts_lovemyself);
        songs.add(4, R.raw.jk_ending);
        songs.add(5, R.raw.bts_springday);
        songs.add(6, R.raw.jin_epiphany);
        songs.add(7, R.raw.car_homesweet);
        songs.add(8, R.raw.v_four);

        // initializing mediaplayer

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));

        // seekbar volume

        int maxV = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSeekBarVol.setMax(maxV);
        mSeekBarVol.setProgress(curV);

        mSeekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // above seekbar volume

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.play_btn);
                }
                else {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.pause_btn);
                }
                SongNames();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer != null) {
                    play.setImageResource(R.drawable.pause_btn);
                }

                if(currentIndex < songs.size() - 1) {
                    currentIndex++;
                }
                else {
                    currentIndex = 0;
                }

                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlayer.start();
                SongNames();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer != null) {
                    play.setImageResource(R.drawable.pause_btn);
                }

                if(currentIndex > 0) {
                    currentIndex--;
                }
                else {
                    currentIndex = songs.size() - 1;
                }

                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlayer.start();
                SongNames();
            }
        });
    }

    // created a seperate section for all the names and then putting it in each view

    private void SongNames() {
        if(currentIndex == 0) {
            songTitle.setText("Jin - Abyss");
            imageView.setImageResource(R.drawable.abyss_image);
        }
        if(currentIndex == 1) {
            songTitle.setText("Jin - Tonight");
            imageView.setImageResource(R.drawable.tonight_image);
        }
        if(currentIndex == 2) {
            songTitle.setText("BTS - two! three!");
            imageView.setImageResource(R.drawable.twothree_image);
        }
        if(currentIndex == 3) {
            songTitle.setText("BTS - Answer : Love Myself");
            imageView.setImageResource(R.drawable.lovemyself_image);
        }
        if(currentIndex == 4) {
            songTitle.setText("JK - Ending Scene");
            imageView.setImageResource(R.drawable.ending_image);
        }
        if(currentIndex == 5) {
            songTitle.setText("BTS - Spring Day");
            imageView.setImageResource(R.drawable.springday_image);
        }
        if(currentIndex == 6) {
            songTitle.setText("Jin - Epiphany");
            imageView.setImageResource(R.drawable.epiphany_image);
        }
        if(currentIndex == 7) {
            songTitle.setText("Car, the garden - Home Sweet Home");
            imageView.setImageResource(R.drawable.home_image);
        }
        if(currentIndex == 8) {
            songTitle.setText("RM & V - 4 O'Clock");
            imageView.setImageResource(R.drawable.four_image);
        }

        // seekbar duration
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
            }
        });

        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekBarTime.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mMediaPlayer != null) {
                    try {
                        if(mMediaPlayer.isPlaying()) {
                            Message message = new Message();
                            message.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @SuppressLint("Handler Leak") Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            mSeekBarTime.setProgress(msg.what);
        }
    };
}
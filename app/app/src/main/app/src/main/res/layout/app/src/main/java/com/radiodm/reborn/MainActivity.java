package com.radiodm.reborn;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;

    String url1 = "https://sapircast.caster.fm:16091/AToYI";
    String url2 = "https://uk18freenew.listen2myradio.com:25177/stream";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.btnRadio1);
        Button b2 = findViewById(R.id.btnRadio2);

        b1.setOnClickListener(v -> play(url1));
        b2.setOnClickListener(v -> play(url2));
    }

    private void play(String url) {
        try {
            if (player != null) {
                player.stop();
                player.release();
            }

            player = new MediaPlayer();
            player.setDataSource(url);
            player.prepareAsync();

            player.setOnPreparedListener(MediaPlayer::start);

            Toast.makeText(this, "Memutar...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Gagal memutar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }
}

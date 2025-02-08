package com.akstudios.tictactoe2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {

    private TextView winnerTextView;
    private Button restartButton;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winnerTextView = findViewById(R.id.finalWinnerTextView);
        restartButton = findViewById(R.id.restartButton);
        restartButton.setEnabled(false);
        // Get the winner's name from the intent
        String winnerName = getIntent().getStringExtra("WINNER_NAME");
        winnerTextView.setText(winnerName + " is the Champion!");

        mediaPlayer = MediaPlayer.create(this, R.raw.win); // Ensure win.mp3 is in res/raw
        mediaPlayer.start();

        // Enable button after audio completes
        mediaPlayer.setOnCompletionListener(mp -> {
            restartButton.setEnabled(true);
            mediaPlayer.release(); // Release media player resources
        });

        // Restart button action
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(WinnerActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            // Apply animation (fade in and fade out)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

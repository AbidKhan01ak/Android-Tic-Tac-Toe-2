package com.akstudios.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GameManager gameManager;
    private Button playAgainButton;
    private TextView winnerTextView;
    private GridLayout gridLayout;

    private TextView yellowWinTextView;
    private TextView redWinTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews(); // Initialize all the UI components
        gameManager = new GameManager(this, playAgainButton, winnerTextView, gridLayout,  yellowWinTextView, redWinTextView); // Create the GameManager instance
    }
    private void initializeViews() {
        playAgainButton = findViewById(R.id.playAgainButton); // Button to restart the game
        winnerTextView = findViewById(R.id.winnerTextView); // TextView to display the game result
        gridLayout = findViewById(R.id.gridLayout); // GridLayout containing the game board
        yellowWinTextView = findViewById(R.id.yellowWinCount);
        redWinTextView = findViewById(R.id.redWinCount);
    }

    public void dropIn(View view) {
        gameManager.dropIn(view); // Delegate the drop-in action to the GameManager
    }

    public void playAgain(View view) {
        gameManager.resetGame(); // Reset the game through the GameManager
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gameManager != null) {
            gameManager.releaseMediaPlayer(); // Release media resources
        }
    }
}
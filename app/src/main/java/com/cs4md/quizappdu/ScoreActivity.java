package com.cs4md.quizappdu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {

    TextView ScoreLabelTV;
    TextView ScoreTV;
    Intent receivedIntent;
    int newScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        receivedIntent = getIntent();
        ScoreLabelTV = (TextView) findViewById(R.id.ScoreLabelTV);
        ScoreTV = (TextView) findViewById(R.id.ScoreTV);
        newScore = receivedIntent.getIntExtra("ScoreActivity", 0);
        String msg = ""+ newScore;
        ScoreTV.setText(msg);
    }
}

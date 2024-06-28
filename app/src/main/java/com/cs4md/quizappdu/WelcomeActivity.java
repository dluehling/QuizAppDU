package com.cs4md.quizappdu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcomeTV, PrevScoreTV;

    Button startBTN;
    //int previousScore;
    private SharedPreferences mPreferences;
    private String sharedPrefFile= "com.cs4md.android.quizappdu";
    private final String PREVIOUS_SCORE_KEY   = "SCORE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTV = (TextView) findViewById(R.id.welcomeTV);
        startBTN = (Button) findViewById(R.id.startBTN);
        PrevScoreTV = (TextView) findViewById(R.id.PrevScoreTV);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
//Read initial Value
        int previousScore = mPreferences.getInt(PREVIOUS_SCORE_KEY, 0);

//Set the Previous Score
        String PrevScoreMsg = "Previous Score: " + previousScore;
        PrevScoreTV.setText(PrevScoreMsg);

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //move to main activity
                //declare and initialize intent
                Intent myIntent = new Intent(WelcomeActivity.this, MainActivity.class);

                //put extra info in if needed
               myIntent.putExtra(getString(R.string.scoreactivityprev), previousScore);
                    //start the new activity
                startActivity(myIntent);
                }

        });


    }
}
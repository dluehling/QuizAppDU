package com.cs4md.quizappdu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //1. declaring my UI elements and other variables
    Button trueBTN, falseBTN, nextBTN, hintBTN;
    TextView questionTV, prevScoreValTV;
    Toast myToast;
    Toast hintToast;
    int score;
    String message;
    Question q1, q2, q3, q4, q5, currentQuestion;
    Question[] questions;
    int currentIndex;
    boolean qFlag;
    private SharedPreferences mPreferences;
    private String sharedPrefFile= "com.cs4md.android.quizappdu";
    private final String PREVIOUS_SCORE_KEY   = "SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. initializing variables & inflating UI elements
        trueBTN = (Button) findViewById(R.id.trueBTN);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        hintBTN = (Button) findViewById(R.id.hintBTN);
        questionTV = (TextView) findViewById(R.id.questionTV);
        //prevScoreValTV = (TextView) findViewById(R.id.prevScoreValueTV);

        score = 0;
        qFlag = false;
        message = "";
        q1 = new Question(getString(R.string.q1_text), true, getString(R.string.q1_hint_url), R.raw.q1sound);
        q2 = new Question(getString(R.string.q2_text), true, getString(R.string.q2_hint_url), R.raw.q2sound);
        q3 = new Question(getString(R.string.q3_text), false, getString(R.string.q3_hint_url), R.raw.q3sound);
        q4 = new Question(getString(R.string.q4_text), false, getString(R.string.q4_hint_url), R.raw.q4sound);
        q5 = new Question(getString(R.string.q5_text), true, getString(R.string.q5_hint_url), R.raw.q1sound);
        questions = new Question[]{q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQuestion = questions[currentIndex];
        questionTV.setText(currentQuestion.getqText());
        String btnText = getString(R.string.next);
        nextBTN.setText(btnText);


        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Read initial Value
        int prefScore = mPreferences.getInt(PREVIOUS_SCORE_KEY, 0);

        //3. do whatever you want your app to do with its UI elements
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!qFlag) {
                    qFlag = true;
                    //check if user answered the question correctly
                    //and adjust the score if correct
                    if (currentQuestion.isCorrectAnswer() == true) {
                        message = getString(R.string.correct);
                        score += 5;
                    } else {
                        message = getString(R.string.incorrect);
                    }

                    myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
                    myToast.show();
                }
                else {
                    message = "You already answered this question.";
                    myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!qFlag) {
                    qFlag = true;
                    //check whether they answered the question correctly
                    //and adjust the score if correct
                    if (currentQuestion.isCorrectAnswer() == false) {
                        message = getString(R.string.correct);
                        score += 5;
                    } else {
                        message = getString(R.string.incorrect);
                    }

                    myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                    myToast.show();
                } else {
                    //let user know they already answered the question via a toast
                    message = "You already answered this question.";
                    myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });

        //Added a hint button that brings up a webpage in a Webview and also a Toast with the URL
        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score -= 2;
                WebView myWebView = (WebView) findViewById(R.id.hintWV);
                myWebView.loadUrl(currentQuestion.getHintURL());
                //Toast not needed but shows the URL for the hint
                hintToast = Toast.makeText(MainActivity.this, currentQuestion.getHintURL(), Toast.LENGTH_SHORT);
                hintToast.show();
            }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                qFlag = false;

                //play a sound when moving to next question
                int tempSound = currentQuestion.getqSound();
                MediaPlayer questionsSound = MediaPlayer.create(MainActivity.this, tempSound);

                //Save the score to the preferences file
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putInt(PREVIOUS_SCORE_KEY, score);
                preferencesEditor.apply();

                if (currentIndex < questions.length) {
                    //advance and show the next question
                    currentQuestion = questions[currentIndex];
                    questionTV.setText(currentQuestion.getqText());
                    //play a sound
                    questionsSound.start();
                } else {
                    //move to score activity
                    //declare and initialize intent
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);

                    //put extra info in if needed
                    myIntent.putExtra(getString(R.string.scoreactivity), score);
                    myIntent.putExtra(getString(R.string.scoreactivityprev), prefScore);
                    //start the new activity
                    startActivity(myIntent);
                }
            }
        });
    }

    //The method currently not used - was for attempting an Intent
    private void openWebPage(String url) {
            
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


}
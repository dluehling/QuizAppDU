package com.cs4md.quizappdu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //1. declaring my UI elements and other variables
    Button trueBTN, falseBTN, nextBTN;
    TextView questionTV;
    Toast myToast;
    int score;
    String message;
    Question q1, q2, q3, q4, q5, currentQuestion;
    Question[] questions;
    int currentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. initializing variables & inflating UI elements
        trueBTN = (Button) findViewById(R.id.trueBTN);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        questionTV = (TextView) findViewById(R.id.questionTV);
        score = 0;
        message = "";
        q1 = new Question(getString(R.string.q1_text), true);
        q2 = new Question(getString(R.string.q2_text), true);
        q3 = new Question(getString(R.string.sequencing_is_always_repeating_code), false);
        q4 = new Question(getString(R.string.there_are_8_bytes_in_a_bit), false);
        q5 = new Question(getString(R.string.a_boolean_returns_either_true_or_false), true);
        questions = new Question[]{q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQuestion = questions[currentIndex];
        questionTV.setText(currentQuestion.getqText());

        //3. do whatever you want your app to do with its UI elements
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion.isCorrectAnswer()==true){
                  message = getString(R.string.correct);
                  score++;
                }
                else{
                    message = getString(R.string.incorrect);
                }


                myToast = Toast.makeText( MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();
                score++;
            }

            });

        falseBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQuestion.isCorrectAnswer()==false) {
                        message = getString(R.string.correct);
                        score++;
                    }
                    else{
                        message = getString(R.string.incorrect);
                    }
                    
                    myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                    myToast.show();
                }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if(currentIndex < questions.length){
                    //advance and show the next question
                    currentQuestion = questions[currentIndex];
                    questionTV.setText(currentQuestion.getqText());
                }
                else {
                    //move to score activity

                    //declare and initialize intent
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);

                    //put extra info in if needed
                    myIntent.putExtra(getString(R.string.scoreactivity), score);

                    //start the new activity
                    startActivity(myIntent);
                }
            }
        });
    }
}
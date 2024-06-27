package com.cs4md.quizappdu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {

    TextView ScoreLabelTV;
    TextView ScoreTV, PrevScoreTV;
    Intent receivedIntent;
    int newScore;
    Button emailBTN;
    int previousScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
//inflate UI elements
        receivedIntent = getIntent();
        ScoreLabelTV = (TextView) findViewById(R.id.ScoreLabelTV);
        ScoreTV = (TextView) findViewById(R.id.ScoreTV);
        emailBTN = (Button) findViewById(R.id.emailBTN);
        PrevScoreTV = (TextView) findViewById(R.id.prefScoreTV);
        newScore = receivedIntent.getIntExtra(getString(R.string.scoreactivity), 0);
        previousScore = receivedIntent.getIntExtra(getString(R.string.scoreactivityprev), 0);
        String msg = "" + newScore;
        ScoreTV.setText(msg);
        String PrevScoreMsg = "Previous Score: " + previousScore;
        PrevScoreTV.setText(PrevScoreMsg);



        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses = new String[] {"dana_l_uehling@mcpsmd.org"};
                String subject = getString(R.string.new_score_on_csp_quiz);
                String body = getString(R.string.i_just_scored_a)+ newScore + getString(R.string.on_dana_s_csp_quiz);
                composeEmail(addresses, subject, body );

            }
        });
    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

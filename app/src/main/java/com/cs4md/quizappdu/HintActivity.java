package com.cs4md.quizappdu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class HintActivity extends AppCompatActivity {
        private WebView webView;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           // setContentView(R.layout.webView);

            webView = (WebView) findViewById(R.id.hintWV);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(
                    "http://studio.code.org/home");

        }











    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

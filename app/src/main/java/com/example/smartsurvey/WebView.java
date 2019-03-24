package com.example.smartsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        android.webkit.WebView webView =(android.webkit.WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://goo.gl/forms/Su66CRTL27bfLtGv2");
    }
}

package com.somsubhra.ipfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Openweb extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_openweb);

        //Linking components:

        webView = findViewById(R.id.webView);

        //Opening URL:

        String url = getIntent().getStringExtra("URL");
        url = "https://" + url;
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    //Setting back button functionality:

    @Override
    public void onBackPressed() {
        if( webView.canGoBack() ) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}

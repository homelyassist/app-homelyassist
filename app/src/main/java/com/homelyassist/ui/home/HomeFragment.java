package com.homelyassist.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homelyassist.R;

public class HomeFragment extends Fragment {

    private WebView webView;
    private ImageView imageView;
    private TextView labelBeforeImage, textHome;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        webView = root.findViewById(R.id.webView);
        imageView = root.findViewById(R.id.imageView);
        labelBeforeImage = root.findViewById(R.id.labelBeforeImage);
        textHome = root.findViewById(R.id.textHome);
        progressBar = root.findViewById(R.id.progressBar);

        // Set up WebView
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new android.app.AlertDialog.Builder(getContext())
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> result.confirm())
                        .setOnCancelListener(dialog -> result.cancel())
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                new android.app.AlertDialog.Builder(getContext())
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> result.confirm())
                        .setNegativeButton(android.R.string.cancel, (dialog, which) -> result.cancel())
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                new android.app.AlertDialog.Builder(getContext())
                        .setMessage(message)
                        .setView(new android.widget.EditText(getContext()))
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> result.confirm())
                        .setNegativeButton(android.R.string.cancel, (dialog, which) -> result.cancel())
                        .create()
                        .show();
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Show the ProgressBar when the page starts loading
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);  // Keep WebView hidden until the page is loaded
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Hide the ProgressBar and show the WebView when the page is loaded
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });

        // Set click listener on ImageView
        imageView.setOnClickListener(v -> {
            // Load URL in WebView
            webView.loadUrl("https://homelyassist.com/assist/agriculture/search");

            // Hide other views and show ProgressBar
            labelBeforeImage.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            textHome.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
package com.homelyassist.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.homelyassist.R;

public class NotificationsFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        webView = rootView.findViewById(R.id.webview);

        // Enable JavaScript if needed
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Ensure links are opened within the WebView, not in an external browser
        webView.setWebViewClient(new WebViewClient());

        // Load the desired URL
        webView.loadUrl("http://192.168.1.65:8080/app/help");

        return rootView;
    }
}
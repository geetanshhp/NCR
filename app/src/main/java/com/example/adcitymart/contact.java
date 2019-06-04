package com.example.adcitymart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class contact extends Fragment
{
    WebView webView;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
            webView=view.findViewById(R.id.idwebview);
            webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSckeR1ob0ogOYYGyRpkgF8vXMJWiYd8am8IFib8td9P_ks-4w/viewform?usp=sf_link");
        view=inflater.inflate(R.layout.contactus,container,false);
        return view;
    }
}

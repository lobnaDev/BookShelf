package com.app.bookshelf.ui.TopStories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentBookInfoBinding;
import com.app.bookshelf.databinding.FragmentStoryDetailesBinding;


public class StoryDetailesFragment extends Fragment {
    FragmentStoryDetailesBinding binding;
    String articleUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoryDetailesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleUrl = getArguments().getString("url");
        // Configure related browser settings
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setLoadsImagesAutomatically(true);
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        binding.webview.setHorizontalScrollBarEnabled(false);
        // Zoom out if the content width is greater than the width of the viewport
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        // Enable responsive layout
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        binding.webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        binding.webview.getSettings().setBuiltInZoomControls(false);

        binding.webview.getSettings().setLoadWithOverviewMode(true);

        binding.webview.getSettings().setSupportZoom(false);
        binding.webview.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        binding.webview.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page
        binding.webview.getSettings().setDomStorageEnabled(true);
        binding.webview.getSettings().setDefaultTextEncodingName("utf-8");
        binding.webview.loadUrl(articleUrl);
    }
}
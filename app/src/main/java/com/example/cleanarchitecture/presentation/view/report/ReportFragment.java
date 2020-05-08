package com.example.cleanarchitecture.presentation.view.report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.FragmentReportBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BaseFragment;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class ReportFragment extends BaseFragment implements ReportContract.View {

    private static final String REPORT_ID = "report_id";

    @Inject
    ReportContract.Presenter presenter;

    private FragmentReportBinding binding;

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment newInstance(String reportId) {
        ReportFragment reportFragment = new ReportFragment();
        Bundle arguments = new Bundle();
        arguments.putString(REPORT_ID, reportId);
        reportFragment.setArguments(arguments);
        return reportFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_report;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentReportBinding.bind(view);
        initializeToolbar();
        Bundle arguments = getArguments();
        if (arguments != null) {
            presenter.setReportId(arguments.getString(REPORT_ID));
        }
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        binding.webView.setWebViewClient(null);
        binding.webView.setWebChromeClient(null);
        binding = null;
        super.onDestroyView();
        presenter.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_report, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_share:
                presenter.shareReport();
                break;
            case R.id.action_open:
                presenter.openReport();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTitle(String title) {
        binding.toolbar.setTitle(title);
    }

    @Override
    public void showSubtitle(String subtitle) {
        binding.toolbar.setSubtitle(subtitle);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void showReport(String url) {
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //To open hyperlink in existing WebView
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.progressReport.setVisibility(View.VISIBLE);
                binding.progressReport.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.progressReport.setVisibility(View.GONE);
            }
        });
        binding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                binding.progressReport.setProgress(newProgress);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                // Always grant permission since the app itself requires location
                // permission and the user has therefore already granted it
                callback.invoke(origin, true, false);
            }
        });
        //setting other settings
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webView.getSettings().setBuiltInZoomControls(false);
        binding.webView.getSettings().setLoadsImagesAutomatically(true);
        binding.webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        binding.webView.getSettings().setDomStorageEnabled(true);
        binding.webView.getSettings().setGeolocationEnabled(true);// For geolocation
        binding.webView.loadUrl(url);
    }

    @Override
    public void showShareReport(ReportDto report) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, report.getTitle());
        shareIntent.putExtra(Intent.EXTRA_TEXT, report.getUrl());
        if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    @Override
    public void showOpenReport(String url) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.setData(Uri.parse(url));
        if (viewIntent.resolveActivity(context.getPackageManager()) != null) {
            startActivity(viewIntent);
        }
    }

    private void initializeToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        baseActivity.setSupportActionBar(binding.toolbar);
        if (baseActivity.getSupportActionBar() != null) {
            baseActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

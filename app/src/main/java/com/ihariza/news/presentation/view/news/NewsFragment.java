package com.ihariza.news.presentation.view.news;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.google.android.material.snackbar.Snackbar;
import com.ihariza.news.R;
import com.ihariza.news.databinding.FragmentNewsBinding;
import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.view.base.BaseFragment;
import com.ihariza.news.presentation.view.main.MainRouterContract;
import com.ihariza.news.presentation.view.util.Constants;
import com.ihariza.news.presentation.view.util.RequestCountingIdlingResource;
import com.ihariza.news.presentation.view.widget.PaginationListener;

import java.util.List;

import javax.inject.Inject;

public class NewsFragment extends BaseFragment
        implements NewsContract.View, NewsAdapter.ReportListener {

    @Inject
    NewsContract.Presenter presenter;

    @Inject
    RequestManager glideRequestManager;

    private FragmentNewsBinding binding;
    private MainRouterContract router;
    private NewsAdapter adapter;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNewsBinding.bind(view);
        router = getRouter();
        initializeToolbar();
        initializeAdapter();
        initializeSwipeRefresh();
        initializeRecyclerView();
        if (savedInstanceState == null && adapter.getItemCount() == 0) {
            RequestCountingIdlingResource.increment();
            presenter.start();
        }
    }

    @Override
    public void onDestroyView() {
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
    protected void onBackPressed() {
        router.finishView();
    }

    @Override
    public void showNews(List<ReportDto> news) {
        RequestCountingIdlingResource.decrement();
        adapter.addAll(news);
    }

    @Override
    public void showRefreshedNews(List<ReportDto> news) {
        RequestCountingIdlingResource.decrement();
        adapter.refresh(news);
    }

    @Override
    public void openReport(String reportId) {
        router.showReport(reportId);
    }

    @Override
    public void showLoading() {
        adapter.showLoading();
    }

    @Override
    public void hideLoading() {
        adapter.hideLoading();
    }

    @Override
    public void hideRefresh() {
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onReportClicked(ReportDto report) {
        presenter.openReport(report);
    }

    private void initializeToolbar() {
        baseActivity.setSupportActionBar(binding.toolbar);
        if (baseActivity.getSupportActionBar() != null) {
            baseActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void initializeAdapter() {
        if (adapter == null) {
            adapter = new NewsAdapter(glideRequestManager, this);
        }
    }

    private void initializeSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeColors(ContextCompat.getColor(
                baseActivity, R.color.colorAccent));
        binding.swipeRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        PaginationListener paginationListener = new PaginationListener(
                layoutManager, Constants.NEWS_PAGE_SIZE) {
            @Override
            public void loadPage(int pageNumber) {
                presenter.getNewsPage(pageNumber);
            }

            @Override
            public boolean isLastPage() {
                return presenter.isNewsLastPage();
            }

            @Override
            public boolean isLoading() {
                return presenter.isNewsLoading();
            }
        };
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.addOnScrollListener(paginationListener);

        RecyclerViewPreloader<ReportDto> imagePreloader =
                new RecyclerViewPreloader<>(
                        glideRequestManager, adapter, adapter.getImageSizeProvider(),
                        Constants.PRELOAD_NEWS_IMAGES);
        binding.recyclerview.addOnScrollListener(imagePreloader);
    }

    private void onRefresh() {
        presenter.refreshNews();
    }
}

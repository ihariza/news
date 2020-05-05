package com.example.cleanarchitecture.presentation.view.news;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.FragmentNewsBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BaseFragment;
import com.example.cleanarchitecture.presentation.view.util.Constants;
import com.example.cleanarchitecture.presentation.view.widget.PaginationListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

public class NewsFragment extends BaseFragment implements NewsContract.View {

    @Inject
    NewsPresenter presenter;

    private FragmentNewsBinding binding;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNewsBinding.bind(view);
        setHasOptionsMenu(true);
        initializeToolbar();
        initializeAdapter();
        initializeSwipeRefresh();
        initializeRecyclerView();
        presenter.start();
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
        baseActivity.finish();
    }

    @Override
    public void showNews(List<ReportDto> news) {
        adapter.addAll(news);
    }

    @Override
    public void showRefreshedNews(List<ReportDto> news) {
        binding.swipeRefresh.setRefreshing(false);
        adapter.refresh(news);
    }

    @Override
    public void openReport(String reportId) {
        baseActivity.getNavigation().showReport(reportId);
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
    public void showError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
    }

    private void initializeToolbar() {
        baseActivity.setSupportActionBar(binding.toolbar);
        if (baseActivity.getSupportActionBar() != null) {
            baseActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void initializeAdapter() {
        adapter = new NewsAdapter(presenter);
    }

    private void initializeSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeColors(ContextCompat.getColor(
                baseActivity, R.color.colorAccent));
        binding.swipeRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.listNews.setLayoutManager(layoutManager);
        binding.listNews.setAdapter(adapter);
        binding.listNews.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            public void loadPage() {
                presenter.loadNewsPage();
            }

            @Override
            public boolean isLastPage() {
                return presenter.isLastPage();
            }

            @Override
            public boolean isLoading() {
                return presenter.isLoading();
            }

            @Override
            public int getPageSize() {
                return Constants.NEWS_PAGE_SIZE;
            }
        });
    }

    private void onRefresh() {
        presenter.refreshNews();
    }
}

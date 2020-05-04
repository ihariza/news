package com.example.cleanarchitecture.presentation.view.news;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cleanarchitecture.databinding.NewRowBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.util.DateUtil;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private final NewsPresenter newsPresenter;
    private final NewRowBinding newsRowBinding;

    NewsViewHolder(@NonNull NewRowBinding binding, @NonNull NewsPresenter newsPresenter) {
        super(binding.getRoot());
        this.newsRowBinding = binding;
        this.newsPresenter = newsPresenter;
    }

    void render(ReportDto report) {
        renderReportImage(report.getImage());
        renderReportTitle(report.getTitle());
        renderReportDescription(report.getDescription());
        renderReportAuthor(report.getAuthor());
        renderReportPublished(report.getPublished());
        onItemClick(report);
    }

    private void renderReportImage(String urlImage) {
        getImage(urlImage, newsRowBinding.image);
    }

    private void renderReportTitle(String name) {
        newsRowBinding.title.setText(name);
    }

    private void renderReportDescription(String description) {
        newsRowBinding.description.setText(description);
    }

    private void renderReportAuthor(String author) {
        newsRowBinding.author.setText(author);
    }

    private void renderReportPublished(String timestamp) {
        String published = DateUtil.formatTimestampIntoDate(timestamp);
        if (published != null) {
            newsRowBinding.published.setText(published);
            newsRowBinding.published.setVisibility(View.VISIBLE);
        } else {
            newsRowBinding.published.setVisibility(View.GONE);
        }
    }

    private void onItemClick(final ReportDto report) {
        itemView.setOnClickListener(v -> newsPresenter.onReportClicked(report));
    }

    private void getImage(String photo, ImageView photoImageView) {
        Glide.with(newsRowBinding.getRoot().getContext()).load(photo).centerCrop().into(photoImageView);
    }
}
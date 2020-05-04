package com.example.cleanarchitecture.presentation.view.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanarchitecture.databinding.NewRowBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final NewsPresenter presenter;
    private final List<ReportDto> news;

    NewsAdapter(@NonNull NewsPresenter presenter) {
        this.presenter = presenter;
        news = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(NewRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        newsViewHolder.render(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    void addAll(Collection<ReportDto> collection) {
        news.addAll(collection);
    }
}
package com.example.cleanarchitecture.presentation.view.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanarchitecture.databinding.LoadingRowBinding;
import com.example.cleanarchitecture.databinding.NewRowBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder<ReportDto>> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private final NewsPresenter presenter;
    private final List<ReportDto> news;

    NewsAdapter(@NonNull NewsPresenter presenter) {
        this.presenter = presenter;
        news = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (presenter.isLoading()) {
            return position == news.size() - 1  ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @NonNull @Override
    public BaseViewHolder<ReportDto> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(
                    LoadingRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
        return new NewsViewHolder(
                NewRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ReportDto> holder, int position) {
        ReportDto report = news.get(position);
        if (report == null) return;
        holder.onBind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    void addAll(Collection<ReportDto> collection) {
        news.addAll(collection);
        notifyDataSetChanged();
    }

    void refresh(Collection<ReportDto> collection) {
        news.clear();
        addAll(collection);
    }

    void showLoading() {
        news.add(null);
        notifyDataSetChanged();
    }

    void hideLoading() {
        news.remove(null);
        notifyDataSetChanged();
    }

}
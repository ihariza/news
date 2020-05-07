package com.example.cleanarchitecture.presentation.view.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.cleanarchitecture.databinding.LoadingRowBinding;
import com.example.cleanarchitecture.databinding.NewRowBinding;
import com.example.cleanarchitecture.presentation.model.LoadingDto;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder<ReportDto>>
        implements ListPreloader.PreloadModelProvider<ReportDto> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private final Context context;
    private final NewsPresenter presenter;
    private final List<ReportDto> news;
    private final LoadingDto loadingDto;
    private final ViewPreloadSizeProvider<ReportDto> imageSizeProvider;


    NewsAdapter(Context context,  @NonNull NewsPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.imageSizeProvider = new ViewPreloadSizeProvider<>();
        this.news = new ArrayList<>();
        this.loadingDto = new LoadingDto();
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
                    LoadingRowBinding.inflate(LayoutInflater.from(
                            parent.getContext()), parent, false));
        }
        NewRowBinding newRowBinding = NewRowBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        imageSizeProvider.setView(newRowBinding.image);
        return new NewsViewHolder(newRowBinding, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ReportDto> holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder<ReportDto> holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @NonNull
    @Override
    public List<ReportDto> getPreloadItems(int position) {
        return Collections.singletonList(news.get(position));
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull ReportDto item) {
        return Glide.with(context).load(item.getImage()).centerCrop().priority(Priority.HIGH);
    }

    ViewPreloadSizeProvider<ReportDto> getImageSizeProvider() {
        return imageSizeProvider;
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
        news.add(loadingDto);
        notifyDataSetChanged();
    }

    void hideLoading() {
        news.remove(loadingDto);
        notifyDataSetChanged();
    }
}
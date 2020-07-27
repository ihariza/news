package com.ihariza.news.presentation.view.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.ihariza.news.databinding.LoadingRowBinding;
import com.ihariza.news.databinding.NewRowBinding;
import com.ihariza.news.presentation.model.LoadingDto;
import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.view.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder<ReportDto>>
        implements ListPreloader.PreloadModelProvider<ReportDto> {

    public interface ReportListener {
        void onReportClicked(ReportDto report);
    }

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private final ReportListener reportListener;
    private final List<ReportDto> news;
    private final LoadingDto loadingDto;
    private final RequestManager requestManager;
    private final ViewPreloadSizeProvider<ReportDto> imageSizeProvider;
    private boolean isLoading;


    NewsAdapter(RequestManager requestManager,
                NewsAdapter.ReportListener reportListener) {
        this.requestManager = requestManager;
        this.reportListener = reportListener;
        this.imageSizeProvider = new ViewPreloadSizeProvider<>();
        this.news = new ArrayList<>();
        this.loadingDto = new LoadingDto();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading) {
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
        return new NewsViewHolder(newRowBinding, requestManager, reportListener);
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
        return requestManager.load(item.getImage()).centerCrop().priority(Priority.HIGH);
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
        isLoading = true;
        news.add(loadingDto);
        notifyDataSetChanged();
    }

    void hideLoading() {
        isLoading = false;
        news.remove(loadingDto);
        notifyDataSetChanged();
    }
}
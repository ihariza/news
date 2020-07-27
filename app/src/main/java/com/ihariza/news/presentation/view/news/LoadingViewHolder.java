package com.ihariza.news.presentation.view.news;

import androidx.annotation.NonNull;

import com.ihariza.news.databinding.LoadingRowBinding;
import com.ihariza.news.presentation.model.ReportDto;
import com.ihariza.news.presentation.view.base.BaseViewHolder;

class LoadingViewHolder extends BaseViewHolder<ReportDto> {

    LoadingViewHolder(@NonNull LoadingRowBinding binding) {
        super(binding.getRoot());
    }
}
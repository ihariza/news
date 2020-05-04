package com.example.cleanarchitecture.presentation.view.news;

import androidx.annotation.NonNull;

import com.example.cleanarchitecture.databinding.LoadingRowBinding;
import com.example.cleanarchitecture.presentation.model.ReportDto;
import com.example.cleanarchitecture.presentation.view.base.BaseViewHolder;

class LoadingViewHolder extends BaseViewHolder<ReportDto> {

    LoadingViewHolder(@NonNull LoadingRowBinding binding) {
        super(binding.getRoot());
    }
}
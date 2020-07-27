package com.ihariza.news.presentation.view.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(T item) {
    }

    public void unbind() {

    }
}

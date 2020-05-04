package com.example.cleanarchitecture.presentation.view.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    @NonNull
    private LinearLayoutManager layoutManager;

    /**
     * Supporting only LinearLayoutManager for now.
     */
    protected PaginationListener(@NonNull LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getPageSize()) {
                loadPage();
            }
        }
    }

    public abstract void loadPage();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    public abstract int getPageSize();
}

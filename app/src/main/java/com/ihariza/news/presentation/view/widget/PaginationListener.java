package com.ihariza.news.presentation.view.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    @NonNull
    private LinearLayoutManager layoutManager;
    private int pageSize;

    /**
     * Supporting only LinearLayoutManager for now.
     */
    protected PaginationListener(@NonNull LinearLayoutManager layoutManager, int pageSize) {
        this.layoutManager = layoutManager;
        this.pageSize = pageSize;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() &&
                !isLastPage() &&
                (visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                firstVisibleItemPosition >= 0 &&
                totalItemCount >= pageSize) {
            loadPage((totalItemCount / pageSize) + 1);
        }
    }

    public abstract void loadPage(int pageNumber);

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}

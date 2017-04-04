package com.carlospienovi.mvpproject.presentation.decorations;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (space <= 0) {
            return;
        }

        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            outRect.top = space;
            outRect.right = space;
            outRect.left = space;
        } else {
            outRect.left = space;
            outRect.top = space;
            outRect.bottom = space;
        }

        if (parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.bottom = space;
            } else {
                outRect.right = space;
            }
        }
    }

    private int getTotalItemCount(RecyclerView parent) {
        return parent.getAdapter().getItemCount();
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        } else {
            throw new IllegalStateException(
                    "SpaceItemDecoration can only be used with a LinearLayoutManager.");
        }
    }

}

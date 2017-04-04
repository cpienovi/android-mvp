package com.carlospienovi.mvpproject.presentation.mvp.views;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carlospienovi.mvpproject.R;
import com.carlospienovi.mvpproject.presentation.adapters.PostAdapter;
import com.carlospienovi.mvpproject.presentation.decorations.SpaceItemDecoration;
import com.carlospienovi.mvpproject.presentation.mvp.viewstates.DetailViewState;
import com.carlospienovi.mvpproject.presentation.mvp.viewstates.MainViewState;
import com.squareup.otto.Bus;

import butterknife.BindView;

public class MainView extends ActivityView {

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.main_progress)
    ProgressBar progressBar;
    @BindView(R.id.main_error)
    TextView txtError;

    private Bus bus;
    private PostAdapter adapter;

    public MainView(Activity activity, Bus bus) {
        super(activity);
        this.bus = bus;

        adapter = new PostAdapter(bus);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(activity.getResources().getDimensionPixelSize(R.dimen.recycler_item_space)));
        recyclerView.setAdapter(adapter);
    }

    public void onResume() {
        bus.post(new MainViewResumedEvent());
    }

    public void render(MainViewState model) {
        progressBar.setVisibility(model.isLoading() ? View.VISIBLE : View.GONE);
        txtError.setVisibility(model.getError() != null ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(model.getPosts() != null ? View.VISIBLE : View.GONE);

        adapter.update(model.getPosts());
    }

    public void toDetail(DetailViewState detailViewState) {
        DetailView.start(getContext(), detailViewState);
    }

    public static final class MainViewResumedEvent {

    }

}

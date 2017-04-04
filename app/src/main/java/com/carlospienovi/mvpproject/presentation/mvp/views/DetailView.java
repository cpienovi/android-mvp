package com.carlospienovi.mvpproject.presentation.mvp.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.carlospienovi.mvpproject.R;
import com.carlospienovi.mvpproject.presentation.activities.DetailActivity;
import com.carlospienovi.mvpproject.presentation.mvp.viewstates.DetailViewState;
import com.squareup.otto.Bus;

import butterknife.BindView;

public class DetailView extends ActivityView {

    public static final String KEY_VIEW_STATE = "keyViewState";

    @BindView(R.id.detail_title)
    TextView txtTitle;
    @BindView(R.id.detail_body)
    TextView txtBody;

    private Bus bus;
    private DetailViewState viewState;

    public static void start(Context context, DetailViewState post) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_VIEW_STATE, post);
        context.startActivity(intent);
    }

    public DetailView(Activity activity, Bus bus, Bundle savedInstanceState) {
        super(activity);
        this.bus = bus;

        if (savedInstanceState == null) {
            init();
        } else {
            fromBundle(savedInstanceState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_VIEW_STATE, viewState);
    }

    public void render(@Nullable DetailViewState viewState) {
        this.viewState = viewState;

        if (viewState == null) {
            return;
        }

        txtTitle.setText(viewState.getTitle());
        txtBody.setText(viewState.getBody());
    }

    private void fromBundle(Bundle bundle) {
        DetailViewState viewState = bundle.getParcelable(KEY_VIEW_STATE);
        render(viewState);
    }

    private void init() {
        if (getActivity() == null) {
            return;
        }

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            return;
        }

        fromBundle(extras);
    }

}

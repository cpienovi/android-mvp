package com.carlospienovi.mvpproject.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlospienovi.mvpproject.R;
import com.carlospienovi.mvpproject.domain.Post;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> implements AdapterClickListener {

    private List<Post> posts;
    private Bus bus;

    public PostAdapter(Bus bus) {
        this.bus = bus;
        this.posts = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = getItem(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void update(@Nullable List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts != null ? posts : new ArrayList<Post>());
        notifyDataSetChanged();
    }

    @Nullable
    private Post getItem(int position) {
        if (position < 0 || position >= posts.size()) {
            return null;
        }

        return posts.get(position);
    }

    @Override
    public void onAdapterItemClick(int position) {
        bus.post(new PostClickedEvent(getItem(position)));
    }

    public static final class PostClickedEvent {

        private Post post;

        PostClickedEvent(Post post) {
            this.post = post;
        }

        public Post getPost() {
            return post;
        }

    }

    public static final class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.post_title)
        TextView txtTitle;
        @BindView(R.id.post_body)
        TextView txtBody;

        private AdapterClickListener clickListener;

        PostViewHolder(View itemView, AdapterClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Post post) {
            txtTitle.setText(post.getTitle());
            txtBody.setText(post.getBody());
        }

        @Override
        public void onClick(View v) {
            clickListener.onAdapterItemClick(getAdapterPosition());
        }

    }

}

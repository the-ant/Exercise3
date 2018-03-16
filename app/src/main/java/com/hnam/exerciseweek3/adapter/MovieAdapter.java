package com.hnam.exerciseweek3.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnam.exerciseweek3.Constants;
import com.hnam.exerciseweek3.R;
import com.hnam.exerciseweek3.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Result> listMovies;
    private MovieItemClickListener listener;

    public interface MovieItemClickListener {
        void onItemClick(Result result);
    }

    public MovieAdapter(Context context, List<Result> listMovies, MovieItemClickListener listener) {
        this.context = context;
        this.listMovies = listMovies;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = listMovies.get(position);
        Picasso.with(context)
                .load(Constants.HEADER_URL_IMAGE + result.getPosterPath())
                .fit()
                .centerInside()
                .into(holder.poster);
        holder.title.setText(result.getTitle());
        holder.overview.setText(result.getOverview());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(result));
        holder.playMovie.setVisibility(result.getVideo() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_poster)
        ImageView poster;
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_overview)
        TextView overview;
        @BindView(R.id.play_movie)
        ImageButton playMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

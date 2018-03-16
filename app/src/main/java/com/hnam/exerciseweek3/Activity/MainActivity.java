package com.hnam.exerciseweek3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.hnam.exerciseweek3.MyApp;
import com.hnam.exerciseweek3.R;
import com.hnam.exerciseweek3.adapter.MovieAdapter;
import com.hnam.exerciseweek3.pojo.Movie;
import com.hnam.exerciseweek3.pojo.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieItemClickListener {
    private static final String TAG = "main";
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle("Movies");

        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        rvMovie.setItemAnimator(new DefaultItemAnimator());

        Gson gson = new Gson();
        String moviesJSON = MyApp.getMessageMovie();
        Movie movie = gson.fromJson(moviesJSON, Movie.class);
        if (movie != null) {
            MovieAdapter adapter = new MovieAdapter(this, movie.getResults(), this);
            rvMovie.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(Result result) {
        Intent detailIntent = new Intent(this, DetailMovieActivity.class);
        detailIntent.putExtra("detail", result);
        startActivity(detailIntent);
    }
}

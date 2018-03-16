package com.hnam.exerciseweek3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hnam.exerciseweek3.Constants;
import com.hnam.exerciseweek3.R;
import com.hnam.exerciseweek3.pojo.Result;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.img_poster)
    ImageView mvPoster;
    @BindView(R.id.title)
    TextView mvTitle;
    @BindView(R.id.release_date)
    TextView mvReleaseDate;
    @BindView(R.id.movie_overview)
    TextView mvOverview;
    @BindView(R.id.rating_bar)
    RatingBar rbVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Result result = (Result) intent.getSerializableExtra("detail");
            if (result != null) {
                Picasso.with(this)
                        .load(Constants.HEADER_URL_IMAGE + result.getBackdropPath())
                        .fit()
                        .centerInside()
                        .into(mvPoster);
                mvTitle.setText(result.getTitle());
                mvOverview.setText(result.getOverview());
                rbVote.setRating(result.getVoteAverage() / 2);
                mvReleaseDate.setText(getResources().getString(R.string.detail_release_date) + result.getReleaseDate());
                getSupportActionBar().setTitle(result.getTitle());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.arjava.dcodingcatmovieuiux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.arjava.dcodingcatmovieuiux.R;
import com.bumptech.glide.Glide;

/**
 * Created by arjava on 11/18/17.
 */

public class DetailsMovie extends AppCompatActivity {

    //create object
    ImageView imageViewDetails;
    TextView textViewVote, textViewTitle, textViewOverview;
    String url_image = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        //inisiasi
        imageViewDetails = (ImageView) findViewById(R.id.imageViewDetails);
        textViewVote = (TextView) findViewById(R.id.textViewVote);
        textViewTitle = (TextView) findViewById(R.id.textTitleDetails);
        textViewOverview = (TextView) findViewById(R.id.overViewDetails);

        //set actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        //get intent data
        Intent movie = getIntent();
        final String titleOri = movie.getStringExtra("orititle");
        final Double voteAvg = movie.getDoubleExtra("vote",0.0);
        final String overview = movie.getStringExtra("overview");
        final String poster_path = movie.getStringExtra("poster");
        final String imageLoad = url_image+poster_path;

        //show image use library
        Glide
                .with(DetailsMovie.this)
                .load(imageLoad)
                .placeholder(R.drawable.ic_toys_green_300_24dp)
                .into(imageViewDetails);
        //show vote @Double
        textViewVote.setText(String.valueOf(voteAvg));
        //show Title
        textViewTitle.setText(titleOri);
        //show Descripton
        textViewOverview.setText(overview);

    }

    //penanganan untuk icon back actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}

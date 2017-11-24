package com.arjava.dcodingcatmovieuiux.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arjava.dcodingcatmovieuiux.R;
import com.arjava.dcodingcatmovieuiux.adapter.MovieAdapter;
import com.arjava.dcodingcatmovieuiux.model.MovieItems;
import com.arjava.dcodingcatmovieuiux.request.ApiClient;
import com.arjava.dcodingcatmovieuiux.request.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);

        progressBar = findViewById(R.id.progressBarMainRated);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toprated);

        loadMovieToprated();
    }

    private void loadMovieToprated() {

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewRated);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiInterface = ApiClient.getRetrofit(getApplicationContext()).create(ApiInterface.class);
        Call<MovieItems> call = apiInterface.getTopRated();
        call.enqueue(new Callback<MovieItems>() {
            //ketika server meresponse
            @Override
            public void onResponse(Call<MovieItems> call, Response<MovieItems> response) {
                MovieItems data = response.body();
                if (data.getResults().size() == 0) {
                    Toast.makeText(getApplicationContext(), "maaf data yang anda cari tidak ditemukan", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    recyclerView.setAdapter(new MovieAdapter(data.getResults(), R.layout.content_recycler, getApplicationContext()));
                    progressBar.setVisibility(View.GONE);
                }
            }

            //ketika gagal mendapatkan response
            @Override
            public void onFailure(Call<MovieItems> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
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
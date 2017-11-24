package com.arjava.dcodingcatmovieuiux.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivitySearchNav extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText inputSearch;
    private Button submitSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nav);

        inputSearch = findViewById(R.id.edtSearch);
        submitSearch = findViewById(R.id.btnSearch);
        progressBar = findViewById(R.id.progressBarSnav);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.search_nav);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovieSearch();
                submitSearch.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        progressBar.setVisibility(View.VISIBLE);
    }

    private void getMovieSearch() {
        //menggunakan search berdasarkan input user
        String input_movie = inputSearch.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        final RecyclerView recyclerView = findViewById(R.id.recyclerMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivitySearchNav.this));
        ApiInterface apiInterface = ApiClient.getRetrofit(getApplicationContext()).create(ApiInterface.class);
        Call<MovieItems> call = apiInterface.getMovieItems(input_movie);
        call.enqueue(new Callback<MovieItems>() {
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

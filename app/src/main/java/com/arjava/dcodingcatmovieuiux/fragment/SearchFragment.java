package com.arjava.dcodingcatmovieuiux.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arjava.dcodingcatmovieuiux.MainActivity;
import com.arjava.dcodingcatmovieuiux.R;
import com.arjava.dcodingcatmovieuiux.adapter.MovieAdapter;
import com.arjava.dcodingcatmovieuiux.model.MovieItems;
import com.arjava.dcodingcatmovieuiux.request.ApiClient;
import com.arjava.dcodingcatmovieuiux.request.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arjava on 11/18/17.
 */

public class SearchFragment extends Fragment {

    private ProgressBar progresBar;

    private static final String TAG = NowPlayingFragment.class.getSimpleName();
    private String cariNama = "";

    public SearchFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadSearchMovie(cariNama);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searching, container, false);
        progresBar = view.findViewById(R.id.progressBarMainSearch);
        return view;
    }

    public void loadSearchMovie(String cariNama) {

//        Bundle args = new Bundle();
        cariNama = MainActivity.cari_film;
        Log.e(TAG, "hasil query cari nama : "+ cariNama);
//        progresBar.setVisibility(View.VISIBLE);
        final RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApiInterface apiInterface = ApiClient.getRetrofit(getActivity()).create(ApiInterface.class);
        Call<MovieItems> call = apiInterface.getMovieItems(cariNama);
        call.enqueue(new Callback<MovieItems>() {
            //ketika server meresponse
            @Override
            public void onResponse(Call<MovieItems> call, Response<MovieItems> response) {
                MovieItems data = response.body();
                if (data.getResults().size()==0 || data.getResults()==null) {
                    Toast.makeText(getActivity(), "maaf data yang anda cari tidak ditemukan", Toast.LENGTH_SHORT).show();
                    progresBar.setVisibility(View.GONE);
                }else {
                    recyclerView.setAdapter(new MovieAdapter(data.getResults(), R.layout.content_recycler, getActivity()));
                    Log.e(TAG, "onResponse: hasil pemanggilan"+ call);
                    progresBar.setVisibility(View.GONE);
                }
            }

            //ketika gagal mendapatkan response
            @Override
            public void onFailure(Call<MovieItems> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.toString());
                progresBar.setVisibility(View.GONE);
            }
        });
    }

}

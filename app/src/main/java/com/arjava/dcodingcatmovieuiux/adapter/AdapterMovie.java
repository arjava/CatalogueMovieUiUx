package com.arjava.dcodingcatmovieuiux.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arjava.dcodingcatmovieuiux.R;
import com.arjava.dcodingcatmovieuiux.model.ResultMovie;

import java.util.List;

/*
 * Created by arjava on 11/17/17.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    List<ResultMovie> resultMovie;
    Context context;
    LayoutInflater rowLayout;

    public AdapterMovie(List<ResultMovie> resultMovie, Context context, LayoutInflater rowLayout) {
        this.resultMovie = resultMovie;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMovie.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return resultMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

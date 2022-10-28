package com.example.petcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.adapter.NewsViewHolder;
import com.example.petcare.model.News;
import com.example.petcare.R;
import com.example.petcare.model.Combo;
import com.example.petcare.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    private List<News> list;
    private Context context;
    private LayoutInflater inflater;

    public NewsAdapter(List<News> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_news_item, parent, false);
        return new NewsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News c = list.get(position);
        holder.setNews(c);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

package com.example.petcare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petcare.R;
import com.example.petcare.customer.HomeCustomerActivity;
import com.example.petcare.model.News;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private ImageView imvNewsItem;
    private TextView tvTitleNewsItem;
    private TextView tvDescriptionNewsItem;
    private final Context context;

    public NewsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
    }

    private void bindingView(View itemView) {
        imvNewsItem = itemView.findViewById(R.id.imvNewsItem);
        tvTitleNewsItem = itemView.findViewById(R.id.tvTitleNewsItem);
        tvDescriptionNewsItem = itemView.findViewById(R.id.tvDescriptionNewsItem);
    }

    public void setNews(News c) {
        Picasso.get().load(c.getImageNews()).into(imvNewsItem);
        tvTitleNewsItem.setText(c.getTitleNews());
        tvDescriptionNewsItem.setText(c.getDescriptionNews());
    }
}

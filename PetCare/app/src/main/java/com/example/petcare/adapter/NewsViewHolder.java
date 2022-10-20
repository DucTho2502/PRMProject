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
    private ImageView ivNewsImage;
    private TextView tvNewsName;
    private TextView tvNewsDescription;
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
        ivNewsImage = itemView.findViewById(R.id.newsLImage);
        tvNewsName = itemView.findViewById(R.id.newsName);
        tvNewsDescription = itemView.findViewById(R.id.newsDescription);
    }

    public void setNews(News c) {
        Glide.with(context).load(c.getImageNews()).into(ivNewsImage);
        tvNewsName.setText(c.getTitleNews());
        tvNewsDescription.setText(c.getDescriptionNews());
    }
}

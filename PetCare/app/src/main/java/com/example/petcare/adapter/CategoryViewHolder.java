package com.example.petcare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.example.petcare.model.Category;
import com.example.petcare.model.Combo;
import com.squareup.picasso.Picasso;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgCategoryItem;
    private TextView tvTitleCategoryItem;
    private final Context context;

    public CategoryViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
    }

    private void bindingView(View itemView) {
        imgCategoryItem = itemView.findViewById(R.id.imgCategoryItem);
        tvTitleCategoryItem = itemView.findViewById(R.id.tvTitleCategoryItem);
    }

    public void setCategory(Category c) {
        Picasso.get().load(c.getImageCategory()).into(imgCategoryItem);
        tvTitleCategoryItem.setText(c.getNameCategory());
    }
}

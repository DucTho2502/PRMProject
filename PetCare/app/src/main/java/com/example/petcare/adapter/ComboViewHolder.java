package com.example.petcare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.example.petcare.model.Combo;

public class ComboViewHolder extends RecyclerView.ViewHolder{
    private ImageView imgBestComboItem;
    private TextView tvTitleBestComboItem;
    private TextView tvPriceBestComboItem;
    private final Context context;

    public ComboViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
        this.context = context;
    }

    private void bindingAction(View itemView) {
    }

    private void bindingView(View itemView) {
        imgBestComboItem = itemView.findViewById(R.id.imgBestComboItem);
        tvTitleBestComboItem = itemView.findViewById(R.id.tvTitleBestComboItem);
        tvPriceBestComboItem = itemView.findViewById(R.id.tvPriceBestComboItem);
    }

    public void setCombo(Combo c) {
        imgBestComboItem.setImageResource(c.getComboImage());
        tvTitleBestComboItem.setText(c.getComboName());
        tvPriceBestComboItem.setText(Double.toString(c.getPrice()));
    }
}

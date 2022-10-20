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
    private ImageView ivComboImage;
    private TextView tvComboName;
    private TextView tvComboDescription;
    private TextView tvComboPrice;
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
        ivComboImage = itemView.findViewById(R.id.newsLImage);
        tvComboName = itemView.findViewById(R.id.newsName);
        tvComboDescription = itemView.findViewById(R.id.newsDescription);
        tvComboPrice = itemView.findViewById(R.id.recentPrice);
    }

    public void setCombo(Combo c) {
        ivComboImage.setImageResource(c.getComboImage());
        tvComboName.setText(c.getComboName());
        tvComboDescription.setText(c.getDescription());
        tvComboPrice.setText(Double.toString(c.getPrice()));
    }
}

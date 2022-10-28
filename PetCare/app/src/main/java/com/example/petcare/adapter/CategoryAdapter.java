package com.example.petcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.example.petcare.model.Category;
import com.example.petcare.model.Combo;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    private List<Category> list;
    private Context context;
    private LayoutInflater inflater;

    public CategoryAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_category_item, parent, false);
        return new CategoryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category c = list.get(position);
        holder.setCategory(c);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

package com.example.petcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.example.petcare.model.Combo;

import java.util.List;

public class ComboAdapter extends RecyclerView.Adapter<ComboViewHolder>{
    private List<Combo> list;
    private Context context;
    private LayoutInflater inflater;

    public ComboAdapter(List<Combo> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.best_combo_item, parent, false);
        return new ComboViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ComboViewHolder holder, int position) {
        Combo c = list.get(position);
        holder.setCombo(c);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

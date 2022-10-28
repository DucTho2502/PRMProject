package com.example.petcare.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare.R;
import com.example.petcare.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAdminAdapter extends FirebaseRecyclerAdapter<Category, CategoryAdminAdapter.CategoryAdminViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryAdminAdapter(@NonNull FirebaseRecyclerOptions<Category> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryAdminViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Category model) {
        Picasso.get().load(model.getImageCategory()).into(holder.imvCategoryItemAdmin);
        holder.tvNameCategoryAdmin.setText(model.getNameCategory());

        holder.btnEditCategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imvCategoryItemAdmin.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_update_category_admin))
                        .setExpanded(true, 1700)
                        .create();


                View view1 = dialogPlus.getHolderView();

                EditText edtNameCategoryAdmin = view1.findViewById(R.id.edtNameCategoryAdmin);
                ImageView imvUpdateCategoryAdmin = view1.findViewById(R.id.imvUpdateCategoryAdmin);
                EditText edtImgCategoryAdmin = view1.findViewById(R.id.edtImgCategoryAdmin);
                EditText edtDescriptionCategoryAdmin = view1.findViewById(R.id.edtDescriptionCategoryAdmin);

                Button btnUpdateCategoryAdmin = view1.findViewById(R.id.btnUpdateCategoryAdmin);

                edtNameCategoryAdmin.setText(model.getNameCategory());
                Picasso.get().load(model.getImageCategory()).into(imvUpdateCategoryAdmin);
                edtImgCategoryAdmin.setText(model.getImageCategory());

                dialogPlus.show();

                btnUpdateCategoryAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("imageCategory", edtImgCategoryAdmin.getText().toString());
                        map.put("nameCategory", edtNameCategoryAdmin.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tvNameCategoryAdmin.getContext(), "Data update success", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.tvNameCategoryAdmin.getContext(), "Error update", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });
                    }
                });
            }
        });

        holder.btnDeleteCategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tvNameCategoryAdmin.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.tvNameCategoryAdmin.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public CategoryAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_admin_item, parent, false);
        return new CategoryAdminViewHolder(view);
    }

    public class CategoryAdminViewHolder extends RecyclerView.ViewHolder{

        ImageView imvCategoryItemAdmin;
        TextView tvNameCategoryAdmin;

        Button btnEditCategoryAdmin;
        Button btnDeleteCategoryAdmin;

        public CategoryAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            imvCategoryItemAdmin = itemView.findViewById(R.id.imvCategoryItemAdmin);
            tvNameCategoryAdmin = itemView.findViewById(R.id.tvNameCategoryAdmin);

            btnEditCategoryAdmin = itemView.findViewById(R.id.btnEditCategoryAdmin);
            btnDeleteCategoryAdmin = itemView.findViewById(R.id.btnDeleteCategoryAdmin);
        }
    }
}
//public class CategoryAdminAdapter extends RecyclerView.Adapter<CategoryAdminViewHolder>{
//    private List<Category> list;
//    private Context context;
//    private LayoutInflater inflater;
//
//    public CategoryAdminAdapter(List<Category> list, Context context) {
//        this.list = list;
//        this.context = context;
//        this.inflater = LayoutInflater.from(context);
//    }
//
//
//    @NonNull
//    @Override
//    public CategoryAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.category_admin_item, parent, false);
//        return new CategoryAdminViewHolder(view, context);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryAdminViewHolder holder, int position) {
//        Category c = list.get(position);
//        holder.setCategory(c);
//        holder.getPositionById(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//}

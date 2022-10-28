package com.example.petcare.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petcare.R;
import com.example.petcare.adapter.CategoryAdapter;
import com.example.petcare.adapter.CategoryAdminAdapter;
import com.example.petcare.adapter.ComboAdapter;
import com.example.petcare.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryAdminActivity extends AppCompatActivity {

    private RecyclerView rcvCategoryAdmin;
    private CategoryAdminAdapter categoryAdminAdapter;

    private SearchView searchViewCategoryAdmin;
    private FloatingActionButton addCategoryAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);
        bindingView();

        rcvCategoryAdmin.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Category.class)
                .build();

        categoryAdminAdapter = new CategoryAdminAdapter(options);
        rcvCategoryAdmin.setAdapter(categoryAdminAdapter);

        addCategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertCategoryAdminActivity.class));
            }
        });

        searchViewCategoryAdmin.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txtSearch(newText);
                return false;
            }
        });

    }

    private void bindingView() {
        rcvCategoryAdmin = findViewById(R.id.rcvCategoryAdmin);
        addCategoryAdmin = findViewById(R.id.addCategoryAdmin);
        searchViewCategoryAdmin = findViewById(R.id.searchViewCategoryAdmin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryAdminAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdminAdapter.stopListening();
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Category").orderByChild("imageCategory").startAt(str).endAt(str+"\\uf8ff"), Category.class)
                .build();

        categoryAdminAdapter = new CategoryAdminAdapter(options);
        categoryAdminAdapter.startListening();
        rcvCategoryAdmin.setAdapter(categoryAdminAdapter);
    }
}
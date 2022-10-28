package com.example.petcare.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petcare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InsertCategoryAdminActivity extends AppCompatActivity {

    EditText edtNameCategoryInsertAdmin;
    EditText edtImgCategoryInsertAdmin;
    EditText edtDescriptionInsertCategoryAdmin;

    Button btnInsertCategoryAdmin;
    Button btnBackCategoryAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_category_admin);

        bindingView();

        btnInsertCategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnBackCategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bindingView() {
        edtNameCategoryInsertAdmin = findViewById(R.id.edtNameCategoryInsertAdmin);
        edtImgCategoryInsertAdmin = findViewById(R.id.edtImgCategoryInsertAdmin);
        edtDescriptionInsertCategoryAdmin = findViewById(R.id.edtDescriptionInsertCategoryAdmin);

        btnInsertCategoryAdmin = findViewById(R.id.btnInsertCategoryAdmin);
        btnBackCategoryAdmin = findViewById(R.id.btnBackCategoryAdmin);

    }

    private void insertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("nameCategory", edtNameCategoryInsertAdmin.getText().toString());
        map.put("imageCategory", edtImgCategoryInsertAdmin.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Category")
                .push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(InsertCategoryAdminActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InsertCategoryAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void clearAll(){
        edtNameCategoryInsertAdmin.setText("");
        edtImgCategoryInsertAdmin.setText("");
    }
}
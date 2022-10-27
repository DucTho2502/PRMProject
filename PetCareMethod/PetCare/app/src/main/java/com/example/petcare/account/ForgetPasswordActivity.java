package com.example.petcare.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petcare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText edtEmailForgetUi;
    private Button btnResetPassword;
    private ImageView imgBackLoginUi;
    private Intent intent;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        
        bindingView();
        bindingAction();
    }

    private void bindingAction() {
        btnResetPassword.setOnClickListener(this::onBtnForgetPasswordCLick);
        imgBackLoginUi.setOnClickListener(this::onBtnBackLoginUiClick);
    }

    private void onBtnBackLoginUiClick(View view) {
//        intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
        backToLogin();
    }

    private void backToLogin() {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void onBtnForgetPasswordCLick(View view) {
        String email = edtEmailForgetUi.getText().toString();

        if(email.isEmpty()){
            edtEmailForgetUi.setError("Email is required!");
            edtEmailForgetUi.requestFocus();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                    backToLogin();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindingView() {
        edtEmailForgetUi = findViewById(R.id.edtEmailForgetUi);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        imgBackLoginUi = findViewById(R.id.imgBackLoginUi);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
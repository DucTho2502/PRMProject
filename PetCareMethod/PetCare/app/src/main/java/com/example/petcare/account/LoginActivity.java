package com.example.petcare.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petcare.R;
import com.example.petcare.customer.HomeCustomerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText edtLoginEmail;
    private EditText edtLoginPassword;
    private Button btnLogin;
    private Button btnSigUpInLoginUi;
    private Button btnForgetPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindingView();
        bindingAction();
    }

    private void bindingView() {
        btnSigUpInLoginUi = findViewById(R.id.btnSignUpInLoginUI);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgetPassword = findViewById(R.id.btnForgetPasswordLoginUi);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onBtnLoginClick);
        btnSigUpInLoginUi.setOnClickListener(this::onBtnSigUpUiClick);
        btnForgetPassword.setOnClickListener(this::onBtnForgetPasswordUiClick);
    }

    private void onBtnForgetPasswordUiClick(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    private void onBtnLoginClick(View view) {
        String email = edtLoginEmail.getText().toString();
        String password = edtLoginPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(view.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this, HomeCustomerActivity.class);
                            startActivity(intent);
                            finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Fail login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void onBtnSigUpUiClick(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
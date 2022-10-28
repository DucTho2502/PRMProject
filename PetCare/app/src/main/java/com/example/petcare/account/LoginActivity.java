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
import com.example.petcare.admin.HomeAdminActivity;
import com.example.petcare.customer.HomeCustomerActivity;
import com.example.petcare.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
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
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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

                        String userId = task.getResult().getUser().getUid();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        firebaseDatabase.getReference().child("Customer").child(userId).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int role = snapshot.getValue(Integer.class);
                                if(role == 1){
                                    Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                                    startActivity(intent);
                                }

                                if(role == 2){
                                    Intent intent = new Intent(LoginActivity.this, HomeCustomerActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
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
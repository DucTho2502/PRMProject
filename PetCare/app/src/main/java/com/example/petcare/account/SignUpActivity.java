package com.example.petcare.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.petcare.R;
import com.example.petcare.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://petcare-25b65-default-rtdb.firebaseio.com/");
    FirebaseAuth firebaseAuth;
    private EditText edtFullNameSignUp;
    private EditText edtUsernameSignUp;
    private EditText edtEmailSignUp;
    private EditText edtPhoneSignUp;
    private EditText edtPasswordSignUp;
    private EditText edtConfirmPassSignUp;
    private Button btnSignUp;
    private Button btnLoginInSignUpUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        btnLoginInSignUpUi = findViewById(R.id.btnLoginInSignUpUi);
        edtFullNameSignUp = findViewById(R.id.edtFullNameSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtPhoneSignUp = findViewById(R.id.edtPhoneSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtConfirmPassSignUp = findViewById(R.id.edtConfirmPassSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void bindingAction() {
        btnSignUp.setOnClickListener(this::onBtnSignUpClick);
        btnLoginInSignUpUi.setOnClickListener(this::onBtnLoginUiClick);
    }

    private void onBtnSignUpClick(View view) {
        String fullName = edtFullNameSignUp.getText().toString();
        String userName = edtUsernameSignUp.getText().toString();
        String email = edtEmailSignUp.getText().toString();
        String phone = edtPhoneSignUp.getText().toString();
        String password = edtPasswordSignUp.getText().toString();
        String confirmPassword = edtConfirmPassSignUp.getText().toString();

        if(fullName.isEmpty() || userName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if(!password.equals(confirmPassword)){
            Toast.makeText(this, "Password are not matching", Toast.LENGTH_SHORT).show();
        } else {

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Customer customer = new Customer(userName, fullName, email, phone, 2);

                        FirebaseDatabase.getInstance().getReference("Customer")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "Customer register successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void onBtnLoginUiClick(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
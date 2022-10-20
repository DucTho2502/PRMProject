package com.example.petcare.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petcare.R;
import com.example.petcare.account.LoginActivity;
import com.example.petcare.adapter.ComboAdapter;
import com.example.petcare.model.Combo;
import com.example.petcare.model.Customer;
import com.example.petcare.model.News;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeCustomerActivity extends AppCompatActivity {

    private FirebaseUser firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userId;

    private RecyclerView rcvCombo;
    private List<Combo> list = new ArrayList<>();
    private RecyclerView rcvNews;
    private List<News> newsList = new ArrayList<>();
    private ImageView imvBooking;
    private TextView tvFullNameHome;
    private ImageView imvAvatarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);

        bindingView();
        bindingAction();
        rcvCombo.setAdapter(new ComboAdapter(list, this));
        rcvNews.setAdapter(new com.example.petcareproject.adapter.NewsAdapter(newsList, this));

        rcvCombo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcvNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        showCustomerInformation();
    }

    private void bindingAction() {
        list.add(new Combo(R.drawable.example, "Combo 1", "Description 1", 3000));
        list.add(new Combo(R.drawable.example, "Combo 2", "Description 2", 3000));
        list.add(new Combo(R.drawable.example, "Combo 3", "Description 3", 3000));
        list.add(new Combo(R.drawable.example, "Combo 4", "Description 4", 3000));

//        newsList.add(new News(R.drawable.introduction, "Take care your little pets", "Take care your little pets. Take care your little pets. Take care your little pets"));
//        newsList.add(new News(R.drawable.introduction, "Take care your little pets 2", "Take care your little pets. Take care your little pets. Take care your little pets"));
//        newsList.add(new News(R.drawable.introduction, "Take care your little pets 3", "Take care your little pets. Take care your little pets. Take care your little pets"));
//        newsList.add(new News(R.drawable.introduction, "Take care your little pets 4", "Take care your little pets. Take care your little pets. Take care your little pets"));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("News");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    News news = data.getValue(News.class);

                    if (news != null) {
                        newsList.add(news);
                    }
                }

                
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imvBooking.setOnClickListener(this::onBtnBookingUiClick);
    }

    private void onBtnBookingUiClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void showCustomerInformation() {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String img = snapshot.child("image").getValue().toString();
                Customer customerProfile = snapshot.getValue(Customer.class);

                if (customerProfile != null) {
                    String fullName = customerProfile.getFullName();

                    Glide.with(HomeCustomerActivity.this).load(img).into(imvAvatarHome);

                    tvFullNameHome.setText(fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bindingView() {
        rcvCombo = findViewById(R.id.rcvRecentCombo);
        rcvNews = findViewById(R.id.rcvNews);
        imvBooking = findViewById(R.id.imvBooking);
        tvFullNameHome = findViewById(R.id.tvFullNameHome);
        imvAvatarHome = findViewById(R.id.imvAvatarHome);
        firebaseDatabase = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        userId = firebaseDatabase.getUid();
    }
}
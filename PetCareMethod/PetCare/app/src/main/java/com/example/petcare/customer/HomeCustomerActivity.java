package com.example.petcare.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petcare.R;
import com.example.petcare.account.LoginActivity;
import com.example.petcare.adapter.CategoryAdapter;
import com.example.petcare.adapter.ComboAdapter;
import com.example.petcare.adapter.NewsAdapter;
import com.example.petcare.admin.HomeAdminActivity;
import com.example.petcare.model.Category;
import com.example.petcare.model.Combo;
import com.example.petcare.model.Customer;
import com.example.petcare.model.News;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeCustomerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //firebase
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userId;

    //best combo
    private RecyclerView rcvBestComboHome;
    private List<Combo> list = new ArrayList<>();

    //news
    private RecyclerView rvcNewsHome;
    private List<News> newsList = new ArrayList<>();

    //categories
    private RecyclerView rcvCategoryHome;
    private List<Category> categories = new ArrayList<>();

    //navigation
    private DrawerLayout dlHomCustomer;
    private NavigationView nfvHomeCustomer;
    private ImageView imvMenuUi;
    private static  float END_SCALE = 0.7f;
    private LinearLayout contentHomeCustomer;

    //show customer information
    private TextView tvNameCustomerMenuHeader;
    private View vMenu;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_customer);

        bindingView();
        bindingAction();

        rcvBestComboHome.setAdapter(new ComboAdapter(list, this));
        rcvBestComboHome.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    private void bindingAction() {

        //best combo
        list.add(new Combo(R.drawable.example, "Combo 1", "Description 1", 3000));
        list.add(new Combo(R.drawable.example, "Combo 2", "Description 2", 3000));
        list.add(new Combo(R.drawable.example, "Combo 3", "Description 3", 3000));
        list.add(new Combo(R.drawable.example, "Combo 4", "Description 4", 3000));

        //news
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("News");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    News news = data.getValue(News.class);
                    newsList.add(news);
                }
                rvcNewsHome.setAdapter(new NewsAdapter(newsList, HomeCustomerActivity.this));
                rvcNewsHome.setLayoutManager(new LinearLayoutManager(HomeCustomerActivity.this, RecyclerView.VERTICAL, false));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //category
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Category");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Category category = data.getValue(Category.class);
                    categories.add(category);
                }
                rcvCategoryHome.setAdapter(new CategoryAdapter(categories, HomeCustomerActivity.this));
                rcvCategoryHome.setLayoutManager(new LinearLayoutManager(HomeCustomerActivity.this, RecyclerView.HORIZONTAL, false));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

        navigation();
        showInforCustomer();
    }

    private void showInforCustomer() {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customer = snapshot.getValue(Customer.class);

                if(customer != null){
                    String name = customer.getFullName();

                    tvNameCustomerMenuHeader.setText(customer.getFullName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeCustomerActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void bindingView() {
        rcvBestComboHome = findViewById(R.id.rcvBestComboHome);
        rvcNewsHome = findViewById(R.id.rvcNewsHome);
        rcvCategoryHome = findViewById(R.id.rcvCategoryHome);
        dlHomCustomer = findViewById(R.id.drawer_layout);
        nfvHomeCustomer = findViewById(R.id.navigation_customer);
        imvMenuUi = findViewById(R.id.imvMenuUi);
        contentHomeCustomer = findViewById(R.id.contentHomeCustomer);

        //binding view full name in menu header
        vMenu = nfvHomeCustomer.getHeaderView(0);
        tvNameCustomerMenuHeader = vMenu.findViewById(R.id.tvNameCustomerMenuHeader);

        //binding view infor customer
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        userId = firebaseUser.getUid();
    }

    private void navigation() {
        nfvHomeCustomer.bringToFront();
        nfvHomeCustomer.setNavigationItemSelectedListener(this);
        nfvHomeCustomer.setCheckedItem(R.id.navHomeCustomer);

        imvMenuUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dlHomCustomer.isDrawerVisible(GravityCompat.START)){
                    dlHomCustomer.closeDrawer(GravityCompat.START);
                } else {
                    dlHomCustomer.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigation();
    }

    private void animateNavigation() {
        dlHomCustomer.setScrimColor(getResources().getColor(R.color.colorPrimary));
        dlHomCustomer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                final float diffScaleOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaleOffset;
                contentHomeCustomer.setScaleX(offsetScale);
                contentHomeCustomer.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentHomeCustomer.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentHomeCustomer.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navHomeCustomer:
                break;
            case R.id.navLogoutCustomer:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeCustomerActivity.this, LoginActivity.class));
                break;
            case R.id.navInformationCustomer:
                Intent intent = new Intent(HomeCustomerActivity.this, AccountProfile.class);
                intent.putExtra("customer", customer);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if(dlHomCustomer.isDrawerVisible(GravityCompat.START)){
            dlHomCustomer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
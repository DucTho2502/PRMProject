package com.example.petcare.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petcare.R;
import com.example.petcare.account.LoginActivity;
import com.example.petcare.model.Customer;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //firebase
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userId;

    //infor admin
    private TextView tvNameAdminMenuHeader;
    private NavigationView navAdmin;
    private View vMenu;

    private DrawerLayout drawerLayoutAdmin;
    private Toolbar toolbarAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bindingView();
        bindingAction();
    }


    private void bindingView() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        userId = firebaseUser.getUid();
        navAdmin = findViewById(R.id.navAdmin);
        vMenu = navAdmin.getHeaderView(0);
        tvNameAdminMenuHeader = vMenu.findViewById(R.id.tvNameAdminMenuHeader);

        drawerLayoutAdmin = findViewById(R.id.drawer_layout_admin);
        toolbarAdmin = findViewById(R.id.toolbarAdmin);
    }

    private void bindingAction() {
        showInforAdmin();

        setSupportActionBar(toolbarAdmin);

        navAdmin.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutAdmin, toolbarAdmin, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayoutAdmin.addDrawerListener(toggle);

        toggle.syncState();

        navAdmin.setNavigationItemSelectedListener(this);
        navAdmin.setCheckedItem(R.id.navHomeAdmin);
    }

    private void showInforAdmin() {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customer = snapshot.getValue(Customer.class);

                if(customer != null){
                    String name = customer.getFullName();
                    tvNameAdminMenuHeader.setText(customer.getFullName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeAdminActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navHomeAdmin:
                break;
            case R.id.navLogoutAdmin:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeAdminActivity.this, LoginActivity.class));
                break;
        }

        drawerLayoutAdmin.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayoutAdmin.isDrawerOpen(GravityCompat.START)){
            drawerLayoutAdmin.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
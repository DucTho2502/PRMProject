package com.example.petcare.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.petcare.R;
import com.example.petcare.account.LoginActivity;
import com.example.petcare.model.Customer;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class InformationCustomerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView tv_fullname_account_profile;
    private TextView tv_username_account_profile;
    private EditText edt_fullname_account_profile;
    private EditText edt_email_account_profile;
    private EditText edt_phone_account_profile;
    private EditText edt_password_account_profile;
    private Button btn_update_account_profile;
    //navigation
    private DrawerLayout dlHomCustomer;
    private NavigationView nfvHomeCustomer;
    private ImageView imvMenuUi;
    private static  float END_SCALE = 0.7f;
    private LinearLayout contentAccountCustomer;
    private TextView tvNameCustomerMenuHeader;
    private View vMenu;

    private Customer customer;

    private void bindingView() {
        tv_fullname_account_profile = findViewById(R.id.tv_fullname_account_profile);
        tv_username_account_profile = findViewById(R.id.tv_username_account_profile);
        edt_fullname_account_profile = findViewById(R.id.edt_fullname_account_profile);
        edt_email_account_profile = findViewById(R.id.edt_email_account_profile);
        edt_phone_account_profile = findViewById(R.id.edt_phone_account_profile);
        edt_password_account_profile = findViewById(R.id.edt_password_account_profile);
        btn_update_account_profile = findViewById(R.id.btn_update_account_profile);
        dlHomCustomer = findViewById(R.id.drawer_layout1);
        nfvHomeCustomer = findViewById(R.id.navigation_customer1);
        imvMenuUi = findViewById(R.id.imvMenuUi1);
        contentAccountCustomer = findViewById(R.id.contentAccountCustomer);

        //binding view full name in menu header
        vMenu = nfvHomeCustomer.getHeaderView(0);
        tvNameCustomerMenuHeader = vMenu.findViewById(R.id.tvNameCustomerMenuHeader);

        customer = (Customer) getIntent().getSerializableExtra("customer");
    }

    private void bindingAction() {
        navigation();
        showInforCustomer();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_customer);
        bindingView();
        bindingAction();
    }
    private void navigation() {
        nfvHomeCustomer.bringToFront();
        nfvHomeCustomer.setNavigationItemSelectedListener(this);
        nfvHomeCustomer.setCheckedItem(R.id.navInformationCustomer);

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
                contentAccountCustomer.setScaleX(offsetScale);
                contentAccountCustomer.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentAccountCustomer.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentAccountCustomer.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navHomeCustomer:
                startActivity(new Intent(InformationCustomerActivity.this, HomeCustomerActivity.class));
                break;
            case R.id.navLogoutCustomer:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(InformationCustomerActivity.this, LoginActivity.class));
                break;
            case R.id.navInformationCustomer:
                break;
        }
        return true;
    }
    private void showInforCustomer() {
        tv_fullname_account_profile.setText(customer.getFullName());
        tv_username_account_profile.setText(customer.getUserName());
        tvNameCustomerMenuHeader.setText(customer.getFullName());
        edt_fullname_account_profile.setText(customer.getFullName());
        edt_email_account_profile.setText(customer.getEmail());
        edt_phone_account_profile.setText(customer.getPhone());
    }

}
package com.yuni.sparepart.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yuni.sparepart.R;
import com.yuni.sparepart.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView txtuserName, txtnamaLengkap, txtemail, txtnoTelepon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile User");

        txtuserName = (TextView) findViewById(R.id.userName);
        txtnamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        txtemail = (TextView) findViewById(R.id.email);
        txtnoTelepon = (TextView) findViewById(R.id.noTelepon);

        txtuserName.setText(PrefSetting.userName);
        txtnamaLengkap.setText(PrefSetting.namaLengkap);
        txtemail.setText(PrefSetting.email);
        txtnoTelepon.setText(PrefSetting.noTelepon);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }
}
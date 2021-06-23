package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Adminuserlogin extends AppCompatActivity {
Button adminlogin;
Button userlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuserlogin);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();


        adminlogin=findViewById(R.id.button3);
        userlogin=findViewById(R.id.button4);


        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Adminuserlogin.this,Login.class);
                startActivity(intent);
            }
        });
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Adminuserlogin.this,Adminlogin.class);
                startActivity(intent);
            }
        });



    }
}
package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class adminbusdisplay extends AppCompatActivity {
RecyclerView businfoadmin;
adminbusdetailsadapter adminbusdetailsadapter;
SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminbusdisplay);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        businfoadmin=findViewById(R.id.BusDisplayrecycleview2);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        businfoadmin.setLayoutManager(new LinearLayoutManager(this));


        String str=getIntent().getStringExtra("adminphonenumber");
        FirebaseRecyclerOptions<adminbusdetailsmodel> options =
                new FirebaseRecyclerOptions.Builder< adminbusdetailsmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails").orderByChild("adminphonenumber").equalTo(str), adminbusdetailsmodel.class)
                        .build();

        adminbusdetailsadapter=new adminbusdetailsadapter(options);
        adminbusdetailsadapter.startListening();
        businfoadmin.setAdapter(adminbusdetailsadapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        adminbusdetailsadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adminbusdetailsadapter.stopListening();
    }

}
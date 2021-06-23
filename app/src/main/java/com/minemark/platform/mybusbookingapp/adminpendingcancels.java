package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class adminpendingcancels extends AppCompatActivity {
RecyclerView adminpendingrecview;
adminpendingcancelsadapter adminpendingcancelsadapter;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpendingcancels);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        adminpendingrecview=findViewById(R.id.BusDisplayrecycleview4);
        adminpendingrecview.setLayoutManager(new LinearLayoutManager(this));
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        String adminphonenumber=preferences.getString("adminphonenumber","454");

        FirebaseRecyclerOptions<adminpendingcancelmodel> options =
                new FirebaseRecyclerOptions.Builder<adminpendingcancelmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("userpendingcancels").orderByChild("adminphonenumber").equalTo(adminphonenumber),adminpendingcancelmodel.class)
                        .build();

        adminpendingcancelsadapter=new adminpendingcancelsadapter(options);
        adminpendingcancelsadapter.startListening();
        adminpendingrecview.setAdapter(adminpendingcancelsadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adminpendingcancelsadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adminpendingcancelsadapter.stopListening();
    }
}
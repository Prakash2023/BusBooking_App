package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class userpendingcancels extends AppCompatActivity {
RecyclerView pendingcancelsrecyview;
pendingcancelsadapter pendingcancelsadapter;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpendingcancels);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        pendingcancelsrecyview=findViewById(R.id.BusDisplayrecycleview3);
        pendingcancelsrecyview.setLayoutManager(new LinearLayoutManager(this));
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        String phonenumber=preferences.getString("phonenumber","454");
        //Toast.makeText(getApplicationContext(),phonenumber,Toast.LENGTH_LONG).show();

        FirebaseRecyclerOptions<userpendingcancelmodel> options =
                new FirebaseRecyclerOptions.Builder<userpendingcancelmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("userpendingcancels").orderByChild("phonenumber").equalTo(phonenumber),userpendingcancelmodel.class)
                        .build();

        pendingcancelsadapter=new pendingcancelsadapter(options);
        pendingcancelsadapter.startListening();
        pendingcancelsrecyview.setAdapter(pendingcancelsadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        pendingcancelsadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        pendingcancelsadapter.stopListening();
    }
}
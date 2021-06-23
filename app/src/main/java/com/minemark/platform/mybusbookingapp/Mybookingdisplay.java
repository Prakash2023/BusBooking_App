package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Mybookingdisplay extends AppCompatActivity {
RecyclerView mybookinginfo;
mybookingadapter mybookingadapter;
SharedPreferences preferences;
    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookingdisplay);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        mybookinginfo=findViewById(R.id.mybookingrecview);
        mybookinginfo.setLayoutManager(new LinearLayoutManager(Mybookingdisplay.this));
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);

       String phonenumber=preferences.getString("phonenumber","454");
       //String phonenumber="9876543210";
        //Toast.makeText(Mybookingdisplay.this,phonenumber,Toast.LENGTH_LONG).show();
        if(getIntent().getStringExtra("checkadmin").equals("admin"))
        {
            phonenumber=(String) getIntent().getStringExtra("phonenumber1");
        }
        if(getIntent().getStringExtra("checkadmin").equals("user"))
        {
            phonenumber=preferences.getString("phonenumber","454");
        }

        FirebaseRecyclerOptions<mybookingmodel> options =
                new FirebaseRecyclerOptions.Builder<mybookingmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("userbooking").child(phonenumber), mybookingmodel.class)
                        .build();

        mybookingadapter=new mybookingadapter(options);
        mybookingadapter.startListening();
        mybookinginfo.setAdapter(mybookingadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mybookingadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mybookingadapter.stopListening();
 }
}


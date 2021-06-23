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

public class Bus_Display extends AppCompatActivity {
    RecyclerView Businfo;
    myadapter adapter;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__display);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        Businfo=findViewById(R.id.BusDisplayrecycleview);

        Businfo.setLayoutManager(new LinearLayoutManager(this));
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);






        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=preferences.edit();
        editor.putString("enterdate1",getIntent().getStringExtra("enterdate"));
        editor.apply();




//




//        String enterdate=getIntent().getStringExtra("enterdate");
//
//
//        Intent intent1=new Intent(Bus_Display.this,Main3Activity.class);
//        intent1.putExtra("enterdate2",enterdate);



        String str=preferences.getString("bus_name","").toLowerCase();
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusDetails").orderByChild("bus_name").startAt(str).endAt(str+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options);
        adapter.startListening();
        Businfo.setAdapter(adapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
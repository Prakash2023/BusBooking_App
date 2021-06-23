package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Userfunction extends AppCompatActivity {
Button newbooking;
Button mybooking;
Button pendingcancels;
Button addbankaccount;
SharedPreferences preferences;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfunction);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        newbooking=findViewById(R.id.button6);
        mybooking=findViewById(R.id.button5);
        pendingcancels=findViewById(R.id.button18);
        addbankaccount=findViewById(R.id.button19);

        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);

        final String phonenumber=preferences.getString("phonenumber","454");
        //final String phonenumber="098tr9";

        final int[] checkuseraccount = {0};
        final String email = preferences.getString("Email","");
        final String username=preferences.getString("username","");

        String[] str=email.split("@");
        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
        DatabaseReference userdata=database.child("userdetails");
        DatabaseReference user=userdata.child(str[0]);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("userbankaccount1").exists())
                {
                    checkuseraccount[0]=1;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Toast.makeText(getApplicationContext(),preferences.getString("Email","rtew"),Toast.LENGTH_LONG).show();

        newbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(checkuseraccount[0]==1)
             {
                 Intent intent=new Intent(Userfunction.this,SearchBus.class);
                 startActivity(intent);
             }
             else
             {
                 Toast.makeText(Userfunction.this,"First add your Bank Account",Toast.LENGTH_SHORT).show();
             }


            }
        });
        mybooking.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                if(checkuseraccount[0]==1)
                {
                    Intent intent=new Intent(Userfunction.this,Mybookingdisplay.class);
                    intent.putExtra("checkadmin","user");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Userfunction.this,"First add your Bank Account",Toast.LENGTH_SHORT).show();
                }


            }
        });
        pendingcancels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkuseraccount[0]==1)
                {
                    Intent intent=new Intent(Userfunction.this,userpendingcancels.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Userfunction.this,"First add your Bank Account",Toast.LENGTH_SHORT).show();
                }


            }
        });
        addbankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),userupiid.class);
                intent.putExtra("email",email);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        } else {
            //Toast.makeText(getBaseContext(), "Click two times to close an activity", Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }
}
}
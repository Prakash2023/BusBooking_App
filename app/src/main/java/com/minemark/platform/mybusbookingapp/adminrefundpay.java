package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class adminrefundpay extends AppCompatActivity {
Button refundpay;
TextView phonetxt,nametxt,banknametxt,bankacnotxt,bankifsctxt,amounttxt;
ProgressBar progressBar;
    private  String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminrefundpay);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();


        refundpay=findViewById(R.id.button23);
        phonetxt=findViewById(R.id.textView66);
        nametxt=findViewById(R.id.textView55);
        banknametxt=findViewById(R.id.textView57);
        bankacnotxt=findViewById(R.id.textView59);
        bankifsctxt=findViewById(R.id.textView61);
        amounttxt=findViewById(R.id.textView64);
        progressBar=findViewById(R.id.progressBar);



        phonetxt.setText(getIntent().getStringExtra("phonenumber"));
        amounttxt.setText(getIntent().getStringExtra("ticketfee"));
        final String busdatestr=getIntent().getStringExtra("busnumber")+getIntent().getStringExtra("date");
        final DatabaseReference database= FirebaseDatabase.getInstance().getReference("userphone");
        final DatabaseReference database2= FirebaseDatabase.getInstance().getReference();

        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference user=database.child(getIntent().getStringExtra("phonenumber").trim());
        try{

            user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("email").exists())
                    {
                        email=(String) snapshot.child("email").getValue();
                        String[] str=email.split("@");
                        DatabaseReference userinfo=database2.child("userdetails");
                        DatabaseReference refunduser=userinfo.child(str[0]);
                        refunduser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.child("userbankname").exists())
                                {
                                    nametxt.setText((String)snapshot.child("userbankaccountname").getValue());
                                    banknametxt.setText((String)snapshot.child("userbankname").getValue());
                                    bankacnotxt.setText((String)snapshot.child("userbankaccount1").getValue());
                                    bankifsctxt.setText((String)snapshot.child("userbankifsc").getValue());
                                }
                                progressBar.setVisibility(View.INVISIBLE);
                                refundpay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(adminrefundpay.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("Refund Paid");
                                        builder.setMessage("Are you sure you paid the refund!");
                                        builder.setPositiveButton("Confirm",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        DatabaseReference database=FirebaseDatabase.getInstance().getReference("userpendingcancels");
                                                        DatabaseReference pendincancel=database.child(getIntent().getStringExtra("phonenumber")+busdatestr);
                                                        pendincancel.child("status").setValue("Refunded");



                                                    }
                                                });
                                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }



        //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();



    }
}
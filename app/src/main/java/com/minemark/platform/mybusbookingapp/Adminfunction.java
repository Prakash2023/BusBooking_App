package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Adminfunction extends AppCompatActivity {
Button addewbus;
Button updatebus;
Button bookinginvoice;
Button mybuses;
Button pendingcancels;
Button adminupiadd;
SharedPreferences preferences;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfunction);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        addewbus=findViewById(R.id.button9);
        updatebus=findViewById(R.id.button10);
        bookinginvoice=findViewById(R.id.button12);
        mybuses=findViewById(R.id.button14);
        pendingcancels=findViewById(R.id.button20);
        adminupiadd=findViewById(R.id.button21);




        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        final String adminphonenumber=preferences.getString("adminphonenumber","");
        final String email = preferences.getString("adminemail","");
        final String username=preferences.getString("adminname","");

        //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
        final int[] checkadminaccount = {0};
        String[] str=email.split("@");
        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
        DatabaseReference userdata=database.child("admindetails");
        DatabaseReference user=userdata.child(str[0]);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("adminupiid").exists())
                {
                    checkadminaccount[0]=1;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        addewbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkadminaccount[0]==1)
                {
                    Intent intent=new Intent(getApplicationContext(),BusRegistration.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"First add your Upi Id",Toast.LENGTH_SHORT).show();
                }

            }
        });

        updatebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkadminaccount[0]==1)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Adminfunction.this);
                    builder.setCancelable(true);
                    builder.setTitle("Update Bus Info");
                    builder.setMessage("Enter the bus number");
                    final EditText input = new EditText(Adminfunction.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    input.setLayoutParams(lp);
                    builder.setView(input);
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(input.getText().toString().trim().equals(""))
                                    {
                                        Toast.makeText(Adminfunction.this,"Enter a Bus number",Toast.LENGTH_SHORT).show();
                                        return;

                                    }
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference bus_number=database.getReference("BusDetails");
                                    DatabaseReference businfo=bus_number.child(input.getText().toString().trim());



                                    businfo.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()&&snapshot.child("adminphonenumber").getValue().equals(adminphonenumber))
                                            {
                                                Intent intent=new Intent(Adminfunction.this,busupdatewindow.class);
                                                intent.putExtra("busnumberupdate",input.getText().toString().trim());
                                                startActivity(intent);

                                            }
                                            else
                                            {
                                                Toast.makeText(Adminfunction.this,"Bus number not found",Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

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
                else
                {
                    Toast.makeText(getApplicationContext(),"First add your Upi Id",Toast.LENGTH_SHORT).show();
                }




            }
        });

        bookinginvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkadminaccount[0]==1)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Adminfunction.this);
                    builder.setCancelable(true);
                    builder.setTitle("Booking and Invoice");
                    builder.setMessage("Enter the User Phone number whome booking you want to see");
                    final EditText input = new EditText(Adminfunction.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    input.setLayoutParams(lp);
                    builder.setView(input);
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(input.getText().toString().trim().equals(""))
                                    {
                                        Toast.makeText(Adminfunction.this,"Enter a Phone number",Toast.LENGTH_SHORT).show();
                                        return;

                                    }
                                    else
                                    {
                                        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
                                        final DatabaseReference phone_number=database.child("userbooking");
                                        phone_number.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.child(input.getText().toString().trim()).exists())
                                                {
                                                    Intent intent=new Intent(getApplicationContext(),Mybookingdisplay.class);
                                                    intent.putExtra("checkadmin","admin");
                                                    intent.putExtra("phonenumber1",input.getText().toString().trim());
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    Toast.makeText(Adminfunction.this," No Booking Found For "+input.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

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
                else
                {
                    Toast.makeText(getApplicationContext(),"First add your Upi Id",Toast.LENGTH_SHORT).show();
                }



            }
        });


        mybuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkadminaccount[0]==1)
                {
                    Intent intent=new Intent(getApplicationContext(),adminbusdisplay.class);
                    intent.putExtra("adminphonenumber",adminphonenumber);

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"First add your Upi Id",Toast.LENGTH_SHORT).show();
                }


            }
        });

        pendingcancels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkadminaccount[0]==1)
                {
                    Intent intent=new Intent(Adminfunction.this,adminpendingcancels.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"First add your Upi Id",Toast.LENGTH_SHORT).show();
                }

            }
        });

        adminupiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),adminupiadd.class);
                startActivity(intent);
            }
        });






    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            startActivity(new Intent(getApplicationContext(),Adminlogin.class));
            finish();
        } else {
            //Toast.makeText(getBaseContext(), "Click two times to close an activity", Toast.LENGTH_SHORT).show(); }
            mBackPressed = System.currentTimeMillis();
        }
    }

}
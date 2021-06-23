package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class adminupiadd extends AppCompatActivity {
EditText adminupinameedt,adminupicompanynameedt,adminupiid1edt,adminupiid2edt;
Button adminupiadd;
CheckBox agree;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupiadd);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        adminupinameedt=findViewById(R.id.editTextTextPersonName6);
        adminupicompanynameedt=findViewById(R.id.editTextTextPersonName7);
        adminupiid1edt=findViewById(R.id.editTextTextPersonName8);
        adminupiid2edt=findViewById(R.id.editTextTextPersonName9);
        agree=findViewById(R.id.checkBox2);
        adminupiadd=findViewById(R.id.button22);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);



        adminupiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email,phonenumber,username,check,adminupiname,adminupicompanyname,adminupiid1,adminupiid2;
                 adminupiname=adminupinameedt.getText().toString().trim();
                 adminupicompanyname=adminupicompanynameedt.getText().toString().trim();
                 adminupiid1=adminupiid1edt.getText().toString().trim();
                 adminupiid2=adminupiid2edt.getText().toString().trim();
                 email=preferences.getString("adminemail","");
                 phonenumber=preferences.getString("adminphonenumber","");
                 username=preferences.getString("adminname","");
                 check="admin";




                if(!adminupiname.equals("")&&!adminupicompanyname.equals("")&&!adminupiid1.equals("")&&!adminupiid2.equals(""))
                {
                    if(agree.isChecked())
                    {
                        if(adminupiid1.equals(adminupiid2))
                        {
                            if(isConnectionAvailable(adminupiadd.this)) {
                                String[] str = email.split("@");
                                adminupiidholder obj = new adminupiidholder(email, phonenumber, username, check, adminupiname, adminupicompanyname, adminupiid1);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference userdata = database.getReference("admindetails");
                                userdata.child(str[0]).setValue(obj);

                                Toast.makeText(getApplicationContext(), "Bank Details added successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Adminfunction.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"UPI Id enterned don't match!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Click The Chekbox!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All Fields are Required!",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
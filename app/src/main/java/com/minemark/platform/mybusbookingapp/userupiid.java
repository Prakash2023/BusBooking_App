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

public class userupiid extends AppCompatActivity {
EditText bankaccountname,bankname,bankaccount1,bankaccount2,bankifsc;
Button addbankaccount;
CheckBox agree;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupiid);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        bankaccountname=findViewById(R.id.editTextTextPersonName);
        bankname=findViewById(R.id.editTextTextPersonName5);
        bankaccount1=findViewById(R.id.editTextTextPersonName2);
        bankaccount2=findViewById(R.id.editTextTextPersonName3);
        bankifsc=findViewById(R.id.editTextTextPersonName4);
        addbankaccount=findViewById(R.id.button17);
        agree=findViewById(R.id.checkBox);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);


        final String[] username = new String[1];
        final String[] phonenumber = new String[1];
        final String[] email = new String[1];
        final String[] userbankaccountname = new String[1];
        final String[] userbankname = new String[1];
        final String[] userbankaccount1 = new String[1];
        final String[] userbankaccount2 = new String[1];
        final String[] userbankifsc = new String[1];


        addbankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userbankaccountname[0] =bankaccountname.getText().toString().trim();
                userbankname[0] =bankname.getText().toString().trim();
                userbankaccount1[0] =bankaccount1.getText().toString().trim();
                userbankaccount2[0] =bankaccount2.getText().toString().trim();
                userbankifsc[0] =bankifsc.getText().toString().trim();
                email[0] =getIntent().getStringExtra("email");
                username[0] =getIntent().getStringExtra("username");
                phonenumber[0] =preferences.getString("phonenumber","33");
                if(agree.isChecked())
                {
                    if(!userbankaccountname[0].equals("")&&!userbankname[0].equals("")&&!userbankaccount1[0].equals("")&&!userbankaccount2[0].equals("")&&!userbankifsc[0].equals(""))
                    {
                        if(userbankaccount1[0].equals(userbankaccount2[0]))
                        {
                            if(isConnectionAvailable(userupiid.this))
                            {
                                String[] str= email[0].split("@");
                                userbankdataholder obj=new userbankdataholder(username[0], phonenumber[0], email[0], userbankaccountname[0], userbankname[0], userbankaccount1[0], userbankifsc[0]);

                                FirebaseDatabase database= FirebaseDatabase.getInstance();
                                DatabaseReference userdata=database.getReference("userdetails");
                                userdata.child(str[0]).setValue(obj);

                                Toast.makeText(getApplicationContext(),"Bank Details added successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),Userfunction.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Bank Account entered do not match each other",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"All fileds are Required",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Click the checkbox",Toast.LENGTH_SHORT).show();
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
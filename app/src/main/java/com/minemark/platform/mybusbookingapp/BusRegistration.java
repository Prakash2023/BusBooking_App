package com.minemark.platform.mybusbookingapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class BusRegistration extends AppCompatActivity {

    EditText Bus_Number;
    EditText Starting_Point;
    EditText  Stoping_Point;
    EditText Starting_Time;
    EditText Stoping_Time;
    EditText Ticket_Fees;
    Button Add_Bus;
    RadioButton ACyes;
    RadioButton ACno;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_registration);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        Bus_Number=findViewById(R.id.editText1);
        Starting_Point=findViewById(R.id.editText2);
        Stoping_Point=findViewById(R.id.editText3);
        Starting_Time=findViewById(R.id.editText4);
        Stoping_Time=findViewById(R.id.editText5);
        Ticket_Fees=findViewById(R.id.editText6);
        Add_Bus=findViewById(R.id.button1);
        ACyes=findViewById(R.id.radioButton);
        ACno=findViewById(R.id.radioButton2);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);



        //ACno.setChecked(true);



//        final String Busnumber=Bus_Number.getText().toString().trim();
//        final String Start_Point=Starting_Point.getText().toString().trim();
//        final String Stop_Point=Stoping_Point.getText().toString().trim();
//        final String Start_Time=Starting_Time.getText().toString().trim();
//        final String Stop_Time=Stoping_Time.getText().toString().trim();;
//        final String Ticketfee=Ticket_Fees.getText().toString().trim();
        final String[] ACstatus = {"No"};
        ACyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACstatus[0] ="Yes";
                ACno.setChecked(false);
            }
        });
        ACno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACstatus[0] ="No";
                ACyes.setChecked(false);
            }
        });
        Add_Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bus_number=Bus_Number.getText().toString().trim().toLowerCase();
                 String Start_Point=Starting_Point.getText().toString().trim().toLowerCase();
                 String Stop_Point=Stoping_Point.getText().toString().trim().toLowerCase();
                 String Start_Time=Starting_Time.getText().toString().trim();
                 String Stop_Time=Stoping_Time.getText().toString().trim();;
                 String Ticketfee=Ticket_Fees.getText().toString().trim();
                 String Bus_name=Start_Point+Stop_Point;

                 String adminphonenumber=preferences.getString("adminphonenumber","xyz");
//
//                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=preferences.edit();
//                editor.putString("bus_name",Bus_name);
//                editor.apply();

                if(!Bus_number.equals("")&&!Start_Point.equals("")&&!Stop_Point.equals("")
                        &&!Start_Time.equals("")&&!Stop_Time.equals("")&&!Bus_name.equals("")&&!ACstatus[0].equals("")&&!Ticketfee.equals(""))
                {
                    String[] str1=Start_Time.split(":");
                    String[] str2=Stop_Time.split(":");
                    if(Start_Time.length()==5&&str1[0].length()==2&&str1[1].length()==2&&Stop_Time.length()==5&&str2[0].length()==2&&str2[1].length()==2)
                    {
                        dataholder obj=new dataholder(ACstatus[0],Bus_name,Bus_number,Start_Point,Start_Time,Stop_Point, Stop_Time,Ticketfee,adminphonenumber);
                        FirebaseDatabase busdb=FirebaseDatabase.getInstance();

                        DatabaseReference node= busdb.getReference("BusDetails");
                        node.child(Bus_number).setValue(obj);

                        Toast.makeText(getApplicationContext(),"Bus Added Successfully",Toast.LENGTH_LONG).show();



                        Intent intent = new Intent(BusRegistration.this,Adminfunction.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BusRegistration.this,"Time should be in Strict HH:mm 24 hours format",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All Fields are Neccessary!",Toast.LENGTH_LONG).show();
                }





            }
        });

    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Adminfunction.class));
        finish();
    }
}
package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class busupdatewindow extends AppCompatActivity {
    EditText Bus_Number;
    EditText Starting_Point;
    EditText  Stoping_Point;
    EditText Starting_Time;
    EditText Stoping_Time;
    EditText Ticket_Fees;
    Button Update_Bus;
    RadioButton ACyes;
    RadioButton ACno;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busupdatewindow);


        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        Bus_Number=findViewById(R.id.editText1);
        Starting_Point=findViewById(R.id.editText2);
        Stoping_Point=findViewById(R.id.editText3);
        Starting_Time=findViewById(R.id.editText4);
        Stoping_Time=findViewById(R.id.editText5);
        Ticket_Fees=findViewById(R.id.editText6);
        Update_Bus=findViewById(R.id.button1);
        ACyes=findViewById(R.id.radioButton);
        ACno=findViewById(R.id.radioButton2);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        String busnumber=getIntent().getStringExtra("busnumberupdate");

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference bus_number=database.getReference("BusDetails");
        DatabaseReference businfo=bus_number.child(busnumber);
        businfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Bus_Number.setText((String)snapshot.child("bus_number").getValue());
                 Starting_Point.setText((String)snapshot.child("start_Point").getValue());
                 Stoping_Point.setText((String)snapshot.child("stop_Point").getValue());
                 Starting_Time.setText((String)snapshot.child("start_Time").getValue());
                 Stoping_Time.setText((String)snapshot.child("stop_Time").getValue());
                 Ticket_Fees.setText((String)snapshot.child("ticketfee").getValue());
                 if(snapshot.child("acstatus").getValue().equals("Yes"))
                 {
                     ACyes.setChecked(true);
                     ACno.setChecked(false);
                 }
                if(snapshot.child("acstatus").getValue().equals("No"))
                {
                    ACno.setChecked(true);
                    ACyes.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        final String[] ACstatus = {"No"};

        if(ACyes.isChecked())
        {
            ACstatus[0] ="Yes";
            ACno.setChecked(false);
        }
        if(ACno.isChecked())
        {
            ACstatus[0] ="No";
            ACyes.setChecked(false);
        }
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
        Update_Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bus_number=Bus_Number.getText().toString().trim();
                String Start_Point=Starting_Point.getText().toString().trim().toLowerCase();
                String Stop_Point=Stoping_Point.getText().toString().trim().toLowerCase();
                String Start_Time=Starting_Time.getText().toString().trim();
                String Stop_Time=Stoping_Time.getText().toString().trim();;
                String Ticketfee=Ticket_Fees.getText().toString().trim();
                String Bus_name=Start_Point+Stop_Point;
                String adminphonenumber=preferences.getString("adminphonenumber","");





                dataholder obj=new dataholder(ACstatus[0],Bus_name,Bus_number,Start_Point,Start_Time,Stop_Point, Stop_Time,Ticketfee,adminphonenumber);
                FirebaseDatabase busdb=FirebaseDatabase.getInstance();

                DatabaseReference node= busdb.getReference("BusDetails");
                node.child(Bus_number).setValue(obj);

                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(busupdatewindow.this,Adminfunction.class);
                startActivity(intent);

            }
        });


    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Adminfunction.class));
        finish();
    }
}
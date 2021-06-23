package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class adminbusseatdata extends AppCompatActivity {
    TextView sourceshowtxt;
    TextView destshowtxt;
    TextView timedisplay;

    TextView ticketfeedisplay;
    TextView acstatusdisplay;
    TextView busnumberdisplay;
    TextView seatdetailstxt;
    TextView totalpricetxt;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminbusseatdata);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();


        final ImageView[] seat=new ImageView[33];
        String checkbusdate=getIntent().getStringExtra("checkbusdate");
        sourceshowtxt=findViewById(R.id.sourceshowtxt);
        destshowtxt=findViewById(R.id.destshowtxt);
        timedisplay=findViewById(R.id.timedisplaytxt);
        acstatusdisplay=findViewById(R.id.acstatusdisplaytxt);
        ticketfeedisplay=findViewById(R.id.ticketfeedisplayTxt);
        busnumberdisplay=findViewById(R.id.busnumbertxt);
        seatdetailstxt=findViewById(R.id.seatdeatailstxt);
        totalpricetxt=findViewById(R.id.totalpricetxt);





        sourceshowtxt.setText(getIntent().getStringExtra("sourcekey"));

        destshowtxt.setText(getIntent().getStringExtra("destkey"));
        acstatusdisplay.setText("AC: "+getIntent().getStringExtra("acstatuskey"));
        busnumberdisplay.setText("Bus No: "+getIntent().getStringExtra("busnumberkey"));
        String busnumberdisp1=getIntent().getStringExtra("busnumberkey");
        String t1=getIntent().getStringExtra("sourcetimekey");
        String t2=getIntent().getStringExtra("desttimekey");
        String t3="Time: "+t1+"-"+t2;
        timedisplay.setText(t3);

        ticketfeedisplay.setText(getIntent().getStringExtra("ticketfeekey")+" Rs");





        for(int i=1;i<33;i++)
        {
            String buttonID = "seat" + i;

            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            seat[i]=findViewById(resID);
        }
        final int[] count = {0};
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference BusSeatDetails = root.child("BusSeatDetails");
        DatabaseReference users = BusSeatDetails.child(checkbusdate);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i=1;i<33;i++)
                {
                    if(snapshot.child("seatstatusstr"+i).exists())
                    {
                        seat[i].setImageResource(R.drawable.booked_img);
                        count[0]++;
                    }
                    else
                    {
                        seat[i].setImageResource(R.drawable.available_img);
                    }
                }
                seatdetailstxt.setText(String.valueOf(count[0]));
                totalpricetxt.setText(String.valueOf(count[0]*Integer.parseInt(getIntent().getStringExtra("ticketfeekey")))+" Rs");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
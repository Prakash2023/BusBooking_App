package com.minemark.platform.mybusbookingapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Main3Activity extends AppCompatActivity  {
    TextView sourceshowtxt;
    TextView destshowtxt;
    TextView timedisplay;

    TextView ticketfeedisplay;
    TextView acstatusdisplay;
    TextView busnumberdisplay;
    TextView seatdetailstxt;
    TextView totalpricetxt;
    SharedPreferences preferences;
    Button proceedtocnfrm;
    Calendar calendar;
    SimpleDateFormat dateFormat,dateFormat2;
    ImageView seat1,seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9,seat10,seat11,seat12,seat13,seat14,seat15,seat16,seat17,seat18,seat19,seat20,seat21,seat22,seat23,seat24,seat25,seat26,seat27,seat28,seat29,seat30,seat31,seat32;
    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        sourceshowtxt=findViewById(R.id.sourceshowtxt);
        destshowtxt=findViewById(R.id.destshowtxt);
        timedisplay=findViewById(R.id.timedisplaytxt);
        acstatusdisplay=findViewById(R.id.acstatusdisplaytxt);
        ticketfeedisplay=findViewById(R.id.ticketfeedisplayTxt);
        busnumberdisplay=findViewById(R.id.busnumbertxt);
        seatdetailstxt=findViewById(R.id.seatdeatailstxt);
        totalpricetxt=findViewById(R.id.totalpricetxt);
        proceedtocnfrm=findViewById(R.id.proceedtonfrm);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);

        seat1=findViewById(R.id.seat1);
        seat2=findViewById(R.id.seat2);
        seat3=findViewById(R.id.seat3);
        seat4=findViewById(R.id.seat4);
        seat5=findViewById(R.id.seat5);
        seat6=findViewById(R.id.seat6);
        seat7=findViewById(R.id.seat7);
        seat8=findViewById(R.id.seat8);
        seat9=findViewById(R.id.seat9);
        seat10=findViewById(R.id.seat10);
        seat11=findViewById(R.id.seat11);
        seat12=findViewById(R.id.seat12);
        seat13=findViewById(R.id.seat13);
        seat14=findViewById(R.id.seat14);
        seat15=findViewById(R.id.seat15);
        seat16=findViewById(R.id.seat16);
        seat17=findViewById(R.id.seat17);
        seat18=findViewById(R.id.seat18);
        seat19=findViewById(R.id.seat19);
        seat20=findViewById(R.id.seat20);
        seat21=findViewById(R.id.seat21);
        seat22=findViewById(R.id.seat22);
        seat23=findViewById(R.id.seat23);
        seat24=findViewById(R.id.seat24);
        seat25=findViewById(R.id.seat25);
        seat26=findViewById(R.id.seat26);
        seat27=findViewById(R.id.seat27);
        seat28=findViewById(R.id.seat28);
        seat29=findViewById(R.id.seat29);
        seat30=findViewById(R.id.seat30);
        seat31=findViewById(R.id.seat31);
        seat32=findViewById(R.id.seat32);








        sourceshowtxt.setText(getIntent().getStringExtra("sourcekey").toUpperCase());

        destshowtxt.setText(getIntent().getStringExtra("destkey").toUpperCase());
        acstatusdisplay.setText("AC: "+getIntent().getStringExtra("acstatuskey"));
        busnumberdisplay.setText("Bus No: "+getIntent().getStringExtra("busnumberkey"));
        String busnumberdisp1=getIntent().getStringExtra("busnumberkey");
        String t1=getIntent().getStringExtra("sourcetimekey");
        String t2=getIntent().getStringExtra("desttimekey");
        String t3="Time: "+t1+"-"+t2;
        timedisplay.setText(t3);
        final String phonenumber=preferences.getString("phonenumber","");
        ticketfeedisplay.setText(getIntent().getStringExtra("ticketfeekey")+" Rs");

        final String busdate=preferences.getString("enterdate1","0");
        busnumberdisp1+=busdate;

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=preferences.edit();
        editor.putString("busnumber",getIntent().getStringExtra("busnumberkey"));
        editor.putString("source",getIntent().getStringExtra("sourcekey"));
        editor.putString("destination",getIntent().getStringExtra("destkey"));
        editor.putString("sourcetime",getIntent().getStringExtra("sourcetimekey"));
        editor.putString("desttime",getIntent().getStringExtra("desttimekey"));
        editor.putString("acstatus",getIntent().getStringExtra("acstatuskey"));
        editor.putString("adminphonenumber",getIntent().getStringExtra("adminnumber"));
        editor.apply();
        //busnumberdisplay.setText(busnumberdisp1);


        final int[] seatstatus=new int[33];
        seatstatus[0]=0;

        final int[] myseats=new int[33];
        Arrays.fill(myseats, 0);


//        final String[] seatstatusstr=new String[33];
//        seatstatusstr[0]="demo";
        final String[] seatstatusstr=new String[33];

        final String busnumberdisp=busnumberdisp1;
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference BusSeatDetails = root.child("BusSeatDetails");
        BusSeatDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference users = BusSeatDetails.child(busnumberdisp);
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child("seatstatusstr1").exists()) {
                            String s= (String) snapshot.child("seatstatusstr1").getValue();

                            //assert s != null;
                            if(!s.equals(""))
                            {
                                seatstatusstr[1]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat1.setImageResource(R.drawable.booked_img);
                                    myseats[1]=2;
                                    seatstatus[1]=1;


                                }
                                else
                                {
                                    seat1.setImageResource(R.drawable.booked_img);
                                    seatstatus[1]=1;

                                }
                            }
                            else
                            {
                                seat1.setImageResource(R.drawable.available_img);
                                seatstatus[1]=0;
                            }
                        }else{
                            seat1.setImageResource(R.drawable.available_img);
                            seatstatus[1]=0;
                        }
                        if (snapshot.child("seatstatusstr2").exists()) {
                            String s= (String) snapshot.child("seatstatusstr2").getValue();
                            //assert s != null;
                            if(!s.equals(""))
                            {
                                seatstatusstr[2]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat2.setImageResource(R.drawable.booked_img);
                                    seatstatus[2]=1;
                                    myseats[2]=2;
                                }
                                else
                                {
                                    seat2.setImageResource(R.drawable.booked_img);
                                    seatstatus[2]=1;
                                }
                            }
                            else
                            {
                                seat2.setImageResource(R.drawable.available_img);
                                seatstatus[2]=0;
                            }
                        }else{
                            seat2.setImageResource(R.drawable.available_img);
                            seatstatus[2]=0;
                        }
                        if (snapshot.child("seatstatusstr3").exists()) {
                            String s= (String) snapshot.child("seatstatusstr3").getValue();
                            //assert s != null;
                            if(!s.equals(""))
                            {
                                seatstatusstr[3]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat3.setImageResource(R.drawable.booked_img);
                                    seatstatus[3]=1;
                                    myseats[3]=2;
                                }
                                else
                                {
                                    seat3.setImageResource(R.drawable.booked_img);
                                    seatstatus[3]=1;
                                }
                            }
                            else {
                                seat3.setImageResource(R.drawable.available_img);
                                seatstatus[3]=0;
                            }

                        }else{
                            seat3.setImageResource(R.drawable.available_img);
                            seatstatus[3]=0;
                        }
                        if (snapshot.child("seatstatusstr4").exists()) {
                            String s= (String) snapshot.child("seatstatusstr4").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[4]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat4.setImageResource(R.drawable.booked_img);
                                    seatstatus[4]=1;
                                    myseats[4]=2;
                                }
                                else
                                {
                                    seat4.setImageResource(R.drawable.booked_img);
                                    seatstatus[4]=1;
                                }
                            }
                            else
                            {
                                seat4.setImageResource(R.drawable.available_img);
                                seatstatus[4]=0;
                            }
                        }else{
                            seat4.setImageResource(R.drawable.available_img);
                            seatstatus[4]=0;
                        }
                        if (snapshot.child("seatstatusstr5").exists()) {
                            String s= (String) snapshot.child("seatstatusstr5").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[5]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat5.setImageResource(R.drawable.booked_img);
                                    seatstatus[5]=2;
                                    myseats[5]=2;
                                }
                                else
                                {
                                    seat5.setImageResource(R.drawable.booked_img);
                                    seatstatus[5]=1;
                                }
                            }
                            else
                            {
                                seat5.setImageResource(R.drawable.available_img);
                                seatstatus[5]=0;
                            }
                        }else{
                            seat5.setImageResource(R.drawable.available_img);
                            seatstatus[5]=0;
                        }
                        if (snapshot.child("seatstatusstr6").exists()) {
                            String s= (String) snapshot.child("seatstatusstr6").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[6]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat6.setImageResource(R.drawable.booked_img);
                                    seatstatus[6]=2;
                                    myseats[6]=2;
                                }
                                else
                                {
                                    seat6.setImageResource(R.drawable.booked_img);
                                    seatstatus[6]=1;
                                }
                            }
                            else
                            {
                                seat6.setImageResource(R.drawable.available_img);
                                seatstatus[6]=0;
                            }
                        }else{
                            seat6.setImageResource(R.drawable.available_img);
                            seatstatus[6]=0;
                        }
                        if (snapshot.child("seatstatusstr7").exists()) {
                            String s= (String) snapshot.child("seatstatusstr7").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[7]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat7.setImageResource(R.drawable.booked_img);
                                    seatstatus[7]=2;
                                    myseats[7]=2;
                                }
                                else
                                {
                                    seat7.setImageResource(R.drawable.booked_img);
                                    seatstatus[7]=1;
                                }
                            }
                            else {
                                seat7.setImageResource(R.drawable.available_img);
                                seatstatus[7]=0;
                            }
                        }else{
                            seat7.setImageResource(R.drawable.available_img);
                            seatstatus[7]=0;
                        }
                        if (snapshot.child("seatstatusstr8").exists()) {
                            String s= (String) snapshot.child("seatstatusstr8").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[8]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat8.setImageResource(R.drawable.booked_img);
                                    seatstatus[8]=2;
                                    myseats[8]=2;
                                }
                                else
                                {
                                    seat8.setImageResource(R.drawable.booked_img);
                                    seatstatus[8]=1;
                                }
                            }
                            else
                            {
                                seat8.setImageResource(R.drawable.available_img);
                                seatstatus[8]=0;
                            }

                        }else{
                            seat8.setImageResource(R.drawable.available_img);
                            seatstatus[8]=0;
                        }
                        if (snapshot.child("seatstatusstr9").exists()) {
                            String s= (String) snapshot.child("seatstatusstr9").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[9]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat9.setImageResource(R.drawable.booked_img);
                                    seatstatus[9]=2;
                                    myseats[9]=2;
                                }
                                else
                                {
                                    seat9.setImageResource(R.drawable.booked_img);
                                    seatstatus[9]=1;
                                }
                            }
                            else
                            {
                                seat9.setImageResource(R.drawable.available_img);
                                seatstatus[9]=0;
                            }
                        }else{
                            seat9.setImageResource(R.drawable.available_img);
                            seatstatus[9]=0;
                        }
                        if (snapshot.child("seatstatusstr10").exists()) {
                            String s= (String) snapshot.child("seatstatusstr10").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[10]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat10.setImageResource(R.drawable.booked_img);
                                    seatstatus[10]=2;
                                    myseats[10]=2;
                                }
                                else
                                {
                                    seat10.setImageResource(R.drawable.booked_img);
                                    seatstatus[10]=1;
                                }
                            }
                            else
                            {
                                seat10.setImageResource(R.drawable.available_img);
                                seatstatus[10]=0;
                            }
                        }else{
                            seat10.setImageResource(R.drawable.available_img);
                            seatstatus[10]=0;
                        }
                        if (snapshot.child("seatstatusstr11").exists()) {
                            String s= (String) snapshot.child("seatstatusstr11").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[11]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat11.setImageResource(R.drawable.booked_img);
                                    seatstatus[11]=2;
                                    myseats[11]=2;
                                }
                                else
                                {
                                    seat11.setImageResource(R.drawable.booked_img);
                                    seatstatus[11]=1;
                                }
                            }
                            else
                            {
                                seat11.setImageResource(R.drawable.available_img);
                                seatstatus[11]=0;
                            }
                        }else{
                            seat11.setImageResource(R.drawable.available_img);
                            seatstatus[11]=0;
                        }
                        if (snapshot.child("seatstatusstr12").exists()) {
                            String s= (String) snapshot.child("seatstatusstr12").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[12]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat12.setImageResource(R.drawable.booked_img);
                                    seatstatus[12]=2;
                                    myseats[12]=2;
                                }
                                else
                                {
                                    seat12.setImageResource(R.drawable.booked_img);
                                    seatstatus[12]=1;
                                }
                            }
                            else
                            {
                                seat12.setImageResource(R.drawable.available_img);
                                seatstatus[12]=0;
                            }
                        }else{
                            seat12.setImageResource(R.drawable.available_img);
                            seatstatus[12]=0;
                        }
                        if (snapshot.child("seatstatusstr13").exists()) {
                            String s= (String) snapshot.child("seatstatusstr13").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[13]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat13.setImageResource(R.drawable.booked_img);
                                    seatstatus[13]=2;
                                    myseats[13]=2;
                                }
                                else
                                {
                                    seat13.setImageResource(R.drawable.booked_img);
                                    seatstatus[13]=1;
                                }
                            }
                            else
                            {
                                seat13.setImageResource(R.drawable.available_img);
                                seatstatus[13]=0;
                            }
                        }else{
                            seat13.setImageResource(R.drawable.available_img);
                            seatstatus[13]=0;
                        }
                        if (snapshot.child("seatstatusstr14").exists()) {
                            String s= (String) snapshot.child("seatstatusstr14").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[14]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat14.setImageResource(R.drawable.booked_img);
                                    seatstatus[14]=2;
                                    myseats[14]=2;
                                }
                                else
                                {
                                    seat14.setImageResource(R.drawable.booked_img);
                                    seatstatus[14]=1;
                                }
                            }
                            else
                            {
                                seat14.setImageResource(R.drawable.available_img);
                                seatstatus[14]=0;
                            }
                        }else{
                            seat14.setImageResource(R.drawable.available_img);
                            seatstatus[14]=0;
                        }
                        if (snapshot.child("seatstatusstr15").exists()) {
                            String s= (String) snapshot.child("seatstatusstr15").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[15]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat15.setImageResource(R.drawable.booked_img);
                                    seatstatus[15]=2;
                                    myseats[15]=2;
                                }
                                else
                                {
                                    seat15.setImageResource(R.drawable.booked_img);
                                    seatstatus[15]=1;
                                }
                            }
                            else
                            {
                                seat15.setImageResource(R.drawable.available_img);
                                seatstatus[15]=0;
                            }
                        }else{
                            seat15.setImageResource(R.drawable.available_img);
                            seatstatus[15]=0;
                        }
                        if (snapshot.child("seatstatusstr16").exists()) {
                            String s= (String) snapshot.child("seatstatusstr16").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[16]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat16.setImageResource(R.drawable.booked_img);
                                    seatstatus[16]=2;
                                    myseats[16]=2;
                                }
                                else
                                {
                                    seat16.setImageResource(R.drawable.booked_img);
                                    seatstatus[16]=1;
                                }
                            }
                            else
                            {
                                seat16.setImageResource(R.drawable.available_img);
                                seatstatus[16]=0;
                            }
                        }else{
                            seat16.setImageResource(R.drawable.available_img);
                            seatstatus[16]=0;
                        }
                        if (snapshot.child("seatstatusstr17").exists()) {
                            String s= (String) snapshot.child("seatstatusstr17").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[17]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat17.setImageResource(R.drawable.booked_img);
                                    seatstatus[17]=2;
                                    myseats[17]=2;
                                }
                                else
                                {
                                    seat17.setImageResource(R.drawable.booked_img);
                                    seatstatus[17]=1;
                                }
                            }
                            else
                            {
                                seat17.setImageResource(R.drawable.available_img);
                                seatstatus[17]=0;
                            }
                        }else{
                            seat17.setImageResource(R.drawable.available_img);
                            seatstatus[17]=0;
                        }
                        if (snapshot.child("seatstatusstr18").exists()) {
                            String s= (String) snapshot.child("seatstatusstr18").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[18]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat18.setImageResource(R.drawable.booked_img);
                                    seatstatus[18]=2;
                                    myseats[18]=2;
                                }
                                else
                                {
                                    seat18.setImageResource(R.drawable.booked_img);
                                    seatstatus[18]=1;
                                }
                            }
                            else
                            {
                                seat18.setImageResource(R.drawable.available_img);
                                seatstatus[18]=0;
                            }
                        }else{
                            seat18.setImageResource(R.drawable.available_img);
                            seatstatus[18]=0;
                        }
                        if (snapshot.child("seatstatusstr19").exists()) {
                            String s= (String) snapshot.child("seatstatusstr19").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[19]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat19.setImageResource(R.drawable.booked_img);
                                    seatstatus[19]=2;
                                    myseats[19]=2;
                                }
                                else
                                {
                                    seat19.setImageResource(R.drawable.booked_img);
                                    seatstatus[19]=1;
                                }
                            }
                            else {
                                seat19.setImageResource(R.drawable.available_img);
                                seatstatus[19]=0;
                            }
                        }else{
                            seat19.setImageResource(R.drawable.available_img);
                            seatstatus[19]=0;
                        }
                        if (snapshot.child("seatstausstr20").exists()) {
                            String s= (String) snapshot.child("seatstatusstr20").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[20]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat20.setImageResource(R.drawable.booked_img);
                                    seatstatus[20]=2;
                                    myseats[20]=2;
                                }
                                else
                                {
                                    seat20.setImageResource(R.drawable.booked_img);
                                    seatstatus[20]=1;
                                }
                            }
                            else
                            {
                                seat20.setImageResource(R.drawable.available_img);
                                seatstatus[20]=0;
                            }
                        }else{
                            seat20.setImageResource(R.drawable.available_img);
                            seatstatus[20]=0;
                        }
                        if (snapshot.child("seatstatusstr21").exists()) {
                            String s= (String) snapshot.child("seatstatusstr21").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[21]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat21.setImageResource(R.drawable.booked_img);
                                    seatstatus[21]=2;
                                    myseats[21]=2;
                                }
                                else
                                {
                                    seat21.setImageResource(R.drawable.booked_img);
                                    seatstatus[21]=1;
                                }
                            }
                            else
                            {
                                seat21.setImageResource(R.drawable.available_img);
                                seatstatus[21]=0;
                            }
                        }else{
                            seat21.setImageResource(R.drawable.available_img);
                            seatstatus[21]=0;
                        }
                        if (snapshot.child("seatstatusstr22").exists()) {
                            String s= (String) snapshot.child("seatstatusstr22").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[22]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat22.setImageResource(R.drawable.booked_img);
                                    seatstatus[22]=2;
                                    myseats[22]=2;
                                }
                                else
                                {
                                    seat22.setImageResource(R.drawable.booked_img);
                                    seatstatus[22]=1;
                                }
                            }
                            else
                            {
                                seat22.setImageResource(R.drawable.available_img);
                                seatstatus[22]=0;
                            }
                        }else{
                            seat22.setImageResource(R.drawable.available_img);
                            seatstatus[22]=0;
                        }
                        if (snapshot.child("seatstatusstr23").exists()) {
                            String s= (String) snapshot.child("seatstatusstr23").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[23]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat23.setImageResource(R.drawable.booked_img);
                                    seatstatus[23]=2;
                                    myseats[23]=2;
                                }
                                else
                                {
                                    seat23.setImageResource(R.drawable.booked_img);
                                    seatstatus[23]=1;
                                }
                            }
                            else
                            {
                                seat23.setImageResource(R.drawable.available_img);
                                seatstatus[23]=0;
                            }
                        }else{
                            seat23.setImageResource(R.drawable.available_img);
                            seatstatus[23]=0;
                        }
                        if (snapshot.child("seatstatusstr24").exists()) {
                            String s= (String) snapshot.child("seatstatusstr24").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[24]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat24.setImageResource(R.drawable.booked_img);
                                    seatstatus[24]=2;
                                    myseats[24]=2;
                                }
                                else
                                {
                                    seat24.setImageResource(R.drawable.booked_img);
                                    seatstatus[24]=1;
                                }
                            }
                            else
                            {
                                seat24.setImageResource(R.drawable.available_img);
                                seatstatus[24]=0;
                            }
                        }else{
                            seat24.setImageResource(R.drawable.available_img);
                            seatstatus[24]=0;
                        }
                        if (snapshot.child("seatstatusstr25").exists()) {
                            String s= (String) snapshot.child("seatstatusstr25").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[25]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat25.setImageResource(R.drawable.booked_img);
                                    seatstatus[25]=2;
                                    myseats[25]=2;
                                }
                                else
                                {
                                    seat25.setImageResource(R.drawable.booked_img);
                                    seatstatus[25]=1;
                                }
                            }
                            else
                            {
                                seat25.setImageResource(R.drawable.available_img);
                                seatstatus[25]=0;
                            }
                        }else{
                            seat25.setImageResource(R.drawable.available_img);
                            seatstatus[25]=0;
                        }
                        if (snapshot.child("seatstatusstr26").exists()) {
                            String s= (String) snapshot.child("seatstatusstr26").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[26]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat26.setImageResource(R.drawable.booked_img);
                                    seatstatus[26]=2;
                                    myseats[26]=2;
                                }
                                else
                                {
                                    seat26.setImageResource(R.drawable.booked_img);
                                    seatstatus[26]=1;
                                }
                            }
                            else
                            {
                                seat26.setImageResource(R.drawable.available_img);
                                seatstatus[26]=0;
                            }
                        }else{
                            seat26.setImageResource(R.drawable.available_img);
                            seatstatus[26]=0;
                        }
                        if (snapshot.child("seatstatusstr27").exists()) {
                            String s= (String) snapshot.child("seatstatusstr27").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[27]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat27.setImageResource(R.drawable.booked_img);
                                    seatstatus[27]=2;
                                    myseats[27]=2;
                                }
                                else
                                {
                                    seat27.setImageResource(R.drawable.booked_img);
                                    seatstatus[27]=1;
                                }
                            }
                            else
                            {
                                seat27.setImageResource(R.drawable.available_img);
                                seatstatus[27]=0;
                            }
                        }else{
                            seat27.setImageResource(R.drawable.available_img);
                            seatstatus[27]=0;
                        }
                        if (snapshot.child("seatstatusstr28").exists()) {
                            String s= (String) snapshot.child("seatstatusstr28").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[28]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat28.setImageResource(R.drawable.booked_img);
                                    seatstatus[28]=2;
                                    myseats[28]=2;
                                }
                                else
                                {
                                    seat28.setImageResource(R.drawable.booked_img);
                                    seatstatus[28]=1;
                                }
                            }
                            else
                            {
                                seat28.setImageResource(R.drawable.available_img);
                                seatstatus[28]=0;
                            }
                        }else{
                            seat28.setImageResource(R.drawable.available_img);
                            seatstatus[28]=0;
                        }
                        if (snapshot.child("seatstatusstr29").exists()) {
                            String s= (String) snapshot.child("seatstatusstr29").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[29]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat29.setImageResource(R.drawable.booked_img);
                                    seatstatus[29]=2;
                                    myseats[29]=2;
                                }
                                else
                                {
                                    seat29.setImageResource(R.drawable.booked_img);
                                    seatstatus[29]=1;
                                }
                            }
                            else{
                                seat29.setImageResource(R.drawable.available_img);
                                seatstatus[29]=0;
                            }
                        }else{
                            seat29.setImageResource(R.drawable.available_img);
                            seatstatus[29]=0;
                        }
                        if (snapshot.child("seatstatusstr30").exists()) {
                            String s= (String) snapshot.child("seatstatusstr30").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[30]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat30.setImageResource(R.drawable.booked_img);
                                    seatstatus[30]=2;
                                    myseats[30]=2;
                                }
                                else
                                {
                                    seat30.setImageResource(R.drawable.booked_img);
                                    seatstatus[30]=1;
                                }
                            }
                            else
                            {
                                seat30.setImageResource(R.drawable.available_img);
                                seatstatus[30]=0;
                            }
                        }else{
                            seat30.setImageResource(R.drawable.available_img);
                            seatstatus[30]=0;
                        }
                        if (snapshot.child("seatstatusstr31").exists()) {
                            String s= (String) snapshot.child("seatstatusstr31").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[31]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat31.setImageResource(R.drawable.booked_img);
                                    seatstatus[31]=2;
                                    myseats[31]=2;
                                }
                                else
                                {
                                    seat31.setImageResource(R.drawable.booked_img);
                                    seatstatus[31]=1;
                                }
                            }
                            else
                            {
                                seat31.setImageResource(R.drawable.available_img);
                                seatstatus[31]=0;
                            }
                        }else{
                            seat31.setImageResource(R.drawable.available_img);
                            seatstatus[31]=0;
                        }
                        if (snapshot.child("seatstatusstr32").exists()) {
                            String s= (String) snapshot.child("seatstatusstr32").getValue();
                            if(!s.equals(""))
                            {
                                seatstatusstr[32]=s;
                                if(s.equals(phonenumber))
                                {
                                    seat32.setImageResource(R.drawable.booked_img);
                                    seatstatus[32]=2;
                                    myseats[32]=2;
                                }
                                else
                                {
                                    seat32.setImageResource(R.drawable.booked_img);
                                    seatstatus[32]=1;
                                }
                            }
                            else
                            {
                                seat32.setImageResource(R.drawable.available_img);
                                seatstatus[32]=0;
                            }
                        }else{
                            seat32.setImageResource(R.drawable.available_img);
                            seatstatus[32]=0;
                        }







                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        setseatdetails(myseats);








        if(seatstatus[1]!=1)
        {
            seat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[1]==0)
                    {
                        seat1.setImageResource(R.drawable.your_seat_img);
                        seatstatus[1]=2;



                        myseats[1]=1;
                        setseatdetails(myseats);


//
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[1]==1)
                    {
                        seat1.setImageResource(R.drawable.available_img);
                        seatstatus[1]=0;


                        myseats[1]=0;
                        setseatdetails(myseats);


//
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[2]!=1)
        {
            seat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[2]==0)
                    {
                        seat2.setImageResource(R.drawable.your_seat_img);
                        seatstatus[2]=2;


                        myseats[2]=1;
                        setseatdetails(myseats);


//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();

//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[2]==1)
                    {
                        seat2.setImageResource(R.drawable.available_img);
                        seatstatus[2]=0;


                        myseats[2]=0;
                        setseatdetails(myseats);

//
//
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[3]!=1)
        {
            seat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[3]==0)
                    {
                        seat3.setImageResource(R.drawable.your_seat_img);
                        seatstatus[3]=2;


                        myseats[3]=1;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[3]==1)
                    {
                        seat3.setImageResource(R.drawable.available_img);
                        seatstatus[3]=0;


                        myseats[3]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[4]!=1)
        {
            seat4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[4]==0)
                    {
                        seat4.setImageResource(R.drawable.your_seat_img);
                        seatstatus[4]=2;


                        myseats[4]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[4]==1)
                    {
                        seat4.setImageResource(R.drawable.available_img);
                        seatstatus[4]=0;


                        myseats[4]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[5]!=1)
        {
            seat5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[5]==0)
                    {
                        seat5.setImageResource(R.drawable.your_seat_img);
                        seatstatus[5]=2;


                        myseats[5]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[5]==1)
                    {
                        seat5.setImageResource(R.drawable.available_img);
                        seatstatus[5]=0;


                        myseats[5]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[6]!=1)
        {
            seat6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[6]==0)
                    {
                        seat6.setImageResource(R.drawable.your_seat_img);
                        seatstatus[6]=2;


                        myseats[6]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[6]==1)
                    {
                        seat6.setImageResource(R.drawable.available_img);
                        seatstatus[6]=0;


                        myseats[6]=0;
                        setseatdetails(myseats);


//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[7]!=1)
        {
            seat7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[7]==0)
                    {
                        seat7.setImageResource(R.drawable.your_seat_img);
                        seatstatus[7]=2;


                        myseats[7]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[7]==1)
                    {
                        seat7.setImageResource(R.drawable.available_img);
                        seatstatus[7]=0;


                        myseats[7]=0;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[8]!=1)
        {
            seat8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[8]==0)
                    {
                        seat8.setImageResource(R.drawable.your_seat_img);
                        seatstatus[8]=2;


                        myseats[8]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[8]==1)
                    {
                        seat8.setImageResource(R.drawable.available_img);
                        seatstatus[8]=0;


                        myseats[8]=0;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[9]!=1)
        {
            seat9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[9]==0)
                    {
                        seat9.setImageResource(R.drawable.your_seat_img);
                        seatstatus[9]=2;


                        myseats[9]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[9]==1)
                    {
                        seat9.setImageResource(R.drawable.available_img);
                        seatstatus[9]=0;


                        myseats[9]=0;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[10]!=1)
        {
            seat10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[10]==0)
                    {
                        seat10.setImageResource(R.drawable.your_seat_img);
                        seatstatus[10]=2;


                        myseats[10]=1;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[10]==1)
                    {
                        seat10.setImageResource(R.drawable.available_img);
                        seatstatus[10]=0;


                        myseats[10]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[11]!=1)
        {
            seat11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[11]==0)
                    {
                        seat11.setImageResource(R.drawable.your_seat_img);
                        seatstatus[11]=2;


                        myseats[11]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[11]==1)
                    {
                        seat11.setImageResource(R.drawable.available_img);
                        seatstatus[11]=0;


                        myseats[11]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[12]!=1)
        {
            seat12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[12]==0)
                    {
                        seat12.setImageResource(R.drawable.your_seat_img);
                        seatstatus[12]=2;


                        myseats[12]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[12]==1)
                    {
                        seat12.setImageResource(R.drawable.available_img);
                        seatstatus[12]=0;


                        myseats[12]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[13]!=1)
        {
            seat13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[13]==0)
                    {
                        seat13.setImageResource(R.drawable.your_seat_img);
                        seatstatus[13]=2;


                        myseats[13]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[13]==1)
                    {
                        seat13.setImageResource(R.drawable.available_img);
                        seatstatus[13]=0;


                        myseats[13]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[14]!=1)
        {
            seat14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[14]==0)
                    {
                        seat14.setImageResource(R.drawable.your_seat_img);
                        seatstatus[14]=2;


                        myseats[14]=1;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[14]==1)
                    {
                        seat14.setImageResource(R.drawable.available_img);
                        seatstatus[14]=0;


                        myseats[14]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[15]!=1)
        {
            seat15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[15]==0)
                    {
                        seat15.setImageResource(R.drawable.your_seat_img);
                        seatstatus[15]=2;


                        myseats[15]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[15]==1)
                    {
                        seat15.setImageResource(R.drawable.available_img);
                        seatstatus[15]=0;


                        myseats[15]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[16]!=1)
        {
            seat16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[16]==0)
                    {
                        seat16.setImageResource(R.drawable.your_seat_img);
                        seatstatus[16]=2;


                        myseats[16]=1;
//                        setseatdetails(myseats);
//
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[16]==1)
                    {
                        seat16.setImageResource(R.drawable.available_img);
                        seatstatus[16]=0;


                        myseats[16]=0;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[17]!=1)
        {
            seat17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[17]==0)
                    {
                        seat17.setImageResource(R.drawable.your_seat_img);
                        seatstatus[17]=2;


                        myseats[17]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[17]==1)
                    {
                        seat17.setImageResource(R.drawable.available_img);
                        seatstatus[17]=0;


                        myseats[17]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[18]!=1)
        {
            seat18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[18]==0)
                    {
                        seat18.setImageResource(R.drawable.your_seat_img);
                        seatstatus[18]=2;


                        myseats[18]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[18]==1)
                    {
                        seat18.setImageResource(R.drawable.available_img);
                        seatstatus[18]=0;


                        myseats[18]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[19]!=1)
        {
            seat19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[19]==0)
                    {
                        seat19.setImageResource(R.drawable.your_seat_img);
                        seatstatus[19]=2;


                        myseats[19]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[19]==1)
                    {
                        seat19.setImageResource(R.drawable.available_img);
                        seatstatus[19]=0;


                        myseats[19]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[20]!=1)
        {
            seat20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[20]==0)
                    {
                        seat20.setImageResource(R.drawable.your_seat_img);
                        seatstatus[20]=2;


                        myseats[20]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[20]==1)
                    {
                        seat20.setImageResource(R.drawable.available_img);
                        seatstatus[20]=0;


                        myseats[20]=0;
                        setseatdetails(myseats);

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[21]!=1)
        {
            seat21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[21]==0)
                    {
                        seat21.setImageResource(R.drawable.your_seat_img);
                        seatstatus[21]=2;


                        myseats[21]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[21]==1)
                    {
                        seat21.setImageResource(R.drawable.available_img);
                        seatstatus[21]=0;


                        myseats[21]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[22]!=1)
        {
            seat22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[22]==0)
                    {
                        seat22.setImageResource(R.drawable.your_seat_img);
                        seatstatus[22]=2;


                        myseats[22]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[22]==1)
                    {
                        seat22.setImageResource(R.drawable.available_img);
                        seatstatus[22]=0;


                        myseats[22]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[23]!=1)
        {
            seat23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[23]==0)
                    {
                        seat23.setImageResource(R.drawable.your_seat_img);
                        seatstatus[23]=2;


                        myseats[23]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[23]==1)
                    {
                        seat23.setImageResource(R.drawable.available_img);
                        seatstatus[23]=0;


                        myseats[23]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[24]!=1)
        {
            seat24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[24]==0)
                    {
                        seat24.setImageResource(R.drawable.your_seat_img);
                        seatstatus[24]=2;


                        myseats[24]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[24]==1)
                    {
                        seat24.setImageResource(R.drawable.available_img);
                        seatstatus[24]=0;


                        myseats[24]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[25]!=1)
        {
            seat25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[25]==0)
                    {
                        seat25.setImageResource(R.drawable.your_seat_img);
                        seatstatus[25]=2;


                        myseats[25]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[25]==1)
                    {
                        seat25.setImageResource(R.drawable.available_img);
                        seatstatus[25]=0;


                        myseats[25]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[26]!=1)
        {
            seat26.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[26]==0)
                    {
                        seat26.setImageResource(R.drawable.your_seat_img);
                        seatstatus[26]=2;


                        myseats[26]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[26]==1)
                    {
                        seat26.setImageResource(R.drawable.available_img);
                        seatstatus[26]=0;


                        myseats[26]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[27]!=1)
        {
            seat27.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[27]==0)
                    {
                        seat27.setImageResource(R.drawable.your_seat_img);
                        seatstatus[27]=2;


                        myseats[27]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[27]==1)
                    {
                        seat27.setImageResource(R.drawable.available_img);
                        seatstatus[27]=0;


                        myseats[27]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[28]!=1)
        {
            seat28.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[28]==0)
                    {
                        seat28.setImageResource(R.drawable.your_seat_img);
                        seatstatus[28]=2;


                        myseats[28]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[28]==1)
                    {
                        seat28.setImageResource(R.drawable.available_img);
                        seatstatus[28]=0;


                        myseats[28]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[29]!=1)
        {
            seat29.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[29]==0)
                    {
                        seat29.setImageResource(R.drawable.your_seat_img);
                        seatstatus[29]=2;

                        seatstatusstr[29]="seat29";
                        myseats[29]=1;

//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[29]==1)
                    {
                        seat29.setImageResource(R.drawable.available_img);
                        seatstatus[29]=0;


                        myseats[29]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }


        if(seatstatus[30]!=1)
        {
            seat30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[30]==0)
                    {
                        seat30.setImageResource(R.drawable.your_seat_img);
                        seatstatus[30]=2;


                        myseats[30]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[30]==1)
                    {
                        seat30.setImageResource(R.drawable.available_img);
                        seatstatus[30]=0;


                        myseats[30]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[31]!=1)
        {
            seat31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[31]==0)
                    {
                        seat31.setImageResource(R.drawable.your_seat_img);
                        seatstatus[31]=2;
                        myseats[31]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[31]==1)
                    {
                        seat31.setImageResource(R.drawable.available_img);
                        seatstatus[31]=0;
                        myseats[31]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }



        if(seatstatus[32]!=1)
        {
            seat32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(seatstatus[32]==0)
                    {
                        seat32.setImageResource(R.drawable.your_seat_img);
                        seatstatus[32]=2;
                        myseats[32]=1;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                    else if(myseats[32]==1)
                    {
                        seat32.setImageResource(R.drawable.available_img);
                        seatstatus[32]=0;
                        myseats[32]=0;
                        setseatdetails(myseats);
//                        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//                        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//                        node.child(busnumberdisp).setValue(obj);
                    }
                }
            });
        }
       // dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        proceedtocnfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();
                dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String bookingdateheck=dateFormat2.format(calendar.getTime());;


                String[] datestr=preferences.getString("enterdate1","0").split("-");
                String busdatecheck=datestr[2]+"-"+datestr[1]+"-"+datestr[0]+" "+getIntent().getStringExtra("sourcetimekey").trim();
               // Toast.makeText(Main3Activity.this,busdatecheck,Toast.LENGTH_SHORT).show();
                long days=0;
                long hours=0;
                long minutes=0;
                try {
                    Date d1=dateFormat2.parse(bookingdateheck);
                    Date d2=dateFormat2.parse(busdatecheck);
                    long diff = d2.getTime() - d1.getTime();
                    long seconds = diff / 1000;
                    minutes = seconds / 60;
                    hours = minutes / 60;
                    days=hours/24;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(minutes>=15)
                {
                    int count1=0;
                    for(int i=1;i<33;i++)
                    {
                        if(myseats[i]==1)
                        {
                            count1++;
                        }
                    }
                    if(count1==0)
                    {
                        Toast.makeText(Main3Activity.this,"Please select a seat to continue!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent2=new Intent(Main3Activity.this,Cofirmmyseat.class);
                        intent2.putExtra("myseatsselected",myseats);
                        intent2.putExtra("previousseats",seatstatus);
                        intent2.putExtra("myseatprice",totalpricetxt.getText().toString().trim());
                        intent2.putExtra("busdateno",busnumberdisp);



//                busseataddfirebase obj1=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                        seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//                FirebaseDatabase busseatdb1=FirebaseDatabase.getInstance();
//
//                DatabaseReference node= busseatdb1.getReference("BusSeatDetails");
//                node.child(busnumberdisp).setValue(obj1);
                        intent2.putExtra("seatstatusstr",seatstatusstr);
                        startActivity(intent2);
                    }
                }
                else
                {
                    Toast.makeText(Main3Activity.this,"Booking for this bus has closed",Toast.LENGTH_SHORT).show();
                }







            }
        });


        if(phonenumber.equals(preferences.getString("phonenumber","")));
        {
            setseatdetails(myseats);
        }






    }

    public void setseatdetails(int[] myseats) {
        String seatsno="";
        int count=0;
        for(int i=1;i<33;i++)
        {
            if(myseats[i]==1)
            {
                count++;
            }
        }
        seatsno=String.valueOf(count);
        int totalprice=(Integer.parseInt(getIntent().getStringExtra("ticketfeekey")));
        totalprice=totalprice*count;
        totalpricetxt.setText(String.valueOf(totalprice));

        seatsno+="  ( ";
        for(int i=1;i<33;i++)
        {
            if(myseats[i]==1)
            {
                seatsno+=String.valueOf(i)+" ,";
            }
        }
        seatsno+=" )";
        seatdetailstxt.setText(seatsno);
    }

//    private void seatdataarray(String[] seatstatusstr, int[] seatstatus) {
//        for(int i=1;i<33;i++)
//        {
//            if(seatstatus[i]!=0)
//            {
//                String s="seat";
//                seatstatusstr[i]=s+String.valueOf(i);
//            }
//            else
//            {
//                seatstatusstr[i]="";
//            }
//        }
//    }
}

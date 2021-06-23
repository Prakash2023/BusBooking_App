package com.minemark.platform.mybusbookingapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class mybookingadapter extends FirebaseRecyclerAdapter<mybookingmodel,mybookingadapter.myviewholder> {
    SharedPreferences preferences;
    Calendar calendar;
    SimpleDateFormat dateFormat,dateFormat2;


    public mybookingadapter(@NonNull FirebaseRecyclerOptions<mybookingmodel> options) {
        super(options);


    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final mybookingmodel mybookingmodel) {
        holder.Bus_number.setText(mybookingmodel.getBusnumber());
        holder.route.setText(mybookingmodel.getRoute());
        holder.time.setText(mybookingmodel.getTime());
        holder.ACstatus.setText(mybookingmodel.getACstatus());
        holder.date.setText(mybookingmodel.getDate());
        holder.Ticketfee.setText(mybookingmodel.getTotalprice());
        holder.Totalseats.setText(mybookingmodel.getTotalseats());
        holder.myseats.setText(mybookingmodel.getSeatno());

        final String busdatestr=mybookingmodel.getBusnumber()+mybookingmodel.getDate();
        final String phonenumber=mybookingmodel.getPhonenumber();




        holder.cancelTicket.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();

                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                final String canceldatetime= dateFormat2.format(calendar.getTime());
                final String canceldate= dateFormat.format(calendar.getTime());
                String[] datestr=canceldate.split("-");
                String[] bookedtimestr=mybookingmodel.getTime().split("-");
                String[] str=mybookingmodel.getDate().split("-");

                final String bookeddate=str[2]+"-"+str[1]+"-"+str[0]+" "+bookedtimestr[0].trim();


                //Toast.makeText(holder.ACstatus.getContext(),canceldatetime,Toast.LENGTH_LONG).show();
                if(canceldatetime.compareTo(bookeddate)<0)
                {
                    long days=0;
                    long hours=0;
                    try {
                        Date d1=dateFormat2.parse(canceldatetime);
                        Date d2=dateFormat2.parse(bookeddate);
                        long diff = d2.getTime() - d1.getTime();
                        long seconds = diff / 1000;
                        long minutes = seconds / 60;
                        hours = minutes / 60;
                        days=hours/24;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(holder.ACstatus.getContext(),String.valueOf(hours),Toast.LENGTH_LONG).show();
                    if(hours>=1)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.ACstatus.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Cancel Ticket");
                        builder.setMessage("Are you confirm to cancel ticket");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference Phone_Number=database.getReference("userbooking");
                                        DatabaseReference busdate=Phone_Number.child(phonenumber);
                                        DatabaseReference busdatechild=busdate.child(busdatestr);

                                        final String[] route = new String[1];
                                        final String[] time = new String[1];
                                        final String[] totalprice = new String[1];
                                        final String[] totalseats = new String[1];
                                        final String[] username = new String[1];
                                        final String[] adminphonenumber = new String[1];
                                        final String[] acstatus = new String[1];
                                        final String[] seatno = new String[1];
                                        final String[] date = new String[1];
                                        final String[] name = new String[1];
                                       





                                        busdatechild.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                route[0] =(String) snapshot.child("route").getValue();
                                                time[0] =(String) snapshot.child("time").getValue();
                                                seatno[0] =(String) snapshot.child("seatno").getValue();
                                                totalprice[0] =(String) snapshot.child("totalprice").getValue();
                                                totalseats[0] =(String) snapshot.child("totalseats").getValue();
                                                username[0] =(String) snapshot.child("username").getValue();
                                                adminphonenumber[0] =(String) snapshot.child("adminphonenumber").getValue();
                                                acstatus[0]=(String) snapshot.child("acstatus").getValue();
                                                date[0]=(String) snapshot.child("date").getValue();


                                                String status="Pending";
                                                pendingcanceluploaddataholder obj=new pendingcanceluploaddataholder(username[0],phonenumber,mybookingmodel.getBusnumber(),route[0],mybookingmodel.getDate(),totalseats[0],totalprice[0],adminphonenumber[0],acstatus[0],time[0],seatno[0],status,canceldatetime);
                                                DatabaseReference pendinginvoice=database.getReference("userpendingcancels");

                                                //DatabaseReference updatependingcancels=userphonenumber.child(phonenumber+busdatestr);
                                                pendinginvoice.child(phonenumber+busdatestr).setValue(obj);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                        busdate.child(busdatestr).removeValue();



                                        Toast.makeText(holder.ACstatus.getContext(),phonenumber+" "+busdatestr,Toast.LENGTH_LONG).show();

                                        DatabaseReference busdateinfo=database.getReference("BusSeatDetails");
                                        final DatabaseReference busseatdata=busdateinfo.child(busdatestr);
                                        busseatdata.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int c1=0,c2=0;
                                                for(int i=1;i<33;i++)
                                                {
                                                    String str="seatstatusstr"+ i;
                                                    if(snapshot.child(str).exists())
                                                    {
                                                        c1++;
                                                        String s= (String) snapshot.child(str).getValue();
                                                        assert s != null;
                                                        if(s.equals(phonenumber))
                                                        {
                                                            c2++;
                                                            busseatdata.child(str).removeValue();
                                                        }
                                                    }

                                                }
                                                if(c1==c2)
                                                {
                                                    busseatdata.child("adminphonenumber").removeValue();
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
                        Toast.makeText(holder.ACstatus.getContext(),"You Cannot Cancel a Ticket before 6 hours of a journey",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(holder.ACstatus.getContext(),"You Cannot Cancel this Ticket",Toast.LENGTH_SHORT).show();
                }





            }
        });

        holder.generateinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String invoicekey=phonenumber+"@"+busdatestr;
                Intent intent=new Intent(holder.ACstatus.getContext(),createinvoice.class);
                intent.putExtra("invoicekey",invoicekey);
                holder.ACstatus.getContext().startActivity(intent);
            }
        });



//
    }

//

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowmybooking,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView Bus_number,route,time,ACstatus,date,Ticketfee,Totalseats,myseats,Clickmain;
        Button cancelTicket,generateinvoice;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Bus_number=(TextView)itemView.findViewById(R.id.Busnnotext);
            route=(TextView)itemView.findViewById(R.id.routetext);
            time=(TextView)itemView.findViewById(R.id.Timetxt);
            ACstatus=(TextView)itemView.findViewById(R.id.textView13);
            date=(TextView)itemView.findViewById(R.id.textView37);
            Ticketfee=(TextView)itemView.findViewById(R.id.textView14);
            Clickmain=(TextView)itemView.findViewById(R.id.textView15);
            Totalseats=(TextView)itemView.findViewById(R.id.textView33);
            myseats=(TextView)itemView.findViewById(R.id.textView35);
            cancelTicket=(Button)itemView.findViewById(R.id.button7);
            generateinvoice=(Button)itemView.findViewById(R.id.button11);
        }
    }
}

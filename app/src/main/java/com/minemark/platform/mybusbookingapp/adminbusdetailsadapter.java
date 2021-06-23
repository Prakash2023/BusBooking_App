package com.minemark.platform.mybusbookingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class adminbusdetailsadapter extends FirebaseRecyclerAdapter<adminbusdetailsmodel,adminbusdetailsadapter.myviewholder1> {
    SharedPreferences preferences;


    public adminbusdetailsadapter(@NonNull FirebaseRecyclerOptions<adminbusdetailsmodel> options) {
        super(options);


    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder1 holder1, final int position, @NonNull final adminbusdetailsmodel adminbusdetailsmodel) {
        holder1.Bus_number.setText(adminbusdetailsmodel.getBus_number());
        holder1.Start_Point.setText(adminbusdetailsmodel.getStart_Point());
        holder1.Stop_Point.setText(adminbusdetailsmodel.getStop_Point());
        holder1.Start_Time.setText(adminbusdetailsmodel.getStart_Time());
        holder1.Stop_Time.setText(adminbusdetailsmodel.getStop_Time());
        holder1.ACStatus.setText(adminbusdetailsmodel.getACstatus());
        holder1.Ticketfee.setText(adminbusdetailsmodel.getTicketfee());



        holder1.Clickmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder1.Bus_number.getContext());
                builder.setCancelable(true);
                builder.setTitle("Booking and Invoice");
                builder.setMessage("Enter the Date dd-mm-yyyy");
                final EditText input = new EditText(holder1.Bus_number.getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(input.getText().toString().trim().equals("")||input.getText().toString().trim().length()>10||input.getText().toString().trim().length()<10)
                                {
                                    Toast.makeText(holder1.Bus_number.getContext(),"Enter a Valid date dd-mm-yyyy",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else
                                {
                                    DatabaseReference database=FirebaseDatabase.getInstance().getReference();
                                    final DatabaseReference phone_number=database.child("BusSeatDetails");
                                    phone_number.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.child(adminbusdetailsmodel.getBus_number()+input.getText().toString().trim()).exists())
                                            {
                                                Intent intent=new Intent(holder1.Bus_number.getContext(),adminbusseatdata.class);
                                                intent.putExtra("checkbusdate",adminbusdetailsmodel.getBus_number()+input.getText().toString().trim());
                                                intent.putExtra("sourcekey",adminbusdetailsmodel.getStart_Point());
                                                intent.putExtra("destkey",adminbusdetailsmodel.getStop_Point());
                                                intent.putExtra("sourcetimekey",adminbusdetailsmodel.getStart_Time());
                                                intent.putExtra("desttimekey",adminbusdetailsmodel.getStop_Time());
                                                intent.putExtra("busnumberkey",adminbusdetailsmodel.getBus_number());
                                                intent.putExtra("ticketfeekey",adminbusdetailsmodel.getTicketfee());
                                                intent.putExtra("acstatuskey",adminbusdetailsmodel.getACstatus());

                                                holder1.Bus_number.getContext().startActivity(intent);
                                            }
                                            else
                                            {
                                                Toast.makeText(holder1.Bus_number.getContext()," No Booking Found For "+input.getText().toString().trim(),Toast.LENGTH_SHORT).show();
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
        });






//
    }

//

    @NonNull
    @Override
    public myviewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowadminbusdisplay,parent,false);
        return new myviewholder1(view);
    }

    static class myviewholder1 extends RecyclerView.ViewHolder
    {
        TextView Bus_number,Start_Point,Stop_Point,Start_Time,Stop_Time,ACStatus,Ticketfee;
        Button Clickmain;
        public myviewholder1(@NonNull View itemView) {
            super(itemView);
            Bus_number=(TextView)itemView.findViewById(R.id.Busnnotext);
            Start_Point=(TextView)itemView.findViewById(R.id.routetext);
            Stop_Point=(TextView)itemView.findViewById(R.id.Timetxt);
            Start_Time=(TextView)itemView.findViewById(R.id.textView11);
            Stop_Time=(TextView)itemView.findViewById(R.id.textView12);
            ACStatus=(TextView)itemView.findViewById(R.id.textView13);
            Ticketfee=(TextView)itemView.findViewById(R.id.textView14);
            Clickmain=(Button) itemView.findViewById(R.id.button13);
        }
    }
}

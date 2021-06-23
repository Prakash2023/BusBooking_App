package com.minemark.platform.mybusbookingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {



    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);


    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model) {



            holder.Bus_number.setText(model.getBus_number());
            holder.Start_Point.setText(model.getStart_Point());
            holder.Stop_Point.setText(model.getStop_Point());
            holder.Start_Time.setText(model.getStart_Time());
            holder.Stop_Time.setText(model.getStop_Time());
            holder.ACStatus.setText(model.getACstatus());
            holder.Ticketfee.setText(model.getTicketfee());






        holder.Clickmain.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {

//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Stop_Time.getContext());
//                builder.setCancelable(true);
//                builder.setTitle("Book Ticket");
//                builder.setMessage("Are you confirm to select this bus!\n\nPlease recheck Starting Time of Bus!\n\n" +
//                        "No Refund will be for a bus booked before 1 hour of journey.");
//                builder.setPositiveButton("Confirm",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {


                                Intent intent1=new Intent(holder.Bus_number.getContext(),Main3Activity.class);
                                intent1.putExtra("sourcekey",model.getStart_Point());
                                intent1.putExtra("destkey",model.getStop_Point());
                                intent1.putExtra("sourcetimekey",model.getStart_Time());
                                intent1.putExtra("desttimekey",model.getStop_Time());
                                intent1.putExtra("busnumberkey",model.getBus_number());
                                intent1.putExtra("ticketfeekey",model.getTicketfee());
                                intent1.putExtra("acstatuskey",model.getACstatus());
                                intent1.putExtra("adminnumber",model.getAdminphonenumber());

                               holder.Bus_number.getContext().startActivity(intent1);
//                            }
//                        });
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView Bus_number,Start_Point,Stop_Point,Start_Time,Stop_Time,ACStatus,Ticketfee;
        Button Clickmain;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            Bus_number=(TextView)itemView.findViewById(R.id.Busnnotext);
            Start_Point=(TextView)itemView.findViewById(R.id.routetext);
            Stop_Point=(TextView)itemView.findViewById(R.id.Timetxt);
            Start_Time=(TextView)itemView.findViewById(R.id.textView11);
            Stop_Time=(TextView)itemView.findViewById(R.id.textView12);
            ACStatus=(TextView)itemView.findViewById(R.id.textView13);
            Ticketfee=(TextView)itemView.findViewById(R.id.textView14);
            Clickmain=(Button) itemView.findViewById(R.id.button16);
        }
    }
}

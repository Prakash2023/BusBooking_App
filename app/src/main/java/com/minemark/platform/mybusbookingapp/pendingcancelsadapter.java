package com.minemark.platform.mybusbookingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class pendingcancelsadapter extends FirebaseRecyclerAdapter<userpendingcancelmodel,pendingcancelsadapter .myviewholder2> {

int c=0;

    public pendingcancelsadapter (@NonNull FirebaseRecyclerOptions<userpendingcancelmodel> options) {
        super(options);


    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder2 holder3, final int position, @NonNull final userpendingcancelmodel userpendingcancelmodel) {
        holder3.Bus_number.setText(userpendingcancelmodel.getBusnumber());
        holder3.route.setText(userpendingcancelmodel.getRoute());
        holder3.time.setText(userpendingcancelmodel.getTime());
        holder3.ACstatus.setText(userpendingcancelmodel.getAcstatus());
        holder3.date.setText(userpendingcancelmodel.getDate());
        holder3.Ticketfee.setText(userpendingcancelmodel.getTotalprice());
        holder3.Totalseats.setText(userpendingcancelmodel.getTotalseats());
        holder3.myseats.setText(userpendingcancelmodel.getSeatno());
        holder3.adminphonenumber.setText(userpendingcancelmodel.getAdminphonenumber());
        holder3.canceltime.setText(userpendingcancelmodel.getCanceltime());
        if(userpendingcancelmodel.getStatus().equals("Pending"))
        {
            holder3.statusbtn.setText("Pending");
            holder3.statusbtn.setBackgroundResource(R.drawable.buttonshape);
            c++;
        }
        else
        {
            holder3.statusbtn.setText("Refunded");
            holder3.statusbtn.setBackgroundResource(R.drawable.pendingbutton);
        }


        final String busdatestr=userpendingcancelmodel.getBusnumber()+userpendingcancelmodel.getDate();
        final String phonenumber=userpendingcancelmodel.getPhonenumber();

//        holder3.generateinvoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(holder3.ACstatus.getContext(),createinvoice.class);
//                intent.putExtra("checkintent","pendingcancel");
//                intent.putExtra("busnumber",userpendingcancelmodel.getBusnumber());
//                intent.putExtra("route",userpendingcancelmodel.getRoute());
//                intent.putExtra("time",userpendingcancelmodel.getTime());
//                intent.putExtra("ACstatus",userpendingcancelmodel.getAcstatus());
//                intent.putExtra("date",userpendingcancelmodel.getDate());
//                intent.putExtra("ticketfee",userpendingcancelmodel.getTotalprice());
//                intent.putExtra("totalseats",userpendingcancelmodel.getTotalseats());
//                intent.putExtra("myseats",userpendingcancelmodel.getSeatno());
//                intent.putExtra("busdatestr",busdatestr);
//                intent.putExtra("phonenumber",phonenumber);
//                holder3.ACstatus.getContext().startActivity(intent);
//            }
//        });





//
    }

//

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sinlerowuserpendincancels,parent,false);
        return new myviewholder2(view);
    }

    static class myviewholder2 extends RecyclerView.ViewHolder
    {
        TextView Bus_number,route,time,ACstatus,date,Ticketfee,Totalseats,myseats,adminphonenumber,canceltime;
        Button statusbtn;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            Bus_number=(TextView)itemView.findViewById(R.id.Busnnotext);
            route=(TextView)itemView.findViewById(R.id.routetext);
            time=(TextView)itemView.findViewById(R.id.Timetxt);
            ACstatus=(TextView)itemView.findViewById(R.id.textView13);
            date=(TextView)itemView.findViewById(R.id.textView37);
            Ticketfee=(TextView)itemView.findViewById(R.id.textView14);
            Totalseats=(TextView)itemView.findViewById(R.id.textView33);
            myseats=(TextView)itemView.findViewById(R.id.textView35);
            adminphonenumber=(TextView)itemView.findViewById(R.id.textView53);
            canceltime=(TextView)itemView.findViewById(R.id.textView52);
            statusbtn=(Button)itemView.findViewById(R.id.button24);
            //generateinvoice=(Button)itemView.findViewById(R.id.button11);


        }
    }
}

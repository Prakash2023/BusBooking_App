package com.minemark.platform.mybusbookingapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class adminpendingcancelsadapter  extends FirebaseRecyclerAdapter<adminpendingcancelmodel,adminpendingcancelsadapter  .myviewholder3> {
int count=0;


    public adminpendingcancelsadapter  (@NonNull FirebaseRecyclerOptions<adminpendingcancelmodel> options) {
        super(options);


    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder3 holder4, final int position, @NonNull final adminpendingcancelmodel adminpendingcancelmodel) {

        if(adminpendingcancelmodel.getStatus().equals("Pending"))
        {
            count++;
            holder4.Bus_number.setText(adminpendingcancelmodel.getBusnumber());
            holder4.route.setText(adminpendingcancelmodel.getRoute());
            holder4.time.setText(adminpendingcancelmodel.getTime());
            holder4.ACstatus.setText(adminpendingcancelmodel.getAcstatus());
            holder4.date.setText(adminpendingcancelmodel.getDate());
            holder4.Ticketfee.setText(adminpendingcancelmodel.getTotalprice());
            holder4.Totalseats.setText(adminpendingcancelmodel.getTotalseats());
            holder4.myseats.setText(adminpendingcancelmodel.getSeatno());
            holder4.userphonenumber.setText(adminpendingcancelmodel.getPhonenumber());
            holder4.canceltime.setText(adminpendingcancelmodel.getCanceltime());
        }
        else
        {
           holder4.rootView.setLayoutParams(holder4.params);
        }
      







        final String busdatestr=adminpendingcancelmodel.getBusnumber()+adminpendingcancelmodel.getDate();
        final String phonenumber=adminpendingcancelmodel.getPhonenumber();

        holder4.refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder4.ACstatus.getContext(),adminrefundpay.class);
                intent.putExtra("checkintent","pendingcancel");
                intent.putExtra("busnumber",adminpendingcancelmodel.getBusnumber());
                intent.putExtra("route",adminpendingcancelmodel.getRoute());
                intent.putExtra("time",adminpendingcancelmodel.getTime());
                intent.putExtra("ACstatus",adminpendingcancelmodel.getAcstatus());
                intent.putExtra("date",adminpendingcancelmodel.getDate());
                intent.putExtra("ticketfee",adminpendingcancelmodel.getTotalprice());
                intent.putExtra("totalseats",adminpendingcancelmodel.getTotalseats());
                intent.putExtra("myseats",adminpendingcancelmodel.getSeatno());
                intent.putExtra("busdatestr",busdatestr);
                intent.putExtra("phonenumber",phonenumber);
                holder4.ACstatus.getContext().startActivity(intent);
            }
        });





//
    }

//

    @NonNull
    @Override
    public myviewholder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowadminpendingcancels,parent,false);
//

        return new myviewholder3(view);
    }

    static class myviewholder3 extends RecyclerView.ViewHolder
    {
        TextView Bus_number,route,time,ACstatus,date,Ticketfee,Totalseats,myseats,userphonenumber,canceltime,
                Bus_numbertxt,routetxt,timetxt,ACstatustxt,datetxt,Ticketfeetxt,Totalseatstxt,myseatstxt,userphonenumbertxt,canceltimetxt;
        Button refund;
        public CardView.LayoutParams params;
        public CardView rootView; //the outermost view from your layout. Note that it doesn't necessarily have to be a LinearLayout.
//
        public myviewholder3(@NonNull View itemView) {
            super(itemView);
            params = new CardView.LayoutParams(0, 0);
            rootView=(CardView)itemView.findViewById(R.id.adminpendingcardview);
            Bus_number=(TextView)itemView.findViewById(R.id.Busnnotext);
            route=(TextView)itemView.findViewById(R.id.routetext);
            time=(TextView)itemView.findViewById(R.id.Timetxt);
            ACstatus=(TextView)itemView.findViewById(R.id.textView13);
            date=(TextView)itemView.findViewById(R.id.textView37);
            Ticketfee=(TextView)itemView.findViewById(R.id.textView14);
            Totalseats=(TextView)itemView.findViewById(R.id.textView33);
            myseats=(TextView)itemView.findViewById(R.id.textView35);
            userphonenumber=(TextView)itemView.findViewById(R.id.textView53);
            canceltime=(TextView)itemView.findViewById(R.id.textView52);
            refund=(Button)itemView.findViewById(R.id.button15);


            Bus_numbertxt=(TextView)itemView.findViewById(R.id.textView18);
            routetxt=(TextView)itemView.findViewById(R.id.textView20);
            timetxt=(TextView)itemView.findViewById(R.id.textView25);
            ACstatustxt=(TextView)itemView.findViewById(R.id.textView29);
            datetxt=(TextView)itemView.findViewById(R.id.textView36);
            Ticketfeetxt=(TextView)itemView.findViewById(R.id.textView31);
            Totalseatstxt=(TextView)itemView.findViewById(R.id.textView32);
            myseatstxt=(TextView)itemView.findViewById(R.id.textView34);
            userphonenumbertxt=(TextView)itemView.findViewById(R.id.textView50);
            canceltimetxt=(TextView)itemView.findViewById(R.id.textView51);


        }
    }
}

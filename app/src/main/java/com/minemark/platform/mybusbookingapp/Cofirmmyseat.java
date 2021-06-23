package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Cofirmmyseat extends AppCompatActivity {
TextView mytotalseats;
TextView myseatsno;
TextView myseatprice;
Button editmyseat;
Button proceedtopay;
TextView routetxt;
TextView datetxt;
TextView actxt;
TextView timetxt;
Calendar calendar;
SimpleDateFormat dateFormat,dateFormat2;
SharedPreferences preferences;
int chekpayment=0;
int[] seatstatus;
    String[] seatstatusstr;
int[] myseats;
String busdateno,phonenumber,adminphonenumber,busnumber,date,route,time,acstatus,username,bookingdate;
   String adminemail ;
String TAG ="main";
final int UPI_PAYMENT = 0;
    final int[] count = {0};
    final String[] seatsno = {""};
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofirmmyseat);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        mytotalseats=findViewById(R.id.textView17);
        myseatsno=findViewById(R.id.textView21);
        myseatprice=findViewById(R.id.textView24);
        editmyseat=findViewById(R.id.button);
        proceedtopay=findViewById(R.id.button2);
        routetxt=findViewById(R.id.textView39);
        datetxt=findViewById(R.id.textView41);
        actxt=findViewById(R.id.textView43);
        timetxt=findViewById(R.id.textView45);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        final String username=preferences.getString("username","");

        Bundle bundle=getIntent().getExtras();
        myseats=bundle.getIntArray("myseatsselected");
        seatstatus=bundle.getIntArray("previousseats");
        //final String[] seatstatusstr=bundle.getStringArray("seatstatusstr");
         seatstatusstr=new String[33];

        calendar=Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");




         final String bookingdateheck= dateFormat2.format(calendar.getTime());
         bookingdate = dateFormat.format(calendar.getTime());
        //Toast.makeText(getApplicationContext(),bookingdate,Toast.LENGTH_LONG).show();



        //mytotalseats.setText(getIntent().getStringExtra("mytotalseats"));










      adminphonenumber=preferences.getString("adminphonenumber","adminphone");
         phonenumber=preferences.getString("phonenumber","000");
         busdateno=getIntent().getStringExtra("busdateno");
        busnumber=preferences.getString("busnumber","");
        date=preferences.getString("enterdate1","");
        String src=preferences.getString("source","");
        String dest=preferences.getString("destination","");
        src=src+" - "+dest;
        route=src;
        String t1=preferences.getString("sourcetime","");
        String t2=preferences.getString("desttime","");
         time=t1+"-"+t2;
         acstatus=preferences.getString("acstatus","ghg");


        datetxt.setText(date);
        routetxt.setText(src);
        actxt.setText(acstatus);
        timetxt.setText(time);




//        for(int i=1;i<33;i++)
//        {
//            if(seatstatus[i]==1)
//            {
//                seatstatusstr[i]=(String)phonenumber;
//            }
//        }




        for(int i=1;i<33;i++)
        {
            if(myseats[i]==1)
            {
                count[0]++;
            }
        }
        seatsno[0] ="";



        int c=0;
        for(int i=1;i<33;i++)
        {
            if(c==0)
            {
                if(myseats[i]==1)
                {
                    c++;
                    seatsno[0] +=String.valueOf(i);
                }
            }
            else
            {
                if(myseats[i]==1)
                {
                    seatsno[0] +=","+String.valueOf(i);
                }
            }

        }

        final int totalprice=(Integer.parseInt(getIntent().getStringExtra("myseatprice")));
        // totalprice=totalprice*count;
        myseatprice.setText(String.valueOf(totalprice));
        myseatsno.setText(seatsno[0]);
        mytotalseats.setText(String.valueOf(count[0]));


        editmyseat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        busseataddfirebase obj=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32]);
//        FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//
//        DatabaseReference node= busseatdb.getReference("BusSeatDetails");
//        node.child(busdateno).setValue(obj);












        for(int i=0;i<33;i++)
        {
            if(myseats[i]==2)
            {
                myseats[i]=0;
            }
        }


        //final String busnumberdisp=busnumber+date;

         DatabaseReference root = FirebaseDatabase.getInstance().getReference();
         DatabaseReference BusSeatDetails = root.child("BusSeatDetails");
                DatabaseReference users = BusSeatDetails.child(busdateno);
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(int i=1;i<33;i++)
                        {
                            String str="seatstatusstr"+i;
                            if(snapshot.child(str).exists())
                            {
                                if(seatstatus[i]!=0)
                                {
                                    seatstatusstr[i]=(String) snapshot.child(str).getValue();
                                }
                                if(phonenumber.equals(seatstatusstr[i]))
                                {
                                    myseats[i]=2;
                                }

                            }
                        }

                        String adminnumber=preferences.getString("adminphonenumber","");

                        final String[] email = {""};

                        DatabaseReference admindetails=FirebaseDatabase.getInstance().getReference().child("adminphone").child(adminnumber);

                        admindetails.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    adminemail =(String)snapshot.child("email").getValue();

                                }

                                String[] stremail=adminemail.split("@");
                                Toast.makeText(Cofirmmyseat.this,stremail[0],Toast.LENGTH_LONG).show();
                                final String[] adminname = new String[1];
                                final String[] adminupiid = new String[1];
                                final String[] admincompany = new String[1];
                                DatabaseReference adminbank=FirebaseDatabase.getInstance().getReference().child("admindetails");
                                DatabaseReference admininfo=adminbank.child(stremail[0]);
                                admininfo.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.child("phonenumber").exists())
                                        {
                                            adminname[0] =(String)snapshot.child("admupiname").getValue();

                                            adminupiid[0] =(String)snapshot.child("adminupiid").getValue();
                                            admincompany[0] =(String) snapshot.child("adminupicompanyname").getValue();

                                            proceedtopay.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    if(count[0]==0)
                                                    {
                                                        Toast.makeText(Cofirmmyseat.this,"Please select a seat to continue!",Toast.LENGTH_SHORT).show();

                                                    }
                                                    else
                                                    {
                                                        String[] datestr=date.split("-");
                                                        String busdatecheck=datestr[2]+"-"+datestr[1]+"-"+datestr[0]+" "+preferences.getString("sourcetime","").trim();
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
                                                        if(minutes>=5)
                                                        {
                                                            final AlertDialog.Builder builder = new AlertDialog.Builder(Cofirmmyseat.this);

                                                            builder.setCancelable(true);
                                                            builder.setTitle("Seat Booking");
                                                            builder.setMessage("Are you confirm to book seat!\n\nNote:-Cancels before 1.00 hour of journey will not be refunded");

                                                            builder.setPositiveButton("Confirm",
                                                                    new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                              payUsingUpi(admincompany[0], adminupiid[0]," Bus Booking Payment", String.valueOf(totalprice));



//                                                                                for(int i=1;i<33;i++)
//                                                                                {
//                                                                                    if(myseats[i]==1||myseats[i]==2)
//                                                                                    {
//                                                                                        seatstatusstr[i]=(String)phonenumber;
//                                                                                    }
//                                                                                }
//
//
//
//                                                                                busseataddfirebase obj1=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
//                                                                                        seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32],adminphonenumber);
//                                                                                FirebaseDatabase busseatdb1=FirebaseDatabase.getInstance();
//
//                                                                                DatabaseReference node= busseatdb1.getReference("BusSeatDetails");
//                                                                                node.child(busdateno).setValue(obj1);
//
//
//                                                                                int mytotalseats=0;
//                                                                                String myseatsno="";
//
//                                                                                for(int i=1;i<33;i++)
//                                                                                {
//                                                                                    if(myseats[i]==1||myseats[i]==2)
//                                                                                    {
//                                                                                        mytotalseats++;
//                                                                                    }
//                                                                                }
//                                                                                myseatsno ="";
//
//
//                                                                                int c=0;
//                                                                                for(int i=1;i<33;i++)
//                                                                                {
//                                                                                    if(c==0)
//                                                                                    {
//                                                                                        if(myseats[i]==1||myseats[i]==2)
//                                                                                        {
//                                                                                            c++;
//                                                                                            myseatsno +=String.valueOf(i);
//                                                                                        }
//                                                                                    }
//                                                                                    else
//                                                                                    {
//                                                                                        if(myseats[i]==1||myseats[i]==2)
//                                                                                        {
//                                                                                            c++;
//                                                                                            myseatsno +=","+String.valueOf(i);
//                                                                                        }
//                                                                                    }
//                                                                                }
//                                                                                int  myseatprice1=mytotalseats*((Integer.parseInt(getIntent().getStringExtra("myseatprice")))/count[0]);
//
//
//
//                                                                                myseatproceed obj=new myseatproceed(phonenumber,busnumber,date,route,time,String.valueOf(mytotalseats),myseatsno,String.valueOf(myseatprice1),acstatus,username,bookingdate,adminphonenumber);
//                                                                                FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
//                                                                                DatabaseReference userphone= busseatdb.getReference("userbooking");
//                                                                                DatabaseReference busdate=userphone.child(phonenumber);
//                                                                                busdate.child(busdateno).setValue(obj);
//
//
//
//                                                                                Intent intent1=new Intent(Cofirmmyseat.this,createinvoice.class);
//                                                                                intent1.putExtra("invoicekey",phonenumber+"@"+busdateno);
//                                                                                startActivity(intent1);








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
                                                            Toast.makeText(getApplicationContext(),"Bus has left for destination!",Toast.LENGTH_LONG).show();
                                                        }
                                                    }





                                                }
                                            });



                                        }






                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




    }
    @SuppressLint("QueryPermissionsNeeded")
    void payUsingUpi(String name, String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(Cofirmmyseat.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(Cofirmmyseat.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(Cofirmmyseat.this, "Transaction successful.", Toast.LENGTH_SHORT).show();



                for(int i=1;i<33;i++)
                {
                    if(myseats[i]==1||myseats[i]==2)
                    {
                        seatstatusstr[i]=(String)phonenumber;
                    }
                }



                busseataddfirebase obj1=new busseataddfirebase(seatstatusstr[1],seatstatusstr[2],seatstatusstr[3],seatstatusstr[4],seatstatusstr[5],seatstatusstr[6],seatstatusstr[7],seatstatusstr[8],seatstatusstr[9],seatstatusstr[10],seatstatusstr[11],seatstatusstr[12],seatstatusstr[13],seatstatusstr[14],seatstatusstr[15],seatstatusstr[16],seatstatusstr[17],
                        seatstatusstr[18],seatstatusstr[19],seatstatusstr[20],seatstatusstr[21],seatstatusstr[22],seatstatusstr[23],seatstatusstr[24],seatstatusstr[25],seatstatusstr[26],seatstatusstr[27],seatstatusstr[28],seatstatusstr[29],seatstatusstr[30],seatstatusstr[31],seatstatusstr[32],adminphonenumber);
                FirebaseDatabase busseatdb1=FirebaseDatabase.getInstance();

                DatabaseReference node= busseatdb1.getReference("BusSeatDetails");
                node.child(busdateno).setValue(obj1);


                int mytotalseats=0;
                String myseatsno="";

                for(int i=1;i<33;i++)
                {
                    if(myseats[i]==1||myseats[i]==2)
                    {
                        mytotalseats++;
                    }
                }
                myseatsno ="";


                int c=0;
                for(int i=1;i<33;i++)
                {
                    if(c==0)
                    {
                        if(myseats[i]==1||myseats[i]==2)
                        {
                            c++;
                            myseatsno +=String.valueOf(i);
                        }
                    }
                    else
                    {
                        if(myseats[i]==1||myseats[i]==2)
                        {
                            c++;
                            myseatsno +=","+String.valueOf(i);
                        }
                    }
                }
                int  myseatprice1=mytotalseats*((Integer.parseInt(getIntent().getStringExtra("myseatprice")))/count[0]);



                myseatproceed obj=new myseatproceed(phonenumber,busnumber,date,route,time,String.valueOf(mytotalseats),myseatsno,String.valueOf(myseatprice1),acstatus,username,bookingdate,adminphonenumber);
                FirebaseDatabase busseatdb=FirebaseDatabase.getInstance();
                DatabaseReference userphone= busseatdb.getReference("userbooking");
                DatabaseReference busdate=userphone.child(phonenumber);
                busdate.child(busdateno).setValue(obj);



                Intent intent1=new Intent(Cofirmmyseat.this,createinvoice.class);
                intent1.putExtra("invoicekey",phonenumber+"@"+busdateno);
                startActivity(intent1);









                Log.e("UPI", "payment successfull: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(Cofirmmyseat.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(Cofirmmyseat.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(Cofirmmyseat.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
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
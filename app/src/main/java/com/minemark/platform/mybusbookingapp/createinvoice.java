package com.minemark.platform.mybusbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class createinvoice extends AppCompatActivity {
EditText invoicenumber;
Button generateinvoice;
Bitmap bmp,scaledbmp;
int pagewidth=1200;
SharedPreferences preferences;
    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createinvoice);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        invoicenumber=findViewById(R.id.invoicenoedttxt);
        generateinvoice=findViewById(R.id.button8);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        invoicenumber.setText(getIntent().getStringExtra("invoicekey"));




        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.businvoicehead);
        scaledbmp=Bitmap.createScaledBitmap(bmp,1200,400,false);
        ActivityCompat.requestPermissions(createinvoice.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(createinvoice.this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        generateinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] acstatus = new String[1];
                final String[] date = new String[1];
                final String[] route = new String[1];
                final String[] seatno = new String[1];
                final String[] time = new String[1];
                final String[] totalseats = new String[1];
                final String[] totalprice = new String[1];
                final String[] bookingdate = new String[1];
                final String[] username = new String[1];
                final String[] phonenumber = new String[1];
                final String[] busnumber=new String[1];
                final String[] adminnumber=new String[1];

                final String str=invoicenumber.getText().toString().trim();
                final String[] invoicenumberstr=str.split("@");
               // int len=invoicenumberstr[1].length();



                DatabaseReference database=FirebaseDatabase.getInstance().getReference();
                final DatabaseReference phone_number=database.child("userbooking");
                DatabaseReference busdate=phone_number.child(invoicenumberstr[0]);
                DatabaseReference data=busdate.child(invoicenumberstr[1]);
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child("seatno").exists())
                        {
                            acstatus[0] =(String)snapshot.child("acstatus").getValue();
                            busnumber[0]=(String)snapshot.child("busnumber").getValue();
                            date[0] =(String)snapshot.child("date").getValue();
                            route[0] =(String)snapshot.child("route").getValue();
                            seatno[0] =(String)snapshot.child("seatno").getValue();
                            time[0] =(String)snapshot.child("time").getValue();
                            totalseats[0] =(String)snapshot.child("totalseats").getValue();
                            totalprice[0]=(String)snapshot.child("totalprice").getValue();
                            bookingdate[0] =(String) snapshot.child("bookingdate").getValue();
                            username[0] =(String) snapshot.child("username").getValue();
                            phonenumber[0] =(String) snapshot.child("phonenumber").getValue();
                            adminnumber[0]=(String) snapshot.child("adminphonenumber").getValue();




                            PdfDocument myPdfDocument=new PdfDocument();
                            Paint mypaint=new Paint();
                            Paint titlePaint=new Paint();

                            PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(1200,2010,1).create();
                            PdfDocument.Page myPage1=myPdfDocument.startPage(myPageInfo);
                            Canvas canvas=myPage1.getCanvas();
                            canvas.drawBitmap(scaledbmp,0,0,mypaint);

                            titlePaint.setTextAlign(Paint.Align.CENTER);
                            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                            titlePaint.setTextSize(70);
                            canvas.drawText(" Online Bus Booking",600,450,titlePaint);

                            mypaint.setColor(Color.rgb(0,113,188));
                            mypaint.setTextSize(30f);
                            mypaint.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText("For query call Bus owner: "+adminnumber[0],1160,1800,mypaint);

                            titlePaint.setTextAlign(Paint.Align.CENTER);
                            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
                            titlePaint.setTextSize(70);
                            canvas.drawText("Invoice",600,500,titlePaint);

                            mypaint.setTextAlign(Paint.Align.LEFT);
                            mypaint.setTextSize(20);
                            mypaint.setColor(Color.BLACK);
                            canvas.drawText("Customer Name: "+username[0],20,600,mypaint);
                            canvas.drawText("Contact No.: "+phonenumber[0],20,650,mypaint);

                            mypaint.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText("Invoice No.: "+str,pagewidth-40,600,mypaint);
                            canvas.drawText("Booking Date.: "+bookingdate[0],pagewidth-40,650,mypaint);


                            mypaint.setStyle(Paint.Style.STROKE);
                            mypaint.setStrokeWidth(2);
                            canvas.drawRect(20,780,pagewidth-20,860,mypaint);

                            mypaint.setTextAlign(Paint.Align.LEFT);
                            mypaint.setTextSize(18);
                            mypaint.setStyle(Paint.Style.FILL);
                            mypaint.setColor(Color.BLACK);

                            canvas.drawText("Bus No.",30,830,mypaint);
                            canvas.drawText("Route",280,830,mypaint);
                            canvas.drawText("Time",420,830,mypaint);
                            canvas.drawText("Date",600,830,mypaint);
                            canvas.drawText("Total Seats",680,830,mypaint);
                            canvas.drawText("seat No.",850,830,mypaint);
                            canvas.drawText("A.C",1010,830,mypaint);
                            canvas.drawText("Total Price",1070,830,mypaint);

                            canvas.drawLine(125,790,125,840,mypaint);
                            canvas.drawLine(375,790,375,840,mypaint);
                            canvas.drawLine(545,790,545,840,mypaint);
                            canvas.drawLine(675,790,675,840,mypaint);
                            canvas.drawLine(795,790,795,840,mypaint);
                            canvas.drawLine(1000,790,1000,840,mypaint);
                            canvas.drawLine(1065,790,1065,840,mypaint);

                            mypaint.setTextAlign(Paint.Align.LEFT);
                            mypaint.setTextSize(18);
                            mypaint.setStyle(Paint.Style.FILL);
                            mypaint.setColor(Color.BLACK);

                            canvas.drawText(busnumber[0],30,890,mypaint);
                            canvas.drawText(route[0],130,890,mypaint);
                            canvas.drawText(time[0],380,890,mypaint);
                            canvas.drawText(date[0],550,890,mypaint);
                            canvas.drawText(totalseats[0],680,890,mypaint);
                            canvas.drawText(seatno[0],800,890,mypaint);
                            canvas.drawText(acstatus[0],1010,890,mypaint);
                            canvas.drawText(totalprice[0],1070,890,mypaint);


                            //Toast.makeText(createinvoice.this,"Invoice Pdf generated in Phone Storage",Toast.LENGTH_SHORT).show();
                            myPdfDocument.finishPage(myPage1);
                            String path = Environment.getExternalStorageDirectory() + "/PDF";

                            File dir = new File(path);
                            if(!dir.exists())
                                dir.mkdirs();

                            Log.d("PDFCreator", "PDF Path: " + path);

                            File file = new File(dir, str+".pdf");


                           // File file= new File(Environment.getExternalStorageDirectory(),str+".pdf");
                            try {
                                myPdfDocument.writeTo(new FileOutputStream(file));
                                viewPdf(str+".pdf","PDF");

                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            myPdfDocument.close();




                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong Invoice number",Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });

//

            }
        });


    }
    private void viewPdf(String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + directory + "/" + file);
        Uri path = FileProvider.getUriForFile(createinvoice.this, BuildConfig.APPLICATION_ID + ".provider",pdfFile);


        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        if (pdfFile.exists()) {

            pdfIntent.setDataAndType(path,"application/pdf");
           pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pdfIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Intent.createChooser(pdfIntent, "Open File Using..."));

        }
    }
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),Adminuserlogin.class));
        finish();

    }

}
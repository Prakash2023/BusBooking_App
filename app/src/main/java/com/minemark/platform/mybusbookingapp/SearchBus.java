package com.minemark.platform.mybusbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SearchBus extends AppCompatActivity {
    EditText entersourcedit;
    EditText enterdestedit;
    EditText enterdateedit;
    Button searchbusbtn;
    SharedPreferences preferences;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        entersourcedit=findViewById(R.id.entersourceedit);
        enterdestedit=findViewById(R.id.enterdestedit);
        enterdateedit=findViewById(R.id.enterdateedit);
        searchbusbtn=findViewById(R.id.searchbusbutton);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);
        calendar= Calendar.getInstance();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");





        searchbusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!entersourcedit.getText().toString().trim().toLowerCase().equals("")&&!enterdestedit.getText().toString().trim().toLowerCase().equals("")&&!enterdateedit.getText().toString().trim().equals(""))
                {
                    final String bookingdate = dateFormat.format(calendar.getTime());
                    final String[] userdate = {enterdateedit.getText().toString().trim()};
                    final String[] str= userdate[0].split("-");

                    if(str[0].length()==2&&str[1].length()==2&&str[2].length()==4)
                    {
                        userdate[0] =str[2]+"-"+str[1]+"-"+str[0];
                        if(bookingdate.compareTo(userdate[0])<=0)
                        {
                            Intent intent=new Intent(SearchBus.this,Bus_Display.class);
                            intent.putExtra("entersource",entersourcedit.getText().toString().trim().toLowerCase());
                            intent.putExtra("enterdest",enterdestedit.getText().toString().trim().toLowerCase());

                            intent.putExtra("enterdate",enterdateedit.getText().toString().trim());
                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("bus_name",entersourcedit.getText().toString().trim().toLowerCase()+enterdestedit.getText().toString().trim().toLowerCase());
                            editor.apply();
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Please enter date >= Today's date",Toast.LENGTH_SHORT).show();;
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please enter a valid date e.g 01-04-2021",Toast.LENGTH_SHORT).show();;
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();;
                }




            }
        });


    }
}
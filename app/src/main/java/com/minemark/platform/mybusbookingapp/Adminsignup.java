package com.minemark.platform.mybusbookingapp;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Adminsignup extends AppCompatActivity {
    TextInputEditText  textInputEditTextFullname,textInputEditTextUsername,textInputEditTextPassword,textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();


        textInputEditTextFullname=findViewById(R.id.fullname);
        textInputEditTextUsername=findViewById(R.id.username);
        textInputEditTextEmail=findViewById(R.id.email);
        textInputEditTextPassword=findViewById(R.id.password);
        buttonSignUp=findViewById((R.id.buttonSignUp));
        textViewLogin=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);
        preferences=getSharedPreferences("busshareddata",MODE_PRIVATE);

        fAuth=FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname, phonenumber, password, email;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                phonenumber = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                email = String.valueOf(textInputEditTextEmail.getText());


                final int[] checkphone = {0};
                final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference userdata = database.child("adminphone");
                final DatabaseReference user = userdata.child(phonenumber);
                user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("email").exists()) {
                            Toast.makeText(getApplicationContext(), "Mobile number already Registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (!fullname.equals("") && !phonenumber.equals("") && !password.equals("") && !email.equals("")) {
                                progressBar.setVisibility(View.VISIBLE);
                                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            String[] str = email.split("@");
                                            String check = "admin";

                                            adminsignupdataholder obj = new adminsignupdataholder(fullname, phonenumber, email, check);
                                            FirebaseDatabase busdb = FirebaseDatabase.getInstance();
                                            DatabaseReference node = busdb.getReference("admindetails");
                                            node.child(str[0]).setValue(obj);


                                            usersignupdataholder obj1 = new usersignupdataholder(fullname, phonenumber, email);
                                            DatabaseReference node1 = busdb.getReference("userdetails");
                                            node1.child(str[0]).setValue(obj1);


                                            adminphoneholder obj2=new adminphoneholder(email);
                                            DatabaseReference adminphone=busdb.getReference("adminphone");
                                            adminphone.child(phonenumber).setValue(obj2);


                                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), Adminlogin.class));
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Adminlogin.class));
                finish();
            }
        });

    }
    public void onBackPressed() {
        Intent intent1=new Intent(Adminsignup.this,Adminlogin.class);
        startActivity(intent1);
    }
}


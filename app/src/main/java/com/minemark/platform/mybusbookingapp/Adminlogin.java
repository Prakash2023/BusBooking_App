package com.minemark.platform.mybusbookingapp;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Adminlogin extends AppCompatActivity {
    TextInputEditText eMail,Password;
    Button login;
    TextView textViewSignup;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    SharedPreferences preferences;
    TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        eMail=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progress);
        login=findViewById(R.id.buttonLogin);
        textViewSignup=findViewById(R.id.signUpText);
        forgotpassword=findViewById(R.id.textView46);
        fAuth=FirebaseAuth.getInstance();

      preferences=getSharedPreferences("busshareddata",0);
//        eMail.setText(preferences.getString("Email",""));
//        Password.setText(preferences.getString("Password",""));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  password, email;
                //fullname = String.valueOf(textInputEditTextFullname.getText());
                email = String.valueOf(eMail.getText());
                password = String.valueOf(Password.getText());


                if (!password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);

                    String[] str=email.split("@");

                    FirebaseDatabase busdb=FirebaseDatabase.getInstance();

                    final DatabaseReference node= busdb.getReference("admindetails");
                    final DatabaseReference emailcheck=node.child(str[0]);
                    emailcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                if(email.equals((String) snapshot.child("email").getValue()))
                                {
                                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                SharedPreferences.Editor editor=preferences.edit();
                                                editor.putString("adminemail",email);
                                                editor.putString("adminname",(String) snapshot.child("username").getValue());
                                                editor.putString("adminphonenumber",(String) snapshot.child("phonenumber").getValue());
                                                editor.apply();
                                                Toast.makeText(Adminlogin.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),Adminfunction.class));
                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(Adminlogin.this,"Error! "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(Adminlogin.this,"Admin not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Adminlogin.this,"Admin not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(Adminlogin.this,"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminlogin.this,Adminsignup.class));
                finish();
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1,password1;
                email1 = String.valueOf(eMail.getText());

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                if (!email1.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);

                    String[] str=email1.split("@");

                    FirebaseDatabase busdb=FirebaseDatabase.getInstance();

                    final DatabaseReference node= busdb.getReference("admindetails");
                    final DatabaseReference emailcheck=node.child(str[0]);
                    emailcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                if(email1.equals((String) snapshot.child("email").getValue()))
                                {
                                    fAuth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Adminlogin.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(Adminlogin.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                                    }

                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            });                                }
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(Adminlogin.this,"Admin not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Adminlogin.this,"Admin not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(Adminlogin.this,"email cannot be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),Adminuserlogin.class));
        finish();

    }
}

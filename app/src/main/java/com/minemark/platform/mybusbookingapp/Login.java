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

public class Login extends AppCompatActivity {
    TextInputEditText eMail,Password;
    Button login;
    TextView textViewSignup;
    ProgressBar progressBar;
    TextView forgotpassword;
    FirebaseAuth fAuth;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        eMail.setText(preferences.getString("Email",""));
        Password.setText(preferences.getString("Password",""));

        //fullname = String.valueOf(textInputEditTextFullname.getText());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  password, email;
                email = String.valueOf(eMail.getText());
                password = String.valueOf(Password.getText());
                if (!password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);

                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){



                                String[] str=email.split("@");

                                FirebaseDatabase busdb=FirebaseDatabase.getInstance();

                                final DatabaseReference node= busdb.getReference("userdetails");
                                final DatabaseReference emailcheck=node.child(str[0]);
                                emailcheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull final DataSnapshot snapshot) {
                                        if(snapshot.exists())
                                        {
                                            if(email.equals((String) snapshot.child("email").getValue()))
                                            {
                                                final  String phonenumber=(String) snapshot.child("phonenumber").getValue();
                                                final String username=(String) snapshot.child("username").getValue();
                                                SharedPreferences.Editor editor=preferences.edit();
                                                editor.putString("Email",email);
                                                editor.putString("Password",password);
                                                editor.putString("phonenumber",phonenumber);
                                                editor.putString("username",username);
                                                editor.apply();
                                                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(getApplicationContext(),Userfunction.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                            else
                                            {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(Login.this,"User not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Login.this,"User not found, Please Signup! ",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            }
                            else
                            {
                                Toast.makeText(Login.this,"Error! "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this,"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
                finish();
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1,password1;
                email1 = String.valueOf(eMail.getText());
                password1 = String.valueOf(Password.getText());
                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.sendPasswordResetEmail(email1)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "We have mailed you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Login.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });


    }
    public void onBackPressed() {

            startActivity(new Intent(getApplicationContext(),Adminuserlogin.class));
            finish();

    }
}

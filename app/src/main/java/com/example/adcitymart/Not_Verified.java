package com.example.adcitymart;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Not_Verified extends AppCompatActivity {

    FirebaseAuth Auth;
    FirebaseUser firebaseUser;
    String email;
    ImageView image;
    int time=2000;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not__verified);
        button=findViewById(R.id.resend);
        textView=findViewById(R.id.verfied);
        image=findViewById(R.id.image_verified);
        Auth = FirebaseAuth.getInstance();
        firebaseUser = Auth.getCurrentUser();
        email = firebaseUser.getEmail();
            if(!firebaseUser.isEmailVerified())
            {
                image.setImageResource(R.drawable.not_verified);
                textView.setText("Please Verify your Email To get Access to Application");
            }
            else
            {
                textView.setText("Welcome " + email);
                button.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                       Intent intent=new Intent(Not_Verified.this,Home.class);
                        startActivity(intent);
                        finish();
                    }
                },time);

            }

    }

    public void ResendMail(View view)
    {
        firebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(Not_Verified.this, "Verification Email Sent To "+email, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Not_Verified.this, "Email Sending Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

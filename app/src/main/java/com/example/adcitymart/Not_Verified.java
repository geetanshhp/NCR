package com.example.adcitymart;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Not_Verified extends AppCompatActivity {

    FirebaseAuth Auth;
    FirebaseUser firebaseUser;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not__verified);

        Auth = FirebaseAuth.getInstance();
        firebaseUser = Auth.getCurrentUser();
        email = firebaseUser.getEmail();
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

package com.example.adcitymart;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    SharedPreferences preferences;
    boolean flag;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FrameLayout frameLayout;
    TextView textView;
        String email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        frameLayout =findViewById(R.id.frame_layout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationview);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.idnavigation);
        navigationView  .setNavigationItemSelectedListener(this);


        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //...........what is this............
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!flag)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }



        //To show user id 0n header
       /* name_navigation= navigationView.getHeaderView(0).findViewById(R.id.idofuser);
        Uemail = firebaseUser.getEmail().toString();
        name_navigation.setText(Uemail);*/
        textView=navigationView.getHeaderView(0).findViewById(R.id.idofuser);
        email=firebaseUser.getEmail().toString();
        textView.setText(email);
    }





//ALERT DILAOG BOX

    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("CONFIRM EXIT");
        alert.setTitle("ARE YOU SURE TO EXIT");
        alert.setIcon(R.drawable.close);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                    finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
            alert.show();

    }

   /* public void LogoutMethod(View view)
    {
        flag = false;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean("Islogin",flag).commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       /* if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }*/
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.idhome:
                    Toast.makeText(Home.this, "This is home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.idcontact:
                    Toast.makeText(Home.this, "This  page", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aboutus:
                    Toast.makeText(Home.this, "This is About us", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment=null;
        switch (item.getItemId())
        {
            case R.id.logo:
               fragment=new logo_design();
                break;
            case R.id.threeD:
                Toast.makeText(this, " 3D Modelling and design", Toast.LENGTH_SHORT).show();
                break;
            case R.id.webs:
                fragment=new website();
                break;
            case R.id.android:
                        fragment =new andoid();
                break;
            case R.id.cms:
                fragment=new cms();
                break;
            case R.id.e:
                Toast.makeText(this, "E-Commerce", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sco:
                Toast.makeText(this, "SCO Marketing", Toast.LENGTH_SHORT).show();
                break;
            case R.id.smo:
                fragment=new smo();
                break;
            case R.id.digital:
                Toast.makeText(this, "Digital Markrting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.emailmarket:
            Toast.makeText(this, "E-mail Marketing", Toast.LENGTH_SHORT).show();
            break;
            case R.id.ad1:
                Toast.makeText(this, "AD 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ad2:
                Toast.makeText(this, "AD 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id3:
                Toast.makeText(this, "AD 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id4:
                Toast.makeText(this, "AD 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.career:
                Toast.makeText(this, "Carreres", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paterns:
            Toast.makeText(this, "Paterns", Toast.LENGTH_SHORT).show();
                break;
            case R.id.interm:
            Toast.makeText(this, "Interns", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                logOutMethod();
                break;


        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }







    //Method to log out

    private void logOutMethod()
    {
        auth=FirebaseAuth.getInstance();
            firebaseUser =auth.getCurrentUser();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Do u want to Log out");
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                auth.signOut();
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                    preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
                    preferences.edit().putBoolean("IsLogin",false).commit();
                        finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

    }
}




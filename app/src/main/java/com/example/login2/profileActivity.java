package com.example.login2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class profileActivity extends AppCompatActivity {
    private String recevierleague;
    private ImageView imageView;
    private TextView profileleaguename,profilelocation,profileprice,profileemail,profileusername,profilephone,detailed,playground,numder;
    Button Add;
    private DatabaseReference referencee;
    private static final int    REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_profile);

        referencee= FirebaseDatabase.getInstance().getReference().child("league");


        recevierleague=getIntent().getExtras().get("visite_league_details").toString();

        imageView=findViewById(R.id.leagueimage);
        Add=findViewById(R.id.add);
        profilephone=findViewById(R.id.P);
        profileleaguename= findViewById(R.id.LN);
        profilelocation=findViewById(R.id.L);
        profileprice=findViewById(R.id.PTJ);
        profileemail=findViewById(R.id.E);
        profileusername=findViewById(R.id.UN);
        detailed=findViewById(R.id.textView14);
        playground=findViewById(R.id.textView15);
        numder=findViewById(R.id.textView16);



        RetriveInformation();

    }



    private void RetriveInformation() {
        referencee.child(recevierleague).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    //   String IMAGE=snapshot.child("image ").getValue().toString();

                    String LEAGUENAME=snapshot.child("league_name").getValue().toString();
                    String LOCATION=snapshot.child("location").getValue().toString();
                    String USERNAME=snapshot.child("person name").getValue().toString();
                    String PHONE=snapshot.child("phone number").getValue().toString();
                    String PRICE=snapshot.child("price").getValue().toString();
                    String EMAIL=snapshot.child("email").getValue().toString();

                    String detailedadd=snapshot.child("detailed_address").getValue().toString();
                    String playname=snapshot.child("playground").getValue().toString();
                    String numderr=snapshot.child("number").getValue().toString();

                    String image=snapshot.child("image").getValue().toString();
                    Picasso.get().load(image).placeholder(R.drawable.addtwo).into(imageView);


                    profileemail.setText(EMAIL);
                    profileleaguename.setText(LEAGUENAME);
                    profilelocation.setText(LOCATION);
                    profilephone.setText(PHONE);
                    profileprice.setText(PRICE);
                    profileusername.setText(USERNAME);
                    detailed.setText(detailedadd);
                    playground.setText(playname);
                    numder.setText(numderr);
                    //kugjgklgjfc123510

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.proofiile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.call:
//makephonecall();

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+profilephone.getText()));
                startActivity(intent);

                break;
        }



        return super.onOptionsItemSelected(item);

    }


}
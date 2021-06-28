package com.example.login2;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login2.ui.LeagueFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ADD_LEAGUE extends AppCompatActivity {
    EditText phoneNo, personeName, leagueName, location,price,Detailedaddress,playy,numderr;
    Button save;
    ImageView imageView;
    FirebaseDatabase rootNode;
   DatabaseReference referencee = FirebaseDatabase.getInstance().getReference().child("league");
 StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("image");
    SharedPreferences sharedPreferences;


 private Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d__l_e_a_g_u_e);

        personeName=findViewById(R.id.personeName);
        phoneNo=findViewById(R.id.phoneNo);
        leagueName=findViewById(R.id.leagueName);
    //    email=findViewById(R.id.email);
        location=findViewById(R.id.location);
        price=findViewById(R.id.price);
        save=findViewById(R.id.save);
        imageView=findViewById(R.id.imageView);
        Detailedaddress=findViewById(R.id.location2);
        playy=findViewById(R.id.Nam);
        numderr=findViewById(R.id.Number);



        sharedPreferences=getSharedPreferences("myPref",MODE_PRIVATE);
        String emaill = sharedPreferences.getString("emaail",null);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosepic();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String txt_personName=personeName.getText().toString();
                String txt_phoneNo=phoneNo.getText().toString();
                String txt_location=location.getText().toString();
                String txt_leagueName=leagueName.getText().toString();
                String txt_price=price.getText().toString();
                String txt_detailed=Detailedaddress.getText().toString();
                String txt_playnum=playy.getText().toString();
                String txt_Numder=numderr.getText().toString();


                //           String txt_email=email.getText().toString();
                if ( txt_leagueName.isEmpty() || txt_location.isEmpty() ||txt_personName.isEmpty() ||
                        txt_phoneNo.isEmpty() || txt_price.isEmpty()|| txt_detailed.isEmpty()|| txt_playnum.isEmpty() || txt_Numder.isEmpty())
                {
                    Toast.makeText(ADD_LEAGUE.this, "Some Detials Missed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ADD_LEAGUE.this, "Your Detials Add Sucessfully", Toast.LENGTH_SHORT).show();

                    StorageReference fileref=storageReference.child(System.currentTimeMillis()+ "." +getFileExtention(imageuri));
                    fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("person name", txt_personName);
                                    map.put("league_name", txt_leagueName);
                                    map.put("phone number", txt_phoneNo);
                                    map.put("location", txt_location);
                                    map.put("price", txt_price);
                                    map.put("email", emaill);

                                    map.put("detailed_address", txt_detailed);
                                    map.put("playground", txt_playnum);
                                    map.put("number", txt_Numder);

                                    String image = uri.toString();
                                   map.put("image", image);
                                    referencee.push().setValue(map);
                                  //  FirebaseDatabase.getInstance().getReference().child("new league").push().setValue(map);

                                }
                            });



                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                   Intent ii = new Intent(ADD_LEAGUE.this, Project.class);
                   startActivity(ii);

            }}
        });


    }


    private void choosepic() {
        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==101 && resultCode==RESULT_OK && data!=null)){
            imageuri=data.getData();
            imageView.setImageURI(imageuri);
        }
    }

    private String getFileExtention(Uri imi) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imi));
    }
    }
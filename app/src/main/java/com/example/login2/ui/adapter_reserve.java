package com.example.login2.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login2.Playground_Details;
import com.example.login2.R;
import com.example.login2.groung;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class adapter_reserve extends FirebaseRecyclerAdapter<groung,adapter_reserve.reserveviewholder> {
    private DatabaseReference leagueref, referencee;
    SharedPreferences sharedPreferences;


    public adapter_reserve(@NonNull FirebaseRecyclerOptions<groung> options) {
        super(options);
    }

    @NonNull
    @Override
    public reserveviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new reserveviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout, parent, false));

    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull reserveviewholder holder, int position, @NonNull groung model) {
        leagueref = FirebaseDatabase.getInstance().getReference().child("playground");
        referencee = FirebaseDatabase.getInstance().getReference().child("playground");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent profilintent = new Intent(v.getContext(), Playground_Details.class);
               profilintent.putExtra("visite_league_details", getRef(position).getKey());
               v.getContext().startActivity(profilintent);

           }
        });

        String league_id = getRef(position).getKey();
        referencee.child(league_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String Lname = snapshot.child("league name").getValue().toString();
                    String Locname = snapshot.child("location").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();

                    holder.leaguesname.setText(Lname);

                    holder.locations.setText(Locname);
                    if (image != null) {
                        Picasso.get().load(image).into(holder.profimage);
                    } else {
                        holder.profimage.setImageResource(R.drawable.ic_profile);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
            });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sharedPreferences = v.getContext().getSharedPreferences("myPref", MODE_PRIVATE);
                String userType = sharedPreferences.getString("userType",null);
                if (userType.equals("Manager")) {
                    alertDelete(v, league_id);
                }
                return true;
            }
        });

        }

    public void alertDelete(View v, final String id) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(v.getContext());
        dialogBuilder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                leagueref.child(id).removeValue();
                Toast.makeText(v.getContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create();
        dialogBuilder.show();

    }

///////////////////////////////////////////////////////////////////////

    public static class reserveviewholder extends RecyclerView.ViewHolder {
        TextView leaguesname, locations;
        ImageView profimage;

        public reserveviewholder(@NonNull View itemView) {
            super(itemView);

            leaguesname = itemView.findViewById(R.id.league_name);
            locations = itemView.findViewById(R.id.location_name);
            profimage = itemView.findViewById(R.id.my_image);
        }
    }
}



